package com.pinsoft.project.movierating.Service;

import com.pinsoft.project.movierating.DTO.CommentAddDto;
import com.pinsoft.project.movierating.DTO.CommentGetDto;
import com.pinsoft.project.movierating.DTO.CommentUpdateDto;
import com.pinsoft.project.movierating.Entity.Comment;
import com.pinsoft.project.movierating.Repository.CommentRepository;
import com.pinsoft.project.movierating.Repository.MovieRepository;
import com.pinsoft.project.movierating.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<CommentGetDto> GetAllComments() {
        List<Comment> comments = commentRepository.findAll();
        List<CommentGetDto> commentGetDtos = new ArrayList<CommentGetDto>();
        comments.forEach(comment -> {
            CommentGetDto commentGetDto = new CommentGetDto();
            commentGetDto.setId(comment.getId());
            commentGetDto.setMovieId(comment.getMovie().getId());
            commentGetDto.setUserId(comment.getUser().getId());
            commentGetDto.setUserName(comment.getUser().getUsername());
            commentGetDto.setComment(comment.getComment());
            commentGetDto.setRating(comment.getRating());
            commentGetDto.setTime(comment.getTime());
            commentGetDtos.add(commentGetDto);
        });
        return commentGetDtos;
    }

    public List<CommentGetDto> GetCommentsByMovieId(Long id){
        List<Comment> comments = commentRepository.findCommentByMovieId(id);
        List<CommentGetDto> commentGetDtos = new ArrayList<CommentGetDto>();
        comments.forEach(comment -> {
            CommentGetDto commentGetDto = new CommentGetDto();
            commentGetDto.setId(comment.getId());
            commentGetDto.setMovieId(comment.getMovie().getId());
            commentGetDto.setUserId(comment.getUser().getId());
            commentGetDto.setUserName(comment.getUser().getUsername());
            commentGetDto.setComment(comment.getComment());
            commentGetDto.setRating(comment.getRating());
            commentGetDto.setTime(comment.getTime());
            commentGetDtos.add(commentGetDto);
        });
        return commentGetDtos;
    }

    public boolean RemoveComment(Long id){
        try {
            commentRepository.deleteById(id);
            return true;
        }catch (Exception exception){
            return false;
        }
    }

    public boolean UpdateComment(CommentUpdateDto commentUpdateDto, Long commentId){
        try {
            Comment comment = commentRepository.findById(commentId).get();
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
