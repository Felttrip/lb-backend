#Levels Beyond Notes API

This application is a RESTful JSON API that could back a simple note taking application. 

The project is written in Java and uses the [Spark micro framework](http://sparkjava.com/) for routing, 
[Google gson](https://github.com/google/gson)for serializing and de-serializing JSON, and [Sql2o](http://www.sql2o.org/)
for connecting with the MySql database. 

##Dependencies
This project relies on Docker to host the environment the application will run in.
 
Please install [Docker](https://www.docker.com/) on your local environment. 

##Installation
After Docker is installed, run the following commands from the root of the project directory.

Build/Start the MySql Container. Ensure that port `3306` is open and don't forget to replace the <absolutePathToProject>.
This will also create and seed the database.

`docker run -p 3306:3306 --name felttrip_mysql -v <absolutePathToProject>/lb-backend/sql:/docker-entrypoint-initdb.d -e MYSQL_ROOT_PASSWORD=pass -e MYSQL_DATABASE=lb_backend -d mysql:5.7`

Build the image of the project. This will create an image that has the Spark application compiled with all dependencies. 

`docker build -t felttrip/lb-backend .`

Verify that MySql has started successfully in the felttrip_mysql container.

`docker logs felttrip_mysql`

 Start a container with the applications image.
   
`docker run -p 80:4567 --name felttrip_lb_backend --link felttrip_mysql:my_mysql -d felttrip/lb-backend`

##Endpoints

### Get all notes

### Get a note

### Save a notes
