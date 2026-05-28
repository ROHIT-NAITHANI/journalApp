# 📔 JournalApp

A RESTful Journal application built with **Spring Boot** and **MongoDB**, featuring Spring Security with HTTP Basic Auth and role-based access control.

---

## 🛠️ Tech Stack

- Java + Spring Boot
- Spring Security (HTTP Basic Auth)
- MongoDB (via Spring Data MongoDB)
- SonarCloud (code quality & static analysis)
- Maven

---

## 📁 Project Structure

```
src/main/java/net/engineeringdigest/journalApp/
├── Controller/
│   ├── AdminController.java         # Admin-only endpoints
│   ├── JournalEntryControllerV2.java # Journal CRUD (authenticated users)
│   ├── PublicController.java        # Public endpoints (register, health)
│   └── UserEntryController.java     # User account management
├── config/
│   └── SpringSecurity.java          # Security configuration
├── entity/
│   ├── JournalEntry.java
│   └── User.java
├── repository/
│   ├── JournalEntryRepo.java
│   └── UserRepo.java
└── service/
    ├── JournalEntryService.java
    └── UserService.java
```

---

## 🔐 Authentication & Roles

The app uses **Spring Security with HTTP Basic Auth**. Credentials are sent as a Base64-encoded `Authorization` header on each request. Two roles are supported:

| Role    | Access                                      |
|---------|---------------------------------------------|
| `USER`  | Manage own journal entries and account      |
| `ADMIN` | View all users, create admin accounts       |

---

## 🚀 API Endpoints

### Public — `/public` *(No auth required)*

| Method | Endpoint             | Description           |
|--------|----------------------|-----------------------|
| POST   | `/public/create-user`| Register a new user   |
| GET    | `/public/Health`     | Health check          |

---

### Journal — `/journal` *(Requires USER role)*

| Method | Endpoint            | Description                        |
|--------|---------------------|------------------------------------|
| GET    | `/journal`          | Get all journal entries for the logged-in user |
| POST   | `/journal`          | Create a new journal entry         |
| GET    | `/journal/id/{id}`  | Get a specific entry by ID         |
| PUT    | `/journal/id/{id}`  | Update a specific entry by ID      |
| DELETE | `/journal/id/{id}`  | Delete a specific entry by ID      |

---

### User — `/user` *(Requires USER role)*

| Method | Endpoint | Description                        |
|--------|----------|------------------------------------|
| PUT    | `/user`  | Update current user's credentials  |
| DELETE | `/user`  | Delete current user's account      |

---

### Admin — `/admin` *(Requires ADMIN role)*

| Method | Endpoint                    | Description              |
|--------|-----------------------------|--------------------------|
| GET    | `/admin/all-users`          | Get list of all users    |
| POST   | `/admin/create-admin-User`  | Create a new admin user  |

---

## ⚙️ Getting Started

### Prerequisites

- Java 17+
- MongoDB running locally on port `27017`
- Maven

### Run the Application

```bash
git clone https://github.com/ROHIT-NAITHANI/journalApp.git
cd journalApp
mvn spring-boot:run
```

The server starts at `http://localhost:8080`.

### Create Your First User

```bash
curl -X POST http://localhost:8080/public/create-user \
  -H "Content-Type: application/json" \
  -d '{"userName": "alice", "password": "secret"}'
```

### Health Check

```bash
curl http://localhost:8080/public/Health
# Response: ok
```

---

## 📝 Example Requests

**Create a journal entry** (authenticated):
```bash
curl -X POST http://localhost:8080/journal \
  -u alice:secret \
  -H "Content-Type: application/json" \
  -d '{"title": "My First Entry", "content": "Today was a great day!"}'
```

**Get all your entries:**
```bash
curl http://localhost:8080/journal -u alice:secret
```

---

## 🗂️ MongoDB Collections

| Collection      | Description                  |
|-----------------|------------------------------|
| `users`         | User accounts and roles      |
| `journal_entries` | Journal entries linked to users |

---

