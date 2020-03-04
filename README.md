Built With:

Maven - Dependency Management
Spring Boot - Framework to ease the bootstrapping and development of new Spring Applications
MySQL - Open-Source Relational Database Management System
git - Free and Open-Source distributed version control system


External Tools Used
Postman - API Development Environment (Testing Docmentation)

Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.arc.sbtest.SBtemplateApplication class from your IDE.

Download the zip or clone the Git repository.
Unzip the zip file (if you downloaded one)
Open Command Prompt and Change directory (cd) to folder containing pom.xml
Open Eclipse
File -> Import -> Existing Maven Project -> Navigate to the folder where you unzipped the zip
Select the project
Choose the Spring Boot Application file (search for @SpringBootApplication)
Right Click on the file and Run as Java Application
Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run


URLs
URL	                                                Method	             
http://localhost:8080/api/auth/signin	                 POST          
http://localhost:8080/api/auth/signup	                 POST 	
http://localhost:8080/assignement/saveusers	         POST
http://localhost:8080/assignement/users/{id}	         GET	
http://localhost:8080/assignement/allusers	         GET	
http://localhost:8080/assignement/updateuser/{id}	 PUT
http://localhost:8080/assignement/deleteuser/{id}        DELETE

Sample Request Payload :

http://localhost:8080/api/auth/signin

{
  "username":"Gaurav",
  "password":"password"
}




http://localhost:8080/api/auth/signup

{
  "name":"Sandip",
  "username":"sandy",
  "email":"sandy@gmail.com",
  "role":["user"],
  "password":"password"
}


http://localhost:8080/assignement/saveusers

{
  "name":"Sandip",
  "username":"sandy",
  "email":"sandy@gmail.com",
  "role":["admin","user"],
  "password":"password",
  "status":"ACTIVATED"
}


For database table creation and entries please follow sandip.sql file
