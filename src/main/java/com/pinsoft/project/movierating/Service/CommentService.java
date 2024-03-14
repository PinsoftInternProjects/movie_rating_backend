package com.pinsoft.project.movierating.Service;

import com.pinsoft.project.movierating.DTO.CommentAddDto;
import com.pinsoft.project.movierating.DTO.CommentUpdateDto;
import com.pinsoft.project.movierating.Entity.Comment;
import com.pinsoft.project.movierating.Repository.CommentRepository;
import com.pinsoft.project.movierating.Repository.MovieRepository;
import com.pinsoft.project.movierating.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    public boolean AddComment(CommentAddDto commentAddDto){
        try {
            Comment comment = new Comment();
            comment.setUser(userRepository.findById(commentAddDto.getUserId()).get());
            comment.setMovie(movieRepository.findById(commentAddDto.getMovieId()).get());
            comment.setComment(commentAddDto.getComment());
            comment.setRating(commentAddDto.getRating());
            comment.setTime(commentAddDto.getTime());
            commentRepository.save(comment);
            return true;
        }catch (Exception exception) {
            return false;
        }
    }

    public List<Comment> GetAllComments() {
        return commentRepository.findAll();
    }

    public List<Comment> GetCommentsByMovieId(Long id){
        return commentRepository.findCommentByMovieId(id);
    }

    public boolean RemoveComment(Long id){
        try {
            commentRepository.deleteById(id);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public boolean UpdateComment(CommentUpdateDto commentUpdateDto){
        try {
            Comment comment = commentRepository.findById(commentUpdateDto.getCommentId()).get();
            comment.setComment(commentUpdateDto.getComment());
            comment.setRating(commentUpdateDto.getRating());
            comment.setTime(commentUpdateDto.getTime());
            commentRepository.save(comment);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

}
