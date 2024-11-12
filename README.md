# Women-in-STEM-Matching-Platform Platform

## Overview
The Women-in-STEM-Matching-PlatformPlatform is a web application designed to connect mentors and mentees in the STEM fields. The platform enables users to register, create profiles, and match with mentors or mentees based on their expertise and career goals. Additionally, users can communicate through a messaging system to foster meaningful mentorship connections.

## Table of Contents
1. [MVP Features](#mvp-features)
    - [User Registration & Authentication](#user-registration--authentication)
    - [Profile Creation](#profile-creation)
    - [Matching Algorithm](#matching-algorithm)
    - [Messaging System](#messaging-system)
2. [Nice-to-Have Features](#nice-to-have-features)
    - [Analytics for Mentorship Connections](#analytics-for-mentorship-connections)
    - [Notifications](#notifications)
3. [Technologies Used](#technologies-used)
4. [Installation and Setup](#installation-and-setup)
5. [Usage](#usage)
6. [API Endpoints](#api-endpoints)

---

## MVP Features

### User Registration & Authentication
- **Functionality**: Allows users to securely register and log in to the platform.
- **Implementation**:
    - Utilizes **Spring Security** for authentication.
    - Passwords are encrypted with `BCryptPasswordEncoder` to ensure data security.
    - JWT (JSON Web Tokens) are used to manage user sessions securely.
- **Endpoints**:
    - `POST /auth/register`: Register a new user.
    - `POST /auth/login`: Log in an existing user and receive a JWT token.

### Profile Creation
- **Functionality**: Enables users to create detailed profiles as either mentors or mentees.
- **Implementation**:
    - Users specify their **role** (`MENTOR` or `MENTEE`) and **area of expertise** (e.g., Data Science, Software Engineering).
    - Profiles can be retrieved and updated as needed.
- **Endpoints**:
    - `GET /profile/{username}`: Retrieve a user’s profile.
    - `PUT /profile/{username}`: Update a user’s profile information.

### Matching Algorithm
- **Functionality**: Matches mentors and mentees based on shared career interests and expertise.
- **Implementation**:
    - Uses a simple matching algorithm that pairs users with complementary roles and similar areas of expertise.
    - The algorithm queries users with opposite roles (mentor/mentee) and filters them by expertise.
- **Endpoints**:
    - `POST /match/find`: Retrieves potential matches based on the user's preferences.

### Messaging System
- **Functionality**: Provides a messaging system for mentors and mentees to communicate.
- **Implementation**:
    - Each message records the sender ID, receiver ID, content, and timestamp.
    - Messages are stored in the database and can be retrieved in a conversation history.
- **Endpoints**:
    - `POST /messages/send`: Send a message to a matched user.
    - `GET /messages/conversation`: Retrieve the message history between two users.

---

## Nice-to-Have Features

### Analytics for Mentorship Connections
- **Functionality**: Provides analytics on mentorship engagement and connections.
- **Implementation**:
    - Tracks the total number of mentor-mentee connections and message exchanges.
    - Basic analytics can be visualized through frontend charts (if added) or returned as numerical data.
- **Endpoints**:
    - `GET /analytics/connections`: Retrieves the total number of mentor-mentee connections.
    - `GET /analytics/messages`: Retrieves the total number of messages exchanged across the platform.

### Notifications
- **Functionality**: Notifies users of new messages, matches, or other important updates.
- **Implementation**:
    - Notifications are stored in the database and can be retrieved for each user.
    - Notifications have types (e.g., "MESSAGE" or "MATCH") and include content for display.
- **Endpoints**:
    - `GET /notifications/{userId}/unread`: Retrieves all unread notifications for a user.
    - `PUT /notifications/{notificationId}/markAsRead`: Marks a notification as read.

---

## Technologies Used
- **Backend**: Java, Spring Boot, Spring Security
- **Database**: MySQL (for storing user profiles, messages, and notifications)
- **Caching**: Caffeine (for optimized user lookups)
- **Token Management**: JWT (for session management)

---

## Installation and Setup

1. **Clone the Repository**
   ```bash
   git clone https://github.com/ChristianaKang11/Women-in-STEM-Matching-Platform.git
   cd Women-in-STEM-Matching-Platform
   ```
2. **Configure the Database**
Set up a MySQL database 
Update src/main/resources/application.properties with  MySQL credentials:
    ```bash
   spring.datasource.url=jdbc:mysql://localhost:3306/mentor_match
    spring.datasource.username=your_db_username
    spring.datasource.password=your_db_password
   ```
Build and run the application using Maven:
   ```bash
   git clone https://github.com/ChristianaKang11/Women-in-STEM-Matching-Platform.git
   cd Women-in-STEM-Matching-Platform
   ```
The application will be accessible at http://localhost:8080.

# API Usage

## Register a User

Use `POST /auth/register` to register a new mentor or mentee.

### Example JSON body:
```json
{
  "username": "john_doe",
  "password": "securepassword",
  "role": "MENTOR",
  "expertise": "Software Engineering"
}
```

## Login to Obtain JWT Token

Use `POST /auth/login` to log in and receive a JWT token.  
Attach the token to the `Authorization` header as `Bearer <token>` for subsequent requests.

## Create or Update Profile

Update profile details (e.g., role and expertise) with `PUT /profile/{username}`.

## Find Matches

Use `POST /match/find` to retrieve potential mentor-mentee matches based on your profile.

## Send and Retrieve Messages

- Send messages with `POST /messages/send`.
- Retrieve conversation history with `GET /messages/conversation`.
