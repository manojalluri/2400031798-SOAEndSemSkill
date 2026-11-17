# JWT Validation - Spring Boot

A small Spring Boot 3 / Java 17 example that generates a demo JWT, validates it, and protects an API.

## Run

1. Install Java 17+ and Maven.
2. Open this folder in IntelliJ IDEA / Eclipse, or run:
   ```bash
   mvn spring-boot:run
   ```
3. Server runs at `http://localhost:8001`.

## Test in Postman or a REST client

### 1. Generate a token
- Method: `POST`
- URL: `http://localhost:8001/api/auth/token`
- Header: `Content-Type: application/json`
- Body (raw JSON):
  ```json
  { "username": "manoj" }
  ```
Copy the `token` value from the response.

### 2. Validate the token
- Method: `GET`
- URL: `http://localhost:8001/api/auth/validate`
- Header:
  `Authorization: Bearer YOUR_TOKEN_HERE`

A valid token returns HTTP 200 and `valid: true`. An invalid, expired, or missing token returns HTTP 401.

### 3. Call a protected endpoint
- Method: `GET`
- URL: `http://localhost:8001/api/protected`
- Header:
  `Authorization: Bearer YOUR_TOKEN_HERE`

Without a valid token, Spring Security returns HTTP 401.

## Important

- `/api/auth/token` is a demonstration endpoint and accepts a username without checking a password. Do not use it as production login. In a real application, authenticate the user first and generate the JWT only after successful authentication.
- Replace `jwt.secret` with a long random secret and store it in an environment variable or secret manager. Never commit real secrets.
- This is a standalone example. To add it to an existing project, merge the dependencies, `JwtService`, filter, and security configuration, then adjust package names and endpoint rules. Avoid replacing your existing security configuration without reviewing it.
