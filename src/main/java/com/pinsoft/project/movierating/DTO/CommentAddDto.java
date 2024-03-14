package com.pinsoft.project.movierating.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentAddDto {
    private Long userId;
    private Long movieId;
    private String comment;
    private int rating;
    private LocalDateTime time;
}
