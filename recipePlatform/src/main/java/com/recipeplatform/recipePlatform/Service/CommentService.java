package com.recipeplatform.recipePlatform.Service;
import org.springframework.stereotype.Service;

import com.recipeplatform.recipePlatform.Repository.CommentRepository;
import com.recipeplatform.recipePlatform.Repository.RecipeRepository;
import com.recipeplatform.recipePlatform.Repository.UserRepository;
import com.recipeplatform.recipePlatform.entity.Comment;
import com.recipeplatform.recipePlatform.entity.Recipe;
import com.recipeplatform.recipePlatform.entity.User;

import java.util.List;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public CommentService(CommentRepository commentRepository, UserRepository userRepository, RecipeRepository recipeRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public Comment addComment(Long recipeId, Long userId, String text) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        
        if (user.getRole().equals("ADMIN")) {
            throw new RuntimeException("Admins cannot comment on recipes");
        }

      
        if (recipe.getUser().getId().equals(userId)) {
            throw new RuntimeException("You cannot comment on your own recipe");
        }

        Comment comment = new Comment();
        comment.setText(text);
        comment.setUser(user);
        comment.setRecipe(recipe);
        return commentRepository.save(comment);
    }


    public List<Comment> getCommentsByRecipe(Long recipeId) {
        return commentRepository.findByRecipeId(recipeId);
    }

    public void deleteComment(Long commentId, Long userId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        if (!comment.getUser().getId().equals(userId)) {
            throw new RuntimeException("You can only delete your own comments");
        }

        commentRepository.delete(comment);
    }
}
