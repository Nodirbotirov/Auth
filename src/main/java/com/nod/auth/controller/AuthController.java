package com.nod.auth.controller;

import com.nod.auth.entity.UserEntity;
import com.nod.auth.payload.AllApiResponse;
import com.nod.auth.payload.LoginRequest;
import com.nod.auth.secret.CurrentUser;
import com.nod.auth.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;


@Controller
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/login")
    public HttpEntity<?> login(@ModelAttribute LoginRequest loginRequest) {
        return userService.SignIn(loginRequest);
    }


    @GetMapping("/me")
    public HttpEntity<?> getMe(@CurrentUser UserEntity userEntity) {
        if (userEntity!= null){
            return AllApiResponse.response(1,"Current user", Map.of(
                    "username", userEntity.getUsername(),
                    "role", userEntity.getRole(),
                    "id", userEntity.getId()
            ));
        }else {
            return AllApiResponse.response(403,0,"Forbidden");
        }
    }


}
