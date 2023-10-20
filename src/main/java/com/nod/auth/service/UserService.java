package com.nod.auth.service;

import com.nod.auth.entity.UserEntity;
import com.nod.auth.payload.AllApiResponse;
import com.nod.auth.payload.LoginRequest;
import com.nod.auth.payload.Response;
import com.nod.auth.payload.TokenPayload;
import com.nod.auth.repository.UserRepository;
import com.nod.auth.secret.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;



    public Response all(){
        try {
            return new Response("success", true, userRepository.findAll());
        }catch (Exception e){
            e.printStackTrace();
            return new Response("server bugs", false);
        }
    }




    public UserEntity loadByUserId(Integer id) {
        try {
            Optional<UserEntity> byId = userRepository.findById(id);
            return byId.orElse(null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<UserEntity> byUsername = userRepository.findByUsername(username);
            if (byUsername.isPresent()) {
                return byUsername.get();
            } else {
                throw new UsernameNotFoundException("Username or password incorrect!");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Error enter with login and password!");
        }
    }

    public HttpEntity<?> SignIn(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
            UserEntity principal = (UserEntity) authentication.getPrincipal();
            TokenPayload jwt = jwtTokenProvider.generateToken(principal);
            return AllApiResponse.response(1, "Login successfully", jwt);
        } catch (Exception e){
            e.printStackTrace();
            return AllApiResponse.response(500,0,"Error to login", e.getMessage());
        }
    }
}
