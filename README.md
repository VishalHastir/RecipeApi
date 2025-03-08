# ABNAmro-Assessment: Recipe Application API

Recipe Application contains REST APIs which contains Create, Update Get, Delete recipes from the database and send JSON response to user.

### Design
Recipe Application is based on microservices architecture. This module can be deployed independently on premise server, any public cloud and on docker containers. There are 4 layers from top to bottom:
- Controller Layer
- Service Layer
- Entity Layer
- Repository Layer

## Setup guide

### Minimum Requirements

- Java 21
- Maven 3.x

### Steps to build application
* Download code zip / `git clone https://github.com/VishalHastir/RecipeApi.git
* Run maven build command `mvn clean install`
* To build by skipping unit tests run maven command `mvn clean install -DskipTests`
* On successfully build completion, one should have jar in `target` directory named as `recipe-1.0.0-SNAPSHOT.jar`.

## **Running the Application**

Navigate to the `app` module and start the Spring Boot application:

```sh
cd app
mvn spring-boot:run
```

The application will now be running at:  
👉 **http://localhost:8000**

### Steps to execute Web Service
* **Execution with Embedded H2 Database**
  - In Development Mode, by default web service uses [Embedded H2 database] for persisting and retrieving recipes details.
  - On successfull start, there will be a log message on console `Tomcat started on port(s): 8000 (http)` and have web service listening for web requests at port 8000.
  - Embedded H2 database will be accessed by using http://localhost:8000/h2-console with username `sa` and no password.
  
* **Usage of all endpoints**
  - Open the swagger-ui with http://localhost:8000/swagger-ui/index.html to check all the API documentation and to perform opreations.

