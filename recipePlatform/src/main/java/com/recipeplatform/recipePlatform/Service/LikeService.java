package com.recipeplatform.recipePlatform.Service;

import org.springframework.stereotype.Service;
import com.recipeplatform.recipePlatform.Repository.LikeRepository;
import com.recipeplatform.recipePlatform.Repository.RecipeRepository;
import com.recipeplatform.recipePlatform.Repository.UserRepository;
import com.recipeplatform.recipePlatform.entity.Like;
import com.recipeplatform.recipePlatform.entity.Recipe;
import com.recipeplatform.recipePlatform.entity.User;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;

    public LikeService(LikeRepository likeRepository, UserRepository userRepository, RecipeRepository recipeRepository) {
        this.likeRepository = likeRepository;
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
    }

    public void likeRecipe(Long recipeId, Long userId) {
       
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

       
        if ("ADMIN".equals(user.getRole())) {
            throw new RuntimeException("Admins cannot like recipes");
        }

        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));

        
        if (recipe.getUser().getId().equals(userId)) {
            throw new RuntimeException("You cannot like your own recipe");
        }

       
        if (likeRepository.existsByUserIdAndRecipeId(userId, recipeId)) {
            throw new RuntimeException("You have already liked this recipe");
        }

      
        Like like = new Like(user, recipe);
        likeRepository.save(like);
    }

    public long Likecount(Long recipeId) {
		// TODO Auto-generated method stub
		return likeRepository.countLikesByRecipeId(recipeId);
	}
    
    public void unlikeRecipe(Long recipeId, Long userId) {
        Like like = likeRepository.findByUserIdAndRecipeId(userId, recipeId)
                .orElseThrow(() -> new RuntimeException("You haven't liked this recipe"));

        likeRepository.delete(like);
    }
}

