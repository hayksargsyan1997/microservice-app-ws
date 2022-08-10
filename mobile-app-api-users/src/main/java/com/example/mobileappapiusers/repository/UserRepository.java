package com.example.mobileappapiusers.repository;

import com.example.mobileappapiusers.data.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByEmail(String username);
}
