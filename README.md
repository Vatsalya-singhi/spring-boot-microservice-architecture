# Spring Boot Microservice Architecture

This repository contains an implementation of a **Spring Boot Microservices Architecture**, based on a tutorial for building and orchestrating microservices using Spring Boot. It includes the following key components:

- **User Service** - Handles user-related operations.
- **Department Service** - Manages department-related data.
- **API Gateway** - Acts as a single entry point to route requests to the appropriate services.
- **Service Registry (Eureka Server)** - Registers and discovers microservices.
- **Circuit Breaker (Hystrix Dashboard)** - Ensures resilience by handling failures gracefully.
- **Distributed Tracing (Zipkin & Sleuth)** - Tracks and monitors inter-service communication.

## Prerequisites

Before running the services, ensure you have the following installed:

- **Java 17**
- **Spring Boot 3+**
- **Maven**
- **Docker (optional for running Zipkin)**
- **Postman (for API testing - Postman collection is included in the repo)**

## Running the Services

Each microservice must be started in a specific order to ensure proper communication between them.

### 1. Clone the Repository
```sh
git clone https://github.com/Vatsalya-singhi/spring-boot-microservice-architecture.git
cd spring-boot-microservice-architecture
```

### 2. Start the Service Registry (Eureka Server)
```sh
cd service-registry
mvn spring-boot:run
```
Access Eureka Dashboard at: [http://localhost:8761](http://localhost:8761)

### 3. Start the Department Service
```sh
cd ../department-service
mvn spring-boot:run
```
Runs on: `http://localhost:8081`

### 4. Start the User Service
```sh
cd ../user-service
mvn spring-boot:run
```
Runs on: `http://localhost:8082`

### 5. Start the API Gateway
```sh
cd ../api-gateway
mvn spring-boot:run
```
Runs on: `http://localhost:9191`

### 6. Start the Hystrix Dashboard (Circuit Breaker Monitoring)
```sh
cd ../hystrix-dashboard
mvn spring-boot:run
```
Access Hystrix Dashboard at: [http://localhost:9295/hystrix](http://localhost:9295/hystrix)

### 7. Start Zipkin for Distributed Tracing
If you have Docker installed, run Zipkin using:
```sh
docker run -d -p 9411:9411 openzipkin/zipkin
```
Or, if running locally, download and start Zipkin:
```sh
wget -O zipkin.jar https://zipkin.io/quickstart.zipkin.jar
java -jar zipkin.jar
```
Access Zipkin UI at: [http://localhost:9411](http://localhost:9411)

## Testing the Application

### Using Postman
A **Postman collection** is provided in the repository to test API endpoints. Import it into Postman and make API calls through the API Gateway (`http://localhost:9191`).

### Example API Calls
#### Create a Department:
```sh
POST http://localhost:9191/departments
Content-Type: application/json
{
    "departmentName": "Engineering",
    "departmentAddress": "123 Tech Street",
    "departmentCode": "ENG001"
}
```

#### Create a User:
```sh
POST http://localhost:9191/users
Content-Type: application/json
{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@example.com",
    "departmentId": 1
}
```

#### Retrieve a User by ID:
```sh
GET http://localhost:9191/users/1
```

## Features Implemented
- **Microservice Architecture** with multiple independent services.
- **Service Discovery** using Eureka Server.
- **API Gateway** for routing requests.
- **Circuit Breaker** for fault tolerance with Hystrix.
- **Logging & Tracing** using Sleuth and Zipkin.
- **Centralized API Testing** via the provided Postman collection.

## License
This project is open-source and available under the [MIT License](LICENSE).

---

Enjoy coding! 🚀 If you find this project useful, feel free to give it a ⭐ on GitHub!
