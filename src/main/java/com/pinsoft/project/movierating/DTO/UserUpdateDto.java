package com.pinsoft.project.movierating.DTO;

import lombok.Data;

@Data
public class UserUpdateDto {
    private Long id;
    private String email;
    private String username;
    private String password;
    private String base64image;
}
