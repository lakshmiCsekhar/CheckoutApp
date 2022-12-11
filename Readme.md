# Checkout Application


## Overview

Checkout Application is a simple Rest API built on top of Java 8 and Spring boot.This Rest API is used to start the process of checkout in a e-commerce application.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run this application on your local machine. One way is to execute the `main` method in the `org.ecommerce.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```
## Run unit tests locally

```shell
mvn test
```

## Run all tests locally

```shell
mvn verify -PtestAll
```

## Access swagger locally

```shell
http://localhost:8080/swagger-ui/index.html
```

## Access H2 locally

```shell
http://localhost:8080/h2-console/login.jsp
```

## Approach

1. Problem breakdown & Requirement analysis
2. Architecture & Data Modelling
   1. Identify entities
   2.  Application Tiers (Endpoint, Service, Repository)
   3. Choosing H2
   4. Database Design
   5. Develop Checkout as a Microservice
   6. Choosing spring boot framework
   7. Error Handling
   
3. Project setup
   1. Initial setup for Spring boot
   2. Maven dependency configuration
   3. Sonar lint setup in IDE

4. Tests (TDD)
   1. Started writing test cases with few inputs and expected outputs based on happy flow in the requirement
   2.  Proceed with Development and enhanced Tests
   
5. Development
   1. Figuring out service layer logic to calculate total price and applying discounts
   2. Created packages prototype classes for Service / Repository and Api Resource
   3. Several development cycles and refactoring based on new Test cases
   4. Integration Test 
   5. Added separate Maven profiles for unit testing & Integration Testing 
6. Refactoring
7. Verification
8. Bug fixing & Enhancements



## Future improvements

#### Functional 
1. Different discount models could be added. Percentage reduction in unit price, buy 1 get 1 etc
2. Inventory verification & update on checkout.
3. If the total price is closer to availing discount , add a promotional message which informs the customer about the possible discounts.

#### Technical

1. Add authentication using OAuth
2. Deploy & test the application using a cloud service such as EC2
3. Revisit the datamodel to add support for different product types


