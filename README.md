# memory-game
Memory Game Challenge for Lepaya

## Project details
This challenge was split into 2 sub-projects:
- memoy-game-frontend: Frontend of the application with UI, views and http server.
- memory-game-backend: Backend of the application with all business rules and DB.

## Project installation
To run these projects, you will use docker-compose. This way we can run entire enviroment easily.

1. First we need to create a docker network:
`docker network create memory-game-network`

1. Now we will build and run the projects with a single command
`docker-compose up -d --build`

1. Go to your browser and access [Memory Game](http://localhost:8080/)