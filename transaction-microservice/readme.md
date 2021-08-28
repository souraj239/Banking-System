# Transaction Microservice

This is a microservice of the BANKING system project. This microservice accepts HTTP requests to to do transactions like Add Money, Deposit Money, Transfer Money from the account and get the account statement of the logged in customer.

## Api Endpoints

##### A valid JwtToken token should be present as a bearer token in the request header.

##### GET request to get the statement of the logged in customer. transaction details from the database of the the logged in user.  

```url
/getStatement
```

##### POST request to transfer amount from the logged in user to the account to other account. recipient details should be sent to the following end point as JSON.

```url
/transferMoney
```

##### POST request to withdraw cash from the logged in user's account. Amount should be sent to the following end point as JSON.

```url
/cashWithdraw
```

##### POST request to deposit cash to the logged in user's account. Amount should be sent to the following end point as JSON.

```url
/cashDeposit
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
server.port=8009
logging.level.root=DEBUG

spring.datasource.url=jdbc:h2:mem:transaction_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.h2.console.settings.web-allow-others=true

logging.level.org.hibernate.stat=debug
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```
## About
The project is made during the Cognizant Full Stack Java Internship.