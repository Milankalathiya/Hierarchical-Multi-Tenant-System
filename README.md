# Spring Boot Hierarchical Multi-Tenant System

A simple and easy-to-understand multi-tenant backend built with **Spring Boot**, **PostgreSQL**, and **JWT authentication**. This project supports **hierarchical user roles** and **secure, role-based access** to products, suppliers, and sales data.

---

## 🔑 Features

- **User Registration & Login** (JWT token-based authentication)
- **Hierarchical User Roles**:
  - `SUPER_USER` → `SUB_USER` → `USER` (parent-child relationships)
- **Secure Endpoints**:
  - Users can only access or create data within their own branch (themselves and their descendants)
- **CRUD Operations** for:
  - `Products`
  - `Suppliers`
  - `Sales`
- **PostgreSQL** for data storage
- **BCrypt** password encryption

---

## 🔧 How It Works

1. Register or login to receive a **JWT token**.
2. Use the token in all requests via the header:
Authorization: Bearer <your_token>

yaml
Copy
Edit
3. Access control is enforced in the backend:
- Users can only see or manage their own data and that of their children (recursive).

---

## 📦 Example Endpoints

| Method | Endpoint                         | Description                        |
|--------|----------------------------------|------------------------------------|
| POST   | `/api/users/register`            | Register a new user                |
| POST   | `/api/users/login`               | Login and get a JWT token          |
| GET    | `/api/products?userId=2`         | Get products for user ID 2         |
| POST   | `/api/sales`                     | Create a sale (if allowed)         |

---

## 🚀 Quick Start

1. **Clone the repository**:
```bash
git clone https://github.com/yourusername/hierarchical-multi-tenant-system.git
cd hierarchical-multi-tenant-system
Configure the database:

Set your PostgreSQL credentials in src/main/resources/application.properties.

Run the application:

mvn spring-boot:run
Test the API using tools like Postman:

Register

Login

Perform CRUD operations based on roles

🛠️ Tech Stack
Java 21

Spring Boot

Spring Security (JWT)

PostgreSQL

Maven

🪪 License
This project is licensed under the MIT License.

🙌 Contributing
Contributions are welcome! Please fork the repository and open a pull request with your improvements or fixes.
