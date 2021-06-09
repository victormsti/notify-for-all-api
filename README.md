### Notify For All API Service

Hello! Thanks for visiting my application!

Its an API Service for Notifications using Web Push, E-mail and SMS

The project's stack is:

* Java 11,
* Spring Framework
* Mapstruct
* Swagger
* Hibernate
* Postgres
* Lombok
* Junit
* Mockito
* Twilio
* JWT

Instructions to run this application:

#### Install Docker
This project uses [Docker](https://www.docker.com/) for Postgres and PgAdmin

To install and execute postgres and pgadmin containers, this project has a file 
called **docker-compose.yml**

Run this command on your terminal in the path on the above file:

***docker compose up***

Now you have the Postgres on port 5432 and PgAdmin on port 5050

*Postgres credentials*:

**user**: root

**password**: root

*PgAdmin Credentials*

**user**: admin@admin.com

**password**: root

#### Configuring Database
Create a database called notify-for-all

Now you need to create tables to be able to run this app. To do so, this project has a file
called **db-initialization.sql**

Just execute this script on PgAdmin or your terminal inside Postgres

To create a local server, remeber to type the ***name of the container*** on host, witch is ***pg_container***

#### Executing API routes
This API uses JWT for authentication and security. First, you need to **create a user** and authenticate to be able 
to call al the others routes

##API Documentation
For API Documentation, this project used [Swagger] (https://swagger.io/).

In your browser, type ***http://localhost:8080/swagger-ui.html*** and now you can explore the routes

#### Sending Notifications
With your user created, then you need to create and App and configure witch Notification Type will be enabled

* Web-Push
* E-mail
* Text Message

Now create specific configurations for each Notification Type

Finally, send messages! Just specify the App Id and Configuration Id (webpush, email or text message)

Thanks!

[Testable link](http://ec2-54-94-148-229.sa-east-1.compute.amazonaws.com:8080/swagger-ui.html)
