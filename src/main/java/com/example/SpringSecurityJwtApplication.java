package com.example;

import com.example.models.ERole;
import com.example.models.RoleEntity;
import com.example.models.UserEntity;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@SpringBootApplication
@EntityScan(basePackages = "com.example.models")
public class SpringSecurityJwtApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityJwtApplication.class, args);
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Bean
    CommandLineRunner init() {
        return args -> {

            UserEntity userEntityAdmin = UserEntity.builder()
                    .email("admin@email.com")
                    .username("admin")
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(RoleEntity.builder()
                            .name(ERole.valueOf(ERole.ADMIN.name()))
                            .build()))
                    .build();

            userRepository.save(userEntityAdmin);

            UserEntity userEntityUser = UserEntity.builder()
                    .email("user1@email.com")
                    .username("user")
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(RoleEntity.builder()
                            .name(ERole.valueOf(ERole.USER.name()))
                            .build()))
                    .build();

            userRepository.save(userEntityUser);

            UserEntity userEntityInvited = UserEntity.builder()
                    .email("invited@email.com")
                    .username("invited")
                    .password(passwordEncoder.encode("1234"))
                    .roles(Set.of(RoleEntity.builder()
                            .name(ERole.valueOf(ERole.INVITED.name()))
                            .build()))
                    .build();

            userRepository.save(userEntityInvited);
        };
    }

}
