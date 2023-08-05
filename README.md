# Foruma - Microservices Forum Application

Foruma is a microservices-based forum application similar to Reddit but without subreddits. It allows users to create, edit, and delete posts and comments. Users can also like and save posts.

## Technology Stack

- Java Spring Boot, Security, MVC, OpenFeign, Gateway
- Docker (Containerization)
- PostgreSQL (Database)
- JWT (Authentication)
- Eureka (Service Discovery)

## Microservices

1. Auth: Handles user management, JWT configuration and services, and authentication configuration and services with Security configuration.
2. Post: Responsible for post management, including creating, retrieving, updating, and deleting posts. It also handles like and save interactions for posts.
3. Comment: Manages comments for posts, providing functionalities for creating, retrieving, updating, and deleting comments. It also handles like and save interactions for comments.
4. Interaction: Contains functionalities for handling like and save interactions used by other microservices internally.
5. Apigw: Acts as the API Gateway for routing requests to appropriate microservices.
6. Eureka-server: Provides service discovery capabilities for the microservices.

## Getting Started

### Prerequisites

- Java 17
- Docker (for environment setup)

### Setup and Installation

1. Clone this repository to your local machine.
2. Ensure you have Docker installed and running.
3. Use Docker Compose to start the services
4. Once the services are up and running, you can access the application at http://localhost:8080 (API Gateway).

## API Endpoints

### Auth Microservice

- `POST /api/auth/register`: User registration.
- `POST /api/auth/authenticate`: User login and authentication.

### Post Microservice

- `GET /api/posts`: Retrieve all posts the authenticated user has created.
- `GET /api/posts/{id}`: Retrieve data for a specific post by ID.
- `POST /api/posts`: Create a new post.
- `DELETE /api/posts/{id}`: Delete a specific post.
- `GET /api/posts/{id}/like`: Like or unlike a post depending on user interaction.
- `GET /api/posts/{id}/save`: Save or unsave a post depending on user interaction.

### Comment Microservice

- `GET /api/comments`: Retrieve all comments the authenticated user has created.
- `GET /api/comments/{id}`: Retrieve data for a specific comment by ID.
- `POST /api/comments`: Create a new comment.
- `DELETE /api/comments/{id}`: Delete a specific comment.
- `GET /api/comments/{id}/like`: Like or unlike a comment depending on user interaction.
- `GET /api/comments/{id}/save`: Save or unsave a comment depending on user interaction.

## Authentication and Authorization

The application uses JWT for authentication. Upon successful login, a JSON Web Token is issued, allowing users to access protected endpoints.

Authorization is based on user roles represented by a Role enum. Certain functionalities are restricted based on the user's role.

## Known Limitations

- The application currently lacks the ability to select and display personalized posts for users, similar to a "for you" or main page.

## Error Handling

Error scenarios and unexpected behavior are handled through appropriate HTTP status codes and error messages.

## Contribution

Contributions to the project are welcome! Please follow the guidelines in the CONTRIBUTING.md file.

## Future Enhancements

In future versions, the project is planned to implement Kubernetes for deployment and scalability.

---

Thank you for checking out Foruma! We hope you find it useful and enjoy exploring the features. If you have any questions or feedback, feel free to contact us.

