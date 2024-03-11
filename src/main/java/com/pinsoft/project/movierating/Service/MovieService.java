package com.pinsoft.project.movierating.Service;

import com.pinsoft.project.movierating.DTO.MovieDto;
import com.pinsoft.project.movierating.DTO.MovieUpdateDto;
import com.pinsoft.project.movierating.Entity.Category;
import com.pinsoft.project.movierating.Entity.Movie;
import com.pinsoft.project.movierating.Repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private CategoryService categoryService;

    public List<Movie> getAllMovies(){return movieRepository.findAll();}

    public Optional<Movie> getMovieById(Long id){return movieRepository.findById(id);}

    public  void  addMovie(MovieDto movieDto){
        Movie movie = new Movie();
        movie.setName(movieDto.getName());
       Category category = categoryService.getCategoryById(movieDto.getCategoryId()).get();
       movie.setCategory(category);
       movie.setLanguage(movieDto.getLanguage());
       movie.setExplanation(movieDto.getExplanation());
       movie.setRelease_date(movieDto.getRelease_date());
       movie.setBase64Image(movieDto.getBase64Image());
       movieRepository.save(movie);

    }

    public void updateMovie(MovieUpdateDto movieUpdateDto){
        Movie movie = movieRepository.findById(movieUpdateDto.getId()).get();
        movie.setName(movieUpdateDto.getName());
        Category category = categoryService.getCategoryById(movieUpdateDto.getCategoryId()).get();
        movie.setCategory(category);
        movie.setLanguage(movieUpdateDto.getLanguage());
        movie.setExplanation(movieUpdateDto.getExplanation());
        movie.setRelease_date(movieUpdateDto.getRelease_date());
        movie.setBase64Image(movieUpdateDto.getBase64Image());
        movieRepository.save(movie);
    }

    public void deleteMovie(Long id,String token){
        movieRepository.deleteById(id);
    }
}
