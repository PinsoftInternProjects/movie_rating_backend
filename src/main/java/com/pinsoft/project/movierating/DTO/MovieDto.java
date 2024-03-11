package com.pinsoft.project.movierating.DTO;

import lombok.Data;

@Data
public class MovieDto {
    private String name;
    private int release_date;
    private String explanation;
    private String language;
    private String base64Image;
    private Long categoryId;
    private String token;
}
