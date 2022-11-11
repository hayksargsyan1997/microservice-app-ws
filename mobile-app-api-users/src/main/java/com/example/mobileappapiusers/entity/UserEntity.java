package com.example.mobileappapiusers.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
public class UserEntity implements Serializable {

    private static final long SERIAL_VERSION_UID = -2731425678149216053L;
    @Id
    @SequenceGenerator(name = "user_sequence",
    sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
    private long id;
    @Column(nullable = false, length = 50)
    private String firstName;
    @Column(nullable = false, length = 50)
    private String lastName;
    @Column(nullable = false, length = 120, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String encryptedPassword;
    @Column(nullable = false, unique = true)
    private String userId;


}
