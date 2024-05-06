# Acme Account Manager

Coding assessment for MOX Bank interview.<br>

Requirements Summary:<br>
<ul>
  <li>Programming language = Java or Kotlin</li>
  <li>Build tool = Maven or Gradle</li>
  <li>Spring Boot</li>
  <li>H2 database with data persisted to disk</li>
  <li>2 REST APIs for a) get account balance of specific customer and b) transfer fund from one account to another</li>
  <li>Provided 2 customer account balances: 12345678 with HKD 1,000,000 and 88888888 with HKD 1,000,000</li>
  <li>Latest account balances can be retrieved between runs (no data reset or re-initialization)</li>
</ul>

Compatible with Java 17.<br>
Spring Boot v3 as base with embedded Tomcat server.<br>
OpenAPI v3 for Swagger.<br>
H2 database for data layer.<br>
REST APIs for the service (business logic) layer.<br>
No JSON web token (JWT) for API authentication & authorization.<br>

Design choices can be accessed <a href="docs/choices.txt">here</a>.<br>

Database located under "data" folder.<br>
Datasource URL = jdbc:h2:file:./data/acctmgrdb<br>

Available endpoints:<br>
Swagger UI => / or /api<br>
Account balance of specific customer REST API => GET /api/v1/balances/{cid}<br>
Transfer fund REST API => POST /api/v1/transfer<br>
H2 Console => /h2-console<br>
Actuator => /actuator<br>

To run the project:<br>
java -jar AcmeAccountManager-1.0.jar<br>
<br>
http://localhost:8080 or http://localhost:8080/api => Swagger UI<br>
GET http://localhost:8080/api/v1/balances/{cid} => Account balance of specific customer REST API<br>
POST http://localhost:8080/api/v1/transfer => Transfer fund REST API<br>
http://localhost:8080/h2-console => H2 Console<br>
http://localhost:8080/actuator => Actuator<br>
