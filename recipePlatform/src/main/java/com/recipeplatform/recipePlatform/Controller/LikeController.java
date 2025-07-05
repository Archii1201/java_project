package com.recipeplatform.recipePlatform.Controller;

import org.springframework.web.bind.annotation.*;

import com.recipeplatform.recipePlatform.Service.LikeService;

@RestController
@RequestMapping("/api/likes")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping("/{recipeId}/{userId}")
    public String likeRecipe(@PathVariable Long recipeId, @PathVariable Long userId) {
        
            likeService.likeRecipe(recipeId, userId);
            return "Recipe liked successfully!";
        
    }
    
    @GetMapping("/{recipeId}")
    public String likecount(@PathVariable Long recipeId)
    {
    	return "Recipe has "+likeService.Likecount(recipeId)+" Like!";
    }
    
    
    @DeleteMapping("/{recipeId}/{userId}")
    public String unlikeRecipe(@PathVariable Long recipeId, @PathVariable Long userId) {
       
            likeService.unlikeRecipe(recipeId, userId);
            return "Recipe unliked successfully!";
        
    }
}
