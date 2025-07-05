package com.recipeplatform.recipePlatform.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id", nullable = false)
    private Recipe recipe;

	public Like() {
		super();
	}

	public Like(User user, Recipe recipe) {
		super();
		this.user = user;
		this.recipe = recipe;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Recipe getRecipe() {
		return recipe;
	}

	public void setRecipe(Recipe recipe) {
		this.recipe = recipe;
	}

	@Override
	public String toString() {
		return "Like [id=" + id + ", user=" + user + ", recipe=" + recipe + "]";
	}
	
   
}
