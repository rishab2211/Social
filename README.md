# 🌐 Social Media Platform — Enterprise Spring Boot Backend

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.x-6DB33F?logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Spring Security](https://img.shields.io/badge/Spring_Security-JWT-6DB33F?logo=springsecurity&logoColor=white)](https://spring.io/projects/spring-security)
[![PostgreSQL](https://img.shields.io/badge/Database-PostgreSQL-316192?logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Hibernate](https://img.shields.io/badge/ORM-Hibernate_JPA-59666C?logo=hibernate&logoColor=white)](https://hibernate.org/)

An enterprise-grade, relational backend engine built with **Java** and **Spring Boot**, engineered to power modern social networking features. The platform exposes robust RESTful APIs for stateless JWT authentication, social graph operations (follow/unfollow), media post publishing, threaded discussions, stories, reels, and peer-to-peer chat sessions.

---

## ✨ Architectural Highlights

- **🔐 Spring Security & JWT:** Configured custom security filter chains with stateless session management and JWT authentication tokens.
- **📊 Relational Social Graph:** Optimized PostgreSQL schemas with foreign-key constraints and indexing strategies designed for bi-directional graph traversals (followers, following, liked posts).
- **🏗️ Domain-Driven Layering:** Clean separation of concerns across Data Transfer Objects (DTOs), JPA Entities, Repository layers, business Services, and REST Controllers.
- **💬 Real-Time Messaging & Chat:** Integrated chat and direct message endpoints supporting threaded message persistence.
- **📱 Multimedia Feeds:** Dedicated subsystems for standard image/video posts, 24-hour stories, and algorithmic reels.

---

## 🛠️ Technology Stack

| Layer | Technologies |
| :--- | :--- |
| **Language & Framework** | Java 17+, Spring Boot (Spring Web, Spring Data JPA) |
| **Security & Auth** | Spring Security, JSON Web Tokens (JJWT), BCrypt Password Hashing |
| **Database** | PostgreSQL, Hibernate ORM |
| **Build Tool** | Apache Maven (`mvnw` wrapper included) |
| **Client Frontend** | React.js & Redux (Repository: [Social-frontend](https://github.com/rishab2211/Social-frontend)) |

---

## 📡 RESTful API Reference

### 1. Authentication (`/auth`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/auth/signup` | Register a new user account |
| `POST` | `/auth/signin` | Authenticate credentials and receive JWT |

### 2. User & Social Graph (`/api/users`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/users/profile` | Retrieve authenticated user profile |
| `GET` | `/api/users/{userId}` | Fetch public profile by user ID |
| `PUT` | `/api/users/update` | Update user metadata and bio |
| `PUT` | `/api/users/follow/{userId2}` | Follow or unfollow a user |
| `GET` | `/api/users/search?query=...` | Search users by name or username |

### 3. Posts & Engagement (`/api/posts`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/posts/user/{userId}` | Create a new multimedia post |
| `GET` | `/api/posts` | Retrieve global chronological post feed |
| `GET` | `/api/posts/{postId}` | Fetch post by unique ID |
| `PUT` | `/api/posts/like/{postId}` | Like or unlike a specific post |
| `PUT` | `/api/posts/save/{postId}` | Bookmark post to saved collection |

### 4. Comments (`/api/comments`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/comments/post/{postId}` | Post a comment on a target post |
| `PUT` | `/api/comments/like/{commentId}` | Like a specific comment |

### 5. Stories & Reels (`/api/story`, `/api/reels`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/story` | Publish an ephemeral story |
| `GET` | `/api/story/user/{userId}` | Fetch stories created by user |
| `POST` | `/api/reels` | Publish short-form video reel |
| `GET` | `/api/reels` | Retrieve reels stream |

### 6. Chat & Messaging (`/api/chats`, `/api/messages`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/chats` | Create or initiate a conversation |
| `GET` | `/api/chats/user` | Fetch active conversations for current user |
| `POST` | `/api/messages/chat/{chatId}` | Send a direct message within a chat |
| `GET` | `/api/messages/chat/{chatId}` | Retrieve chat message history |

---

## 🚀 Quick Start Guide

### Prerequisites
- **Java JDK:** `17` or higher (`java -version`)
- **PostgreSQL:** Running locally or via Docker (`localhost:5432`)
- **Maven:** Bundled via `./mvnw`

### 1. Database Setup
Ensure PostgreSQL is running and create the `social` database:

```sql
CREATE DATABASE social;
```

### 2. Configure Environment Properties
Edit `src/main/resources/application.properties` (or copy from `application.properties.example`):

```properties
spring.application.name=social-app
server.port=8080

spring.datasource.url=jdbc:postgresql://localhost:5432/social
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### 3. Build & Run the Backend
Using the included Maven wrapper:

```bash
# Clean and compile
./mvnw clean install -DskipTests

# Launch the Spring Boot application
./mvnw spring-boot:run
```

The server will initialize and begin listening for API requests on **`http://localhost:8080`**.

---

## 💻 Frontend Application
The companion client application built with **React.js** and **Redux** is available at:
👉 **[rishab2211/Social-frontend](https://github.com/rishab2211/Social-frontend)**

---

## 📄 License

This project is open-source under the [MIT License](LICENSE).
