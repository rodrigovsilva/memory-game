# memory-game
Memory Game Challenge for Lepaya

## Solution architecture

This challenge was split into 2 sub-projects:
* memoy-game-frontend: Frontend of the application with UI, views and http server.
    * I'm using VueJS, Vuex and Vuetify. The main reason is an instance of the system can be used for elements' rendering, the data is sent to the view directly with simple syntax and thanks to reactivity, Vue automatically renders elements without coding. UI with Material Design.
* memory-game-backend: Backend of the application with all business rules and DB.
    * I'm using Java 11 with SpringBoot because enables building production-ready applications quickly and provides non-functional features: Embedded servers which are easy to deploy with the containers. It helps in monitoring the multiples components. It helps in configuring the components externally. I'm also using the embedded H2 as database solution.
* For deployment, I decided to use docker and docker-compose. Docker-compose also makes it easy to startup multiple containers at the same time and automatically connect them together with some form of networking. The purpose of docker-compose is to function as docker cli but to issue multiple commands much more quickly.

## Requirements

1. [Docker](https://docs.docker.com/install/)


## Project installation
To run these projects, you will use docker-compose. This way we can run entire enviroment easily.

1. First we need to create a docker network:
`docker network create memory-game-network`

1. Now we will build and run the projects with a single command
`docker-compose up -d --build`

1. Go to your browser and access [Memory Game](http://localhost:8080/)

## Notes

There are some tests implemented on Backend only to show how important is create tests during project implementation.