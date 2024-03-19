package com.pinsoft.project.movierating.Controller;

import com.pinsoft.project.movierating.DTO.CommentAddDto;
import com.pinsoft.project.movierating.DTO.CommentGetDto;
import com.pinsoft.project.movierating.DTO.CommentUpdateDto;
import com.pinsoft.project.movierating.Entity.Comment;
import com.pinsoft.project.movierating.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/comment")
    public boolean AddComment(@RequestBody CommentAddDto commentAddDto) {
        return commentService.AddComment(commentAddDto);
    }

    @GetMapping("/comment")
    public List<CommentGetDto> GetAllComments(){
        return commentService.GetAllComments();
    }

    @GetMapping("/comment/{movieId}")
    public List<CommentGetDto> GetCommentsByMovieId(@PathVariable Long movieId){
        return commentService.GetCommentsByMovieId(movieId);
    }

    @DeleteMapping("/comment/{commentId}")
    public boolean RemoveComment(@PathVariable Long commentId){
        return commentService.RemoveComment(commentId);
    }

    @PutMapping("/comment/{id}")
    public boolean UpdateComment(@RequestBody CommentUpdateDto commentUpdateDto, @PathVariable Long id){
        return commentService.UpdateComment(commentUpdateDto,id);
    }
}
