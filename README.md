# spring-mondoDB-project

Description:
This project involves the development of a straightforward HTTP server using Spring Boot for a placeholder object. The main goal is to store information in a MongoDB database while integrating a local cache within the application's memory. Ensuring thread safety is crucial, specifically regarding data updates retrieved from and stored in the cache. Moreover, the project requires configuration for two distinct profiles.


How to start this project:
1.Start mongoDb and create a new user using:
show dbs
use admin
db.createUser({user:"ck",pwd:"ck123",roles: [{ role: "root", db: "admin" }]})
db.auth('ck','ck123');

2. For this usage, create a new databse(Please edit application-local if you want to config other user):
use object
db.createUser({user:"ck",pwd:"ck123",roles: [{ role: "root", db: "admin" }]});
db.auth('ck','ck123');

3. run mvn clean package to build a jar file
4. run run.sh to start application (or just run "java -jar springboot-mongo-demo.jar" under folder)
5. test script is provied in postManScript folder, it can be loaded directly from postPan
