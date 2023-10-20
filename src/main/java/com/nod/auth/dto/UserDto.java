package com.nod.auth.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private Integer id;

    private String username;

    private String password;

    public UserDto( String username, String password) {
        this.username = username;
        this.password = password;
    }
}
