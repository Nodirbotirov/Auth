package com.nod.auth.service;

import com.nod.auth.entity.UserEntity;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public class UserService {


    public UserEntity loadByUserId(Integer id) {
        try {
            Optional<UserEntity> byId = userRepository.findById(id);
            return byId.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
