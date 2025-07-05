package com.recipeplatform.recipePlatform.Service;
import org.springframework.stereotype.Service;

import com.recipeplatform.recipePlatform.Repository.RecipeRepository;
import com.recipeplatform.recipePlatform.Repository.UserRepository;
import com.recipeplatform.recipePlatform.entity.Recipe;
import com.recipeplatform.recipePlatform.entity.User;

import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    private final RecipeRepository recipeRepository;
    private final UserRepository userRepository;

    public RecipeService(RecipeRepository recipeRepository, UserRepository userRepository) {
        this.recipeRepository = recipeRepository;
        this.userRepository = userRepository;
    }

    public Recipe createRecipe(Recipe recipe, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        recipe.setUser(user);
        return recipeRepository.save(recipe);
    }

    public Optional<Recipe> getRecipeById(Long id) {
        return recipeRepository.findById(id);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public List<Recipe> searchRecipesByTitle(String title) {
        return recipeRepository.searchByTitle(title);
    }

    public Recipe updateRecipe(Long recipeId, Long userId, Recipe updatedRecipe) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!recipe.getUser().getId().equals(userId) && !user.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("You can only update your own recipes");
        }

        recipe.setTitle(updatedRecipe.getTitle());
        recipe.setIngredients(updatedRecipe.getIngredients());
        recipe.setSteps(updatedRecipe.getSteps());
        recipe.setCategory(updatedRecipe.getCategory());

        return recipeRepository.save(recipe);
    }

    public void deleteRecipe(Long recipeId, Long userId) {
        Recipe recipe = recipeRepository.findById(recipeId)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!recipe.getUser().getId().equals(userId) && !user.getRole().equals("ROLE_ADMIN")) {
            throw new RuntimeException("You can only delete your own recipes");
        }

        recipeRepository.delete(recipe);
    }

}
