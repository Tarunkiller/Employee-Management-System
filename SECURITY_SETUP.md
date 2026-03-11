# Spring Security Setup Guide

## Overview
Your Employee Management System now has complete **JWT-based Spring Security** implementation with role-based access control (RBAC).

## ✅ Security Features Implemented

### 1. **JWT Authentication**
- Token-based authentication using **JJWT** library
- Tokens valid for 7 days (604,800,000 milliseconds)
- Secure token signing with HMAC-SHA algorithm

### 2. **Role-Based Access Control (RBAC)**
- **USER Role**: Can view employees
- **ADMIN Role**: Can create, update, and delete employees
- Configurable permission by HTTP method

### 3. **CORS Configuration**
- Enabled for frontend development on localhost:3000, :4200, :8080
- Supports credentials in requests
- Configurable headers and methods

### 4. **Security Endpoints**

#### **Public Endpoints (No Authentication Required)**
```
POST /api/auth/register - User registration
POST /api/auth/login    - User login
```

#### **Protected Endpoints (JWT Required)**
```
GET    /api/employees          - List all employees (USER, ADMIN)
GET    /api/employees/{id}     - Get employee by ID (USER, ADMIN)
POST   /api/employees          - Create employee (ADMIN only)
PUT    /api/employees/{id}     - Update employee (ADMIN only)
DELETE /api/employees/{id}     - Delete employee (ADMIN only)
```

---

## 🔒 How to Use

### Step 1: Register a User
```bash
POST http://localhost:8081/api/auth/register
Content-Type: application/json

{
  "name": "John Doe",
  "email": "john@example.com",
  "password": "SecurePass123!"
}
```

**Password Requirements:**
- Minimum 8 characters
- Must contain uppercase letter (A-Z)
- Must contain lowercase letter (a-z)
- Must contain digit (0-9)
- Must contain special character (@$!%*?&)

**Example password:** `Secure@Pass123`

**Response (Success):**
```json
{
  "success": true,
  "message": "User registered successfully!",
  "statusCode": 201
}
```

---

### Step 2: Login
```bash
POST http://localhost:8081/api/auth/login
Content-Type: application/json

{
  "email": "john@example.com",
  "password": "SecurePass123!"
}
```

**Response (Success):**
```json
{
  "success": true,
  "message": "Login successful",
  "data": {
    "accessToken": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwiaWF0IjoxNjI2NDI1MzAwLCJleHAiOjE2MjcwMzAxMDB9.signature",
    "tokenType": "Bearer"
  },
  "statusCode": 200
}
```

---

### Step 3: Access Protected Endpoints
Add JWT token to request header:

```bash
GET http://localhost:8081/api/employees
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJqb2huQGV4YW1wbGUuY29tIiwiaWF0IjoxNjI2NDI1MzAwLCJleHAiOjE2MjcwMzAxMDB9.signature
Content-Type: application/json
```

---

## 🔐 Configuration Files

### `application.properties`
```properties
# JWT Configuration
app.jwt-secret=daf66e01593f61a15b857cf433aae03a005812b31234e149036bcc8dee755db1
app.jwt-expiration-milliseconds=604800000

# Server
server.port=8081
```

**Note:** In production, use a strong secret key:
```bash
# Generate a secure key (Linux/Mac):
openssl rand -hex 32

# Or generate in Java:
import io.jsonwebtoken.security.Keys;
String key = Encoders.BASE64.encode(Keys.secretKeyFor(SignatureAlgorithm.HS512).getEncoded());
```

---

## 📝 Security Components

### 1. **SecurityConfig.java**
- Configures HTTP security rules
- Sets up CORS
- Configures JWT filter
- Defines role-based authorization

### 2. **JwtTokenProvider.java**
- Generates JWT tokens
- Validates tokens
- Extracts username from tokens
- Handles token expiration

### 3. **JwtAuthenticationFilter.java**
- Intercepts requests
- Validates JWT tokens
- Converts token to Authentication object
- Sets SecurityContext

### 4. **JwtAuthenticationEntryPoint.java**
- Handles authentication exceptions
- Returns 401 Unauthorized for invalid/missing tokens

### 5. **CustomUserDetailsService.java**
- Loads user details from database
- Maps roles to authorities
- Used by Spring Security for authentication

---

## 🛡️ Exception Handling

The application includes comprehensive error handling with standardized responses:

```json
{
  "success": false,
  "message": "Email is already taken!",
  "statusCode": 400
}
```

### Common Error Codes:
- **400 Bad Request** - Validation failed
- **401 Unauthorized** - Invalid/missing JWT token
- **404 Not Found** - Resource not found
- **500 Internal Server Error** - Server error

---

## 📊 Testing Tools

### Using Postman
1. Create a new Postman collection
2. Register endpoint: `POST /api/auth/register`
3. Login endpoint: `POST /api/auth/login`
4. Copy the `accessToken` from login response
5. In other requests, go to **Authorization** tab
6. Select **Bearer Token**
7. Paste the token

### Using cURL
```bash
# Register
curl -X POST http://localhost:8081/api/auth/register \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@example.com","password":"SecurePass123!"}'

# Login
curl -X POST http://localhost:8081/api/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"john@example.com","password":"SecurePass123!"}'

# Get Employees (with token)
curl -X GET http://localhost:8081/api/employees \
  -H "Authorization: Bearer YOUR_TOKEN"
```

---

## 🚀 Production Checklist

- [ ] Change JWT secret key to a strong random value
- [ ] Change CORS allowed origins to your production domain
- [ ] Enable HTTPS only
- [ ] Set `csrf.disable()` to false for stateful implementations
- [ ] Implement token refresh mechanism
- [ ] Add rate limiting for login endpoint
- [ ] Enable logging in application properties
- [ ] Set up monitoring and alerting
- [ ] Implement audit logging
- [ ] Add WAF (Web Application Firewall) rules

---

## 📚 Additional Resources

- [Spring Security Documentation](https://spring.io/projects/spring-security)
- [JJWT Library](https://github.com/jwtk/jjwt)
- [JWT.io](https://jwt.io/)
- [OWASP Authentication Cheat Sheet](https://cheatsheetseries.owasp.org/cheatsheets/Authentication_Cheat_Sheet.html)

---

## 🐛 Troubleshooting

### Issue: "Invalid JWT token"
- Check token expiration (7 days)
- Verify token format: `Bearer <token>`
- Ensure JWT secret matches in config

### Issue: "User not found"
- Verify email is correctly registered
- Check database table `users` for the user
- Verify case-sensitivity of email

### Issue: "Access Denied"
- Check user role in `user_roles` table
- Verify role starts with `ROLE_` prefix (e.g., `ROLE_USER`)
- Confirm permission for the HTTP method

---

**Last Updated:** 2026-03-11
