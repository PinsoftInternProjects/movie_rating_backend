package com.pinsoft.project.movierating.Repository;

import com.pinsoft.project.movierating.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie , Long> {
}
