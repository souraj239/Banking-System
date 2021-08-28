# Customer Microservice

This is a microservice of the BANKING system project. This microservice accepts HTTP requests to to do transactions like Add Money or Deduct Money from any account and return the account details of the logged in customer.

## Api Endpoints

##### A valid JwtToken token should be present as a bearer token in the request header.

##### GET request to get the Account details from the database of the the logged in user.  

```url
/viewAccount
```
##### POST request to deduct amount from the given user's account. Username and amount should be sent to the following end point as JSON.

```url
/withdrawAmount
```

##### POST request to add amount to the given user's account. Username and amount should be sent to the following end point as JSON.

```url
/depositAmount
```

## Strarting the microservice
1. create the package by running the command

```bash
mvn clean install
```
2. Run the created jar file present in the \target folder of the microservice

```bash
java -jar 'jar-fie-name'
```
##### By default it will run on the port number 8092. So use the URL foe sending the requests
```url
http://localhost:8092/
```

## Configuration
use the "application.properties" file present in the "/src/resource" folder for the configuration of the microservice. 

####  The default configuration
```properties
server.port=8092
logging.level=info
server.port=8092

spring.h2.console.enabled=true
spring.datasource.patform=h2
spring.datasource.url=jdbc:h2:mem:customer_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto = update

logging.level.org.hibernate.stat=debug
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.show-sql=true
```
## About
The project is made during the Cognizant Full Stack Java Internship.