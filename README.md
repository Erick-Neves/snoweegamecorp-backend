# springboot-security-jwt-backend
*Project template to start REST API development with Spring Security and JWT Token.*

> This project was created with the aim of implementing unit tests in the Spring layers (Repository, Service and Controller), the API validates the credentials of a registered user, and when logging in, a Token is generated to be used in the value of the Authentication header, and when filtering a received request, it validates the user's authorizations for that resource.

You can check the last test run [here](https://erick-neves.github.io/snoweegamecorp-backend/).

**Technologies:**

- JDK 17
- Spring-boot 3.2.4
- Spring-boot Starter WEB
- Spring-boot Starter JPA
- Spring-boot Starter Test
- Spring-boot Starter Security
- Spring Mock MVC
- Hibernate
- H2
- Postgres
- Json Web Token (JWT)
- JUnit 5
- Maven Surefire Reports

**Environment Requirements:**

- *You must have JDK 17 maven installed*

**To execute the project:**

1. To run the spring-boot project, in CMD, navigate to the project folder, and type:

```
mvn spring-boot:run
```

**To test the project:**

​	1. To test the spring-boot project, in CMD, navigate to the project folder, and type:

```
mvn clean test surefire-report:report
```

​	2. The test report file will be in the folder:

```
target/surefire-reports/surefire-report.html
```

**To build the project:**

​	1. To build the spring-boot project, in CMD, navigate to the project folder, and type:

```
mvn clean package -DskipTests
```

​	2. The compiled Java file will be in the folder:

```
target/backend-0.0.1-SNAPSHOT.jar
```

**Evidence of functionality:**

1. **Create a new User with Users role:**

   ![](https://i.imgur.com/6TDWx68.png)

   

2. **Login:**

   ![](https://i.imgur.com/E6bQTvI.png)

   

3. **Request a resource that is authorized:**

   ![](https://i.imgur.com/UB3EuMm.png)

   

4. **Request a resource that isn't authorized:**

   ![](https://i.imgur.com/ZoONU9O.png)

