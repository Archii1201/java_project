package com.recipeplatform.recipePlatform.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.recipeplatform.recipePlatform.entity.Recipe;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.title) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Recipe> searchByTitle(@Param("keyword") String keyword);
    
}
