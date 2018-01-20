# Registration Services

Click the badges below to see build and coverage information

[![Build Status](https://travis-ci.org/ClaudioNazareth/address-parser.svg?branch=master)](https://travis-ci.org/ClaudioNazareth/address-parser)
[![codecov](https://codecov.io/gh/ClaudioNazareth/address-parser/branch/master/graph/badge.svg)](https://codecov.io/gh/ClaudioNazareth/address-parser)


![javaversion](https://img.shields.io/badge/Java-8-yellowgreen.svg)
![springboot](https://img.shields.io/badge/spring%20boot-1.5.9.RELEASE-orange.svg)
![server](https://img.shields.io/badge/server-undertow-yellow.svg)
![swagger](https://img.shields.io/badge/swagger-2.7.0-green.svg)
![googleformater](https://img.shields.io/badge/google%20format-1.5-blue.svg)


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


The Use Case(Class) that do the job is **ParseAddress** method **execute**  
  
Instructions
============
  
To compile and run this project you will need:

  * **Java 8** (JDK8)
  * **Maven 3.0.5** or grater
  
 
To start the application use the command bellow   

```bash
mvn spring-boot:run
```
**The base path for the endpoins is**: /v1
  -For this application we have:  **/v1/address-parser/parse** 

**Application port** :8080  

To run all unit and integration tests use the command bellow   

```bash
mvn test
```  

**To run mutation tests use the command bellow - MUST RUN AFTER mvn test**  

```bash
mvn org.pitest:pitest-maven:mutationCoverage
```  

**This will output an html report to target/pit-reports/YYYYMMDDHHMI.**



You can also test the application at : https://nazareth-address-parser.herokuapp.com/swagger-ui.html



Formatter
==============
The code was formatted using [Google Format](https://github.com/google/google-java-format)


APIs - Swagger
==============

To document the APIs I used Swagger.

Swagger is the world’s largest framework of API developer tools for the OpenAPI Specification(OAS),
enabling development across the entire API lifecycle, from design and documentation, 
to test and deployment.

Here you can read more about [Swagger](https://swagger.io/)

To **see and test** the APIs go to path **/swagger-ui.html** (ex: _http://localhost:8080/swagger-ui.html_)



Clean Architecture and Clean Code
==============

#### Clean Code

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

#### Clean Architecture

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
