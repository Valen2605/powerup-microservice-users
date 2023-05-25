<br />
<div align="center">
<h3 align="center">PRAGMA POWER-UP</h3>
  <p align="center">
    This project develops an application that centralizes the services and orders of a restaurant chain that has different branches in the city..
  </p>
</div>

### Built With

* ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=java&logoColor=white)
* ![Spring](https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
* ![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
* ![MySQL](https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white)


### Unit tests are performed with
* [JUnit5](https://junit.org/junit5/) - Library used for testing
* [Mockito](https://site.mockito.org/) - Framework used for code testing
* [Jacoco](https://www.jacoco.org/jacoco/trunk/index.html) - Test coverage tool


### Security With
* [JWT](https://jwt.io/) - Standard tool for transmitting information

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these steps.

### Prerequisites

* JDK 17 [https://jdk.java.net/java-se-ri/17](https://jdk.java.net/java-se-ri/17)
* Gradle [https://gradle.org/install/](https://gradle.org/install/)
* MySQL [https://dev.mysql.com/downloads/installer/](https://dev.mysql.com/downloads/installer/)



### Installation

1. Clone the repository
2. Create a new database in MySQL called square
3. Update the database connection settings
   ```yml
   # src/main/resources/application-dev.yml
   spring:
      datasource:
          url: jdbc:mysql://localhost/users
          username: root
          password: <your-password>
   ```
<!-- USAGE -->
## Usage

1. Right-click the class PowerUpApplication and choose Run
2. Open [http://localhost:8090/swagger-ui/index.html](http://localhost:8090/swagger-ui/index.html) in your web browser

<!-- ROADMAP -->
## Tests

- Right-click the test folder and choose Run tests with coverage


## Project structure

### Configuration Layer
It is the outermost layer and is in charge of security configuration, class assignment and dependency injection.

### Layer Adapters
This layer contains two layers: the driven layer and the driving layer.

#### Driving layer
It is the user's entry point when connecting to the application.

#### Domain layer

It is the most internal module of the architecture, it belongs to
the domain layer and encapsulates the business logic and rules.


## Entry Points

### Entry point Authentication
![Image text](img/EntryPointAuthentication.jpg)

### Authentication Response
![Image text](img/AuthenticationResponse.jpg)

### Entry point create owner
![Image text](img/EntryPointCreateOwner.jpg)

### Exit point create owner
![Image text](/img/ExitPointCreateOwner.jpg)


## Author ✒️
* **Valentina Santa Muñoz** 
