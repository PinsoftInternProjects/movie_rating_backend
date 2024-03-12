package com.pinsoft.project.movierating.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "movie-table", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int release_date;
    private String explanation;
    private String language;
    private String base64Image;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
