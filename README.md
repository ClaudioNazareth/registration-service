# Registration Services

Click the badges below to see build and coverage information

[![Build Status](https://travis-ci.org/ClaudioNazareth/registration-service.svg?branch=master)](https://travis-ci.org/ClaudioNazareth/registration-service)
[![codecov](https://codecov.io/gh/ClaudioNazareth/registration-service/branch/master/graph/badge.svg)](https://codecov.io/gh/ClaudioNazareth/registration-service)


![javaversion](https://img.shields.io/badge/Java-8-yellowgreen.svg)
![springboot](https://img.shields.io/badge/spring%20boot-1.5.9.RELEASE-orange.svg)
![server](https://img.shields.io/badge/server-undertow-yellow.svg)
![swagger](https://img.shields.io/badge/swagger-2.7.0-green.svg)
![googleformater](https://img.shields.io/badge/google%20format-1.5-blue.svg)
![springsecurity](https://img.shields.io/badge/spring%20security-1.5.9-orange.svg)
![fixturefactory](https://img.shields.io/badge/fixture%20factory-3.1.10-yellowgreen.svg)
![pitest](https://img.shields.io/badge/pitest-1.3.1-yellow.svg)


- _Technical information about the project is after the description_

Scenario for this application

Build a web application that offers a remote service interface (endpoint) to register a user. A user
can only register if he is not part of a blacklist. The goal is to demonstrate that the web
application runs, the tests are passing and real HTTP requests can be made. In summary:

1. Set up a way to run a web application server
2. Implement an HTTP remote endpoint for the 'register' operation based on the given
interface specifications
3. Implement a simple way for the web application to remember already registered users (no
real persistence needed, in-memory is sufficient)
4. Implement a way to use the given 'ExclusionService' interface to simulate the business
logic of validating the user against a black-list. The ExclusionService does not need to be
implemented but can be stubbed out/ faked.
5. Check the implementation with unit tests and integration tests
  
If you have any doubts about the project, please feel free to contact me at chtnazareth@gmail.com

The Use Case(Class) that do the job is **RegisterUser** method **register**  

  
## Instructions
  
To compile and run this project you will need:

  * **Java 8** (JDK8)
  * **Maven 3.0.5** or grater
  
#### Why Maven and not Gradle ?  

I have some Gradle projects as you can see in my Github, but some services I've used in this project 
do not work very well yet with gradle. So for this test I chose maven.


##### Start and endpoints   
 
To start the application use the command bellow   

```bash
mvn spring-boot:run
```

##### I disabled the spring security basic authentication for this test.

**The base path for the endpoins is**: /api/v1
  -For this application we have:  **/v1/users/register** 
 
I prefer the approach  **post** to **/api/users** but was asked in the test that the endpoint 
should be **register**  


#### Tests

**Application port** :8080  

To run all unit and integration tests use the command bellow   

```bash
mvn verify
```  

**To run mutation tests use the command bellow - MUST RUN AFTER mvn test or verify**  

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```  
**This will output an html report to target/pit-reports/YYYYMMDDHHMI.**

You can also test the application at : https://registration-service.herokuapp.com/swagger-ui.html

#### Data base

- The application contains a built-in ** MongoDB ** database that is initialized along with application    
    - Port to access MongoDB: **12345**
    
- To see the log, see the ** registration-service.log ** file created at the root of the application.

- For the resources exposed I have used the following:
    - **200 OK** - to **GET** requests.
    - **201 Created** - to **POST**.
    - **204 No Content** - to **PUT**, **PATCH**, e **DELETE** requests.
    - I used some principles of ** HATEOAS ** for the API in which each resource has ** self link ** 
     and for creation of new resources is returned ** in the header the link to the resource created **.


# Architecture, tools and frameworks used

##Clean Architecture and Clean Code

### Clean Code

Clean Code is a development style that focuses on the **ease of writing, reading and maintaining code**.

**Robert C. Martin**, in his book, "**Clean Code: A Handbook of Agile Software Craftsmanship**," 
states that the reading to writing ratio of the code is 10: 1. Therefore, a well-written code that 
facilitates reading is not only desirable, **but necessary in the current scenario**.

For this project I've used some clean code principles like :

* **Names are very important** : 
  * **Be precise**: _we must pass the central idea of ​​our variable or method, without turning, being concise 
    and direct_.
  * **Do not be afraid of big names**: _a very descriptive name, even if it is large, will enable a 
    better understanding and subsequent maintenance of the code_.
    
* **Comments only the necessary**
  * _Comment what is needed and only what is necessary. Codes are constantly modified, while comments rarely. 
    Thus, it is common for a comment to cease to have meaning, or worse, to pass on a false meaning after some time_.    

### Clean Architecture

For this application I choose to use **Clean Architecture**

The Clean Architecture leverages well-known and not so well-known concepts, rules, and patterns, 
explaining how to fit them together, to propose a standardised way of building applications.

The core objectives behind Clean Architecture are the same as for Ports & Adapters (Hexagonal)
 and Onion Architectures:

* Independence of tools;
* Independence of delivery mechanisms;
* Testability in isolation.

In the [post](https://8thlight.com/blog/uncle-bob/2012/08/13/the-clean-architecture.html) about 
Clean Architecture was published, this was the diagram used to explain the global idea:

![cleanarchitecture](https://8thlight.com/blog/assets/posts/2012-08-13-the-clean-architecture/CleanArchitecture.jpg)

**_The best of clean architecture is its use an software design technique to understand and solve 
complexity is Domain Driven Design (DDD). Domain Driven Design advocates modeling based on the 
reality of business as relevant to our use cases._** 

## Formatter

The code was formatted using [Google Format](https://github.com/google/google-java-format)


## APIs - Swagger

To document the APIs I used Swagger.

Swagger is the world’s largest framework of API developer tools for the OpenAPI Specification(OAS),
enabling development across the entire API lifecycle, from design and documentation, 
to test and deployment.

Here you can read more about [Swagger](https://swagger.io/)

To **see and test** the APIs go to path **/swagger-ui.html** (ex: _http://localhost:8080/swagger-ui.html_)

## UNDERTONW
Undertow is a web server designed to be used for both blocking and non-blocking tasks. 
Some of its main features are:

  * High Performance
  * Embeddable
  * Servlet 3.1
  * Web Sockets
  * Reverse Proxy

See more at In the [undertow](http://undertow.io/)


## Fixture Factory
Fixture Factory is a tool to help developers quickly build and organize fake objects for unit tests. 
The key idea is to create specification limits of the data (templates) instead of hardcoded data. 
Try using F-F, then you can focus on the behavior of your methods and we manage the data.

See more at [Fixture Factory](https://github.com/six2six/fixture-factory)


## MongoDB
MongoDB is the database for today’s applications: innovative, fast time-to-market, globally scalable, 
reliable, and inexpensive to operate. With MongoDB, you can build applications that were never
possible with traditional relational databases.


See more at [MongoDB](https://www.mongodb.com/collateral/mongodb-architecture-guide)