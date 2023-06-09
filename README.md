Online bookstore
================

### Few notes about implementation details

To keep the solution simple and address requested features without compromising showcase knowledge, I made the following decisions:

* The ordering logic assumes that each customer has an unlimited account balance. Also, we can take it as the customer will pay for it 
at the time of delivery.
* A separate endpoint exposes the loyalty benefit. With this approach, the initial ordering logic with its total price calculation and 
interface to communicate stays simple and effective. Further, it allows that customer can decide about spending loyalty points.
Also, the front-end app can group those operations in one form, so end user cannot realize this logic contains more than one step.
* I used Basic Authentication for customer management. The security aims to enable easy loyalty points management but not to show
more advanced security providers like OAuth, JWT or others.

### Running application locally

Prerequisites:

* Docker, Docker Compose v3
* Java 17

### Starting the app

First, package the service:

    $ mvn clean package

Then start dependencies with Docker Compose. From the project's root:

    $ docker compose --file docker/docker-compose.yml up --build -d

Finally, we can start the app running the main class: [OnlineBookstoreApplication](src/main/java/com/example/onlinebookstore/OnlineBookstoreApplication.java)

After we finish, to clean up the resources run the following:

    $ docker compose --file docker/docker-compose.yml down

### Using the app

Please consider either of the following to see how to use this service:

* http://localhost:8080/swagger-ui/index.html#/

* [OnlineBookstoreOpenAPI](src/main/resources/online-bookstore-api.yml)

