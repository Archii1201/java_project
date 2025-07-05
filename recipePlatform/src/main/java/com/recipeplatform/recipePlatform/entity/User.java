package com.recipeplatform.recipePlatform.entity;
import jakarta.persistence.*;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    private String bio;
    
    private String role = "ROLE_USER";
    
    
    @Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
    private boolean enabled = true;
	private boolean isPremium=false;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Recipe> recipes;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Comment> comments;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Like> likes;

	public User(String username, String password, String bio, List<Recipe> recipes, List<Comment> comments,
			List<Like> likes) {
		this.username = username;
		this.password = password;
		this.bio = bio;
		this.recipes = recipes;
		this.comments = comments;
		this.likes = likes;
		
	}

	public User(String username, String password, String bio, String role) {
		super();
		this.username = username;
		this.password = password;
		this.bio = bio;
		this.role = role;
	}

	public User() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
	    this.password = password; 
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public List<Recipe> getRecipes() {
		return recipes;
	}

	public void setRecipes(List<Recipe> recipes) {
		this.recipes = recipes;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public List<Like> getLikes() {
		return likes;
	}

	public void setLikes(List<Like> likes) {
		this.likes = likes;
	}



	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", bio=" + bio + "]";
	}



	public void setEnabled(boolean b) {
		this.enabled=b;
	}

	public void setPremium(boolean b) {
		this.isPremium=b;
	}
}
