package com.recipeplatform.recipePlatform.Controller;

import com.recipeplatform.recipePlatform.Service.CommentService;
import com.recipeplatform.recipePlatform.entity.Comment;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/{recipeId}/{userId}")
    public Object addComment(
            @PathVariable Long recipeId,
            @PathVariable Long userId,
            @RequestBody String text) {

        Comment comment = commentService.addComment(recipeId, userId, text);
        return comment != null ? comment : "Failed to add comment!";
    }

    @GetMapping("/{recipeId}")
    public List<Comment> getCommentsByRecipe(@PathVariable Long recipeId) {
        return commentService.getCommentsByRecipe(recipeId);
    }

    @DeleteMapping("/{commentId}/{userId}")
    public String deleteComment(@PathVariable Long commentId, @PathVariable Long userId) {
        commentService.deleteComment(commentId, userId);
        return "Comment deleted successfully!";
    }
}
