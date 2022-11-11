package com.example.mobileappapiusers.service;


import com.example.mobileappapiusers.data.AlbumsServiceClient;
import com.example.mobileappapiusers.controller.dto.UserDto;
import com.example.mobileappapiusers.entity.UserEntity;
import com.example.mobileappapiusers.model.AlbumResponseModel;
import com.example.mobileappapiusers.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserRepository repository;

    //    private RestTemplate restTemplate;
    private final Environment environment;
    private AlbumsServiceClient albumsServiceClient;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public UserServiceImpl(UserRepository repository, BCryptPasswordEncoder bCryptPasswordEncoder, Environment environment, AlbumsServiceClient albumsServiceClient) {
        this.repository = repository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
//
        this.environment = environment;
        this.albumsServiceClient = albumsServiceClient;
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setUserId(UUID.randomUUID().toString());
        userDto.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserEntity userEntity = modelMapper.map(userDto, UserEntity.class);


        return modelMapper.map(repository.save(userEntity), UserDto.class);
    }

    @Override
    public UserDto getUserDetailsByEmail(String email) {
        UserEntity userEntity = repository.findByEmail(email);
        if (userEntity == null) {
            throw new UsernameNotFoundException(email);

        }
        return new ModelMapper().map(userEntity, UserDto.class);
    }

    @Override
    public UserDto getUserByUserId(String userId) {
        UserEntity userEntity = repository.findByUserId(userId);
        if (userEntity == null) {
            throw new UsernameNotFoundException("User not found");
        }
        ModelMapper modelMapper = new ModelMapper();

        UserDto userDto = modelMapper.map(userEntity, UserDto.class);
//        String albumsUrl=String.format(Objects.requireNonNull(environment.getProperty("albums.url")),userId);
//        ResponseEntity<List<AlbumResponseModel>> albumsListResponse = restTemplate.exchange(albumsUrl,
//                HttpMethod.GET, null, new ParameterizedTypeReference<>() {
//                });
//        List<AlbumResponseModel> albumsList= albumsListResponse.getBody();
        logger.info("Before calling albums Microservice");
        List<AlbumResponseModel> albumsList = albumsServiceClient.getAlbums(userId);
        logger.info("After calling albums Microservice");

        userDto.setAlbums(albumsList);

        return userDto;

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = repository.findByEmail(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(username);
        }
        return new User(userEntity.getEmail(), userEntity.getEncryptedPassword(), true, true,
                true, true, new ArrayList<>());
    }
}
