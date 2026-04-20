# Week 11 - Express Student CRUD API with JWT Authentication

## Aim
Develop an Express web application that implements JWT (JSON Web Token) authentication for securing CRUD operations on student data through a REST API.

## Theory: JWT Authentication

### What is JWT?
JSON Web Token (JWT) is an open standard (RFC 7519) that defines a compact and self-contained way for securely transmitting information between parties as a JSON object. This information can be verified and trusted because it is digitally signed.

### How JWT Works
1. **Authentication**: User provides credentials (username/password)
2. **Token Generation**: Server validates credentials and generates a JWT token
3. **Token Storage**: Client stores the token (usually in localStorage or cookies)
4. **Authorization**: Client sends token in Authorization header for subsequent requests
5. **Verification**: Server verifies the token before allowing access to protected resources

### JWT Structure
A JWT consists of three parts separated by dots:
- **Header**: Contains token type and signing algorithm
- **Payload**: Contains claims (user information, expiration, etc.)
- **Signature**: Used to verify the token hasn't been altered

### Advantages of JWT
- Stateless authentication (no server-side session storage needed)
- Self-contained (contains all necessary information)
- Can be used across different domains
- Secure (digitally signed)
- Compact and URL-safe

## Project Structure
```text
week-11/
|-- package.json
|-- server.js
|-- README.md
```

## Step 1: Open terminal in `week-11`
```powershell
cd "c:\Users\VEERAMOSU\Documents\WAD Practice\week-11"
```

## Step 2: Install dependencies
```powershell
npm install
```

## Step 3: Run the server
```powershell
npm start
```

Server URL:
```text
http://localhost:3000
```

## API Endpoints

### Authentication

#### 1. Login (Get JWT Token)
- Method: `POST`
- URL: `http://localhost:3000/login`
- Body: `raw` -> `JSON`
```json
{
  "username": "admin",
  "password": "password123"
}
```
- Response:
```json
{
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "user": {
    "id": 1,
    "username": "admin"
  }
}
```

### Protected CRUD Endpoints
**Note**: All CRUD endpoints require JWT authentication. Include the token in the Authorization header:
```
Authorization: Bearer YOUR_JWT_TOKEN
```

#### 2. Get all students
- Method: `GET`
- URL: `http://localhost:3000/students`
- Headers: `Authorization: Bearer YOUR_JWT_TOKEN`

#### 3. Get student by ID
- Method: `GET`
- URL: `http://localhost:3000/students/101`
- Headers: `Authorization: Bearer YOUR_JWT_TOKEN`

#### 4. Add a new student
- Method: `POST`
- URL: `http://localhost:3000/students`
- Headers: `Authorization: Bearer YOUR_JWT_TOKEN`
- Body: `raw` -> `JSON`
```json
{
  "name": "Kiran",
  "age": 22,
  "branch": "IT"
}
```

#### 5. Update a student
- Method: `PUT`
- URL: `http://localhost:3000/students/101`
- Headers: `Authorization: Bearer YOUR_JWT_TOKEN`
- Body: `raw` -> `JSON`
```json
{
  "name": "Anita Sharma",
  "age": 21,
  "branch": "CSE"
}
```

#### 6. Delete a student
- Method: `DELETE`
- URL: `http://localhost:3000/students/102`
- Headers: `Authorization: Bearer YOUR_JWT_TOKEN`

## Postman Testing Steps

### Step 1: Login to get JWT token
1. Create a new request in Postman
2. Method: `POST`
3. URL: `http://localhost:3000/login`
4. Go to `Body` -> `raw` -> choose `JSON`
5. Enter credentials:
```json
{
  "username": "admin",
  "password": "password123"
}
```
6. Click `Send`
7. Copy the token from the response

### Step 2: Test protected endpoints
1. Create a new request
2. Select the method (`GET`, `POST`, `PUT`, `DELETE`)
3. Enter the URL
4. Go to `Headers` tab
5. Add header: `Authorization` with value `Bearer YOUR_COPIED_TOKEN`
6. For `POST` and `PUT`, go to `Body` -> `raw` -> choose `JSON` and enter the data
7. Click `Send`
8. Check the JSON response

## Expected Output Examples

### Login Response
```json
{
  "message": "Login successful",
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJhZG1pbiIsImlhdCI6MTY0MjM5MzYwMCwiZXhwIjoxNjQyMzk3MjAwfQ.example_signature",
  "user": {
    "id": 1,
    "username": "admin"
  }
}
```

### GET `/students` (Protected)
```json
[
  {
    "id": 101,
    "name": "Alice",
    "age": 21,
    "branch": "CSE"
  },
  {
    "id": 102,
    "name": "Bob",
    "age": 20,
    "branch": "IT"
  }
]
```

### POST `/students` (Protected)
```json
{
  "message": "Student Added Successfully",
  "newStudent": {
    "id": 103,
    "name": "Kiran",
    "age": 22,
    "branch": "IT"
  }
}
```

## Error Responses

### Unauthorized (No Token)
```json
{
  "message": "Access token required"
}
```

### Invalid Token
```json
{
  "message": "Invalid or expired token"
}
```

### Invalid Credentials
```json
{
  "message": "Invalid credentials"
}
```

## Security Notes
- JWT tokens expire after 1 hour
- Store tokens securely (not in localStorage in production)
- Use HTTPS in production
- Never store sensitive information in JWT payload
- Use strong, unique secret keys
- Implement refresh token mechanism for better security

## Note
- Student data is stored in memory, so it resets every time the server restarts.
- This is suitable for lab/demo purposes and Postman testing.
- In production, use proper database storage and environment variables for secrets.
  "age": 22,
  "course": "IT"
}
```

### 4. Update a student
- Method: `PUT`
- URL: `http://localhost:3000/students/1`
- Body: `raw` -> `JSON`
```json
{
  "name": "Anita Sharma",
  "age": 21,
  "course": "CSE"
}
```

### 5. Delete a student
- Method: `DELETE`
- URL: `http://localhost:3000/students/2`

## Postman Testing Steps
1. Open Postman.
2. Create a new request.
3. Select the method (`GET`, `POST`, `PUT`, `DELETE`).
4. Enter the URL.
5. For `POST` and `PUT`, go to `Body` -> `raw` -> choose `JSON`.
6. Click `Send`.
7. Check the JSON response.

## Expected Output Examples

### GET `/students`
```json
[
  {
    "id": 1,
    "name": "Anita",
    "age": 20,
    "course": "CSE"
  },
  {
    "id": 2,
    "name": "Rahul",
    "age": 21,
    "course": "ECE"
  }
]
```

### POST `/students`
```json
{
  "message": "Student added successfully",
  "student": {
    "id": 3,
    "name": "Kiran",
    "age": 22,
    "course": "IT"
  }
}
```

## Note
- Student data is stored in memory, so it resets every time the server restarts.
- This is suitable for lab/demo purposes and Postman testing.