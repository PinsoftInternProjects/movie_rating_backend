package com.pinsoft.project.movierating.Controller;

import com.pinsoft.project.movierating.DTO.MovieDto;
import com.pinsoft.project.movierating.DTO.MovieUpdateDto;
import com.pinsoft.project.movierating.Entity.Movie;
import com.pinsoft.project.movierating.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<List<Movie>> getAllMovies(){
        List<Movie> movies = movieService.getAllMovies();
        return  new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/movies/{id}")
    public  ResponseEntity<Movie> getMovieById(@PathVariable Long id){
        Optional<Movie> movieOptional = movieService.getMovieById(id);
        if (movieOptional.isPresent()){
            return new ResponseEntity<>(movieOptional.get(),HttpStatus.OK);
        }else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/movies")
    public void addMovie(@RequestBody MovieDto movieDto){movieService.addMovie(movieDto);}

    @PutMapping("/movies/{id}")
    public  void updateMovie(@RequestBody MovieUpdateDto movieUpdateDto){ movieService.updateMovie(movieUpdateDto);}

    @DeleteMapping("/movies/{id}/{token}")
    public void deleteMovie(@PathVariable Long id,@PathVariable String token){
        movieService.deleteMovie(id,token);
    }
}
