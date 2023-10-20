package com.nod.auth.component;


import com.nod.auth.entity.UserEntity;
import com.nod.auth.entity.enums.UserRole;
import com.nod.auth.repository.UserRepository;
import com.nod.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;


    @Value("never")
    private String mode;


    @Override
    public void run(String... args) throws Exception {
        try {
            Optional<UserEntity> byUsername = userRepository.findByUsername("nodir123");
           if (byUsername.isEmpty()){
               userRepository.save(new UserEntity(
                       "nodir123",
                       passwordEncoder.encode("nodir123"),
                       UserRole.SUPER_ADMIN
               ));
           }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
