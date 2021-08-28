# Banking Application

Spring Boot-based Banking App With Spring Security Authentication. The App contains 3 microservices exposing Restful APIs


- Authorization Microservice
- Customer Microservice
- Transaction Microservice


## Features
- Authentication
- Cash Deposit
- Cash Withdraw
- Money Transfer
- Account Statement

## Apis
```properties
- POST: /authorization/login/
- GET: /viewAccount
- POST: /cashWithdraw
- POST: /cashDeposit
- GET: /getStatement
````

## Default Ports
- Authorization: 8008
- Customer Microservice: 8092
- Transaction Microservice: 8009

## Documentation

### Swagger documentation available at
```
\swagger-ui.html
```
## Properties
#### Databse Used: H2
####  The default database configuration
```properties
spring.h2.console.enabled=true
spring.datasource.patform=h2
spring.datasource.url=jdbc:h2:mem:databasename
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto = update

logging.level.org.hibernate.stat=debug
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.jpa.show-sql=true
```

## About
#### Developed By Souraj Mukhopadhyay
