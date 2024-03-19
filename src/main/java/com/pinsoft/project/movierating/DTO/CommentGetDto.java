package com.pinsoft.project.movierating.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentGetDto {
    private Long id;
    private Long movieId;
    private Long userId;
    private String userName;
    private String comment;
    private int rating;
    private LocalDateTime time;
}
