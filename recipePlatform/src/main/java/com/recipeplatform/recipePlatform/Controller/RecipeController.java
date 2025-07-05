package com.recipeplatform.recipePlatform.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import com.recipeplatform.recipePlatform.Service.RecipeService;
import com.recipeplatform.recipePlatform.Service.UserService;
import com.recipeplatform.recipePlatform.entity.Recipe;
import com.recipeplatform.recipePlatform.entity.User;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/recipes")
public class RecipeController {

	private final RecipeService recipeService;
    private final UserService userService; 

    public RecipeController(RecipeService recipeService, UserService userService) {
        this.recipeService = recipeService;
        this.userService = userService;
    }

    @PostMapping("/{userId}")
    public String createRecipe(@PathVariable Long userId, @RequestBody Recipe recipe, Authentication authentication) {
        String loggedInUsername = authentication.getName();
        
        Optional<User> authenticatedUserOpt = userService.getUserByUsername(loggedInUsername);
        
        if (authenticatedUserOpt.isEmpty()) {
            return "User not found!";
        }

        User authenticatedUser = authenticatedUserOpt.get();

        if (!authenticatedUser.getId().equals(userId)) {
            return "You can only create recipes for your own account!";
        }

        Recipe newRecipe = recipeService.createRecipe(recipe, userId);
        return "Recipe created successfully with ID: " + newRecipe.getId();
    }
    
    @GetMapping("/{id}")
    public Object getRecipe(@PathVariable Long id) {
        Optional<Recipe> recipe = recipeService.getRecipeById(id);
        return recipe!= null ? recipe : "Recipe not found!";
    }

    @GetMapping
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @GetMapping("/search")
    public List<Recipe> searchRecipes(@RequestParam String keyword) {
        return recipeService.searchRecipesByTitle(keyword);
    }

    @PutMapping("/{recipeId}/{userId}")
    public Object updateRecipe(
            @PathVariable Long recipeId,
            @PathVariable Long userId,
            @RequestBody Recipe updatedRecipe,
            Authentication authentication) {
    	
	    	String loggedInUsername = authentication.getName();
	        
	        Optional<User> authenticatedUserOpt = userService.getUserByUsername(loggedInUsername);
	        
	        if (authenticatedUserOpt.isEmpty()) {
	            return "User not found!";
	        }
	
	        User authenticatedUser = authenticatedUserOpt.get();
	
	        if (!authenticatedUser.getId().equals(userId)) {
	            return "access denied";
	        }
	
	       
            return recipeService.updateRecipe(recipeId, userId, updatedRecipe);
        
    }

    @DeleteMapping("/{recipeId}/{userId}")
    public String deleteRecipe(@PathVariable Long recipeId, @PathVariable Long userId) {
        
            recipeService.deleteRecipe(recipeId, userId);
            return "Recipe deleted successfully!";
        
    }
}
