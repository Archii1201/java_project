package com.recipeplatform.recipePlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.recipeplatform.recipePlatform.entity.Like;

import java.util.Optional;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long> {
	
    Optional<Like> findByUserIdAndRecipeId(Long userId, Long recipeId);
    
    boolean existsByUserIdAndRecipeId(Long userId, Long recipeId);
    
    @Query("SELECT COUNT(l) FROM Like l WHERE l.recipe.id = :recipeId")
    Long countLikesByRecipeId(@Param("recipeId") Long recipeId);
}

