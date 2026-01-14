

# 🍽️ Recipe Sharing Platform (Backend)

A **secure, RESTful backend application** built using **Spring Boot** that enables users to manage recipes, interact via likes & comments, upgrade to premium using **Razorpay payments**, and participate in **admin-created events**.

The project follows **clean layered architecture**, enforces **role-based access control**, and is designed as an **API-first system**, tested using **Postman**.

---

## 🚀 Key Features

### 🔐 Authentication & Authorization

* JDBC-based authentication using **Spring Security**
* Password encryption with **BCrypt**
* Role-based access:

  * **ROLE_USER**
  * **ROLE_ADMIN**
* Endpoint-level authorization using HTTP methods
* HTTP Basic authentication (used for API testing & payment flow)

---

### 👤 User Management

* User registration
* Fetch user details by ID or username
* Admin-only user deletion
* Secure access to user data based on role
* Upgrade normal users to **premium users**

---

### 📖 Recipe Management

* Create recipes **only for the authenticated user**
* View single or all recipes
* Search recipes by keyword
* Update recipes (only by owner)
* Delete recipes (only by owner)

---

### ❤️ Likes System

* Users can like/unlike recipes
* Check if a user has liked a recipe
* Get like count per recipe
* Protected endpoints to prevent unauthorized actions

---

### 💬 Comments System

* Add comments to recipes
* Fetch all comments for a recipe
* Delete comments (by owner)
* Maintains proper entity relationships

---

### 💳 Premium Membership & Payments

* Integrated **Razorpay payment gateway**
* Create payment orders
* Verify payments using Razorpay signature
* Automatically upgrade user to **premium** on successful payment
* Prevent duplicate premium upgrades

---

### 📅 Events Management

* **Admins** can create events
* **Premium users** can register for events
* View individual events or all events
* Secure role-based event access

---

## ⚙️ Tech Stack

### Backend

* Java
* Spring Boot
* Spring Framework
* Spring Security
* Spring Data JPA
* Hibernate

### Database

* MySQL

### Tools & Platforms

* Git & GitHub
* Postman
* JDBC Authentication
* BCrypt Password Encoder
* Razorpay Payment Gateway

---

## 🧱 Architecture

The application follows a **layered backend architecture**:

```
Controller → Service → Repository → Database
```

* **Controller Layer** – REST API endpoints
* **Service Layer** – Business logic & validations
* **Repository Layer** – Database operations using JPA
* **Security Layer** – Authentication & authorization rules

---

## 🔐 Security Configuration Highlights

* `JdbcUserDetailsManager` for authentication
* Custom SQL queries for users & roles
* Role-based endpoint protection
* CSRF disabled for REST APIs
* Method-level access control using HTTP verbs

---

## 🔗 API Endpoints

### 👤 Users

| Method | Endpoint                            | Access       |
| ------ | ----------------------------------- | ------------ |
| POST   | `/api/users/register`               | Public       |
| GET    | `/api/users/{id}`                   | USER / ADMIN |
| GET    | `/api/users/by-username/{username}` | USER / ADMIN |
| POST   | `/api/users/upgrade`                | USER         |
| DELETE | `/api/users/{id}`                   | ADMIN        |

---

### 📖 Recipes

| Method | Endpoint                           | Description     |
| ------ | ---------------------------------- | --------------- |
| POST   | `/api/recipes/{userId}`            | Create recipe   |
| GET    | `/api/recipes/{id}`                | Get recipe      |
| GET    | `/api/recipes`                     | Get all recipes |
| GET    | `/api/recipes/search?keyword=`     | Search recipes  |
| PUT    | `/api/recipes/{recipeId}/{userId}` | Update recipe   |
| DELETE | `/api/recipes/{recipeId}/{userId}` | Delete recipe   |

---

### ❤️ Likes

| Method | Endpoint                         | Description     |
| ------ | -------------------------------- | --------------- |
| POST   | `/api/likes/{recipeId}/{userId}` | Like recipe     |
| GET    | `/api/likes/{recipeId}`          | Get like count  |
| GET    | `/api/likes/{recipeId}/{userId}` | Check user like |
| DELETE | `/api/likes/{recipeId}/{userId}` | Unlike recipe   |

---

### 💬 Comments

| Method | Endpoint                             | Description    |
| ------ | ------------------------------------ | -------------- |
| POST   | `/api/comments/{recipeId}/{userId}`  | Add comment    |
| GET    | `/api/comments/{recipeId}`           | Get comments   |
| DELETE | `/api/comments/{commentId}/{userId}` | Delete comment |

---

### 📅 Events

| Method | Endpoint                                  | Access       |
| ------ | ----------------------------------------- | ------------ |
| POST   | `/api/events/create/{adminId}`            | ADMIN        |
| POST   | `/api/events/register/{eventId}/{userId}` | USER         |
| GET    | `/api/events/{id}`                        | USER / ADMIN |
| GET    | `/api/events`                             | USER / ADMIN |

---

### 💳 Payments

| Method | Endpoint                        | Description           |
| ------ | ------------------------------- | --------------------- |
| POST   | `/api/payments/create-order`    | Create Razorpay order |
| POST   | `/api/payments/verify`          | Verify payment        |
| GET    | `/api/payments/order/{orderId}` | Get payment details   |

---

## ▶️ How to Run Locally

### Prerequisites

* Java 17+
* MySQL
* Maven

---

### Steps

```bash
# Clone the repository
git clone https://github.com/Archii1201/recipe-sharing-platform.git

# Navigate to project
cd recipe-sharing-platform

# Configure database in application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/recipe_db
spring.datasource.username=your_username
spring.datasource.password=your_password

# Run application
mvn spring-boot:run
```

Server will start at:

```
http://localhost:8080
```

---

## 🗄️ Core Entities

* User
* Recipe
* Like
* Comment
* Event
* Payment

All relationships are managed using **JPA & Hibernate annotations**.

---
## 📘 API Documentation (Swagger)

The project uses **Swagger (OpenAPI)** for API documentation.

🔗 Swagger UI:http://localhost:8080/swagger-ui.html


## 🚀 Future Enhancements

* JWT-based authentication (replace HTTP Basic)
* Pagination & sorting for recipes
* Recipe categories & tags
* Swagger / OpenAPI documentation
* Admin analytics dashboard

---

## 👩‍💻 Author

**Archi Patel**
Backend Developer | Java | Spring Boot

* 🌐 GitHub: [https://github.com/Archii1201](https://github.com/Archii1201)
* 💼 LinkedIn: [https://www.linkedin.com/in/archi-patel-765aa2350/](https://www.linkedin.com/in/archi-patel-765aa2350/)

---

⭐ *This project demonstrates real-world backend development, secure role-based access control, payment integration, and scalable REST API design.*

---

