# My First Angular App

This repository is another playground with the purpose of learning front end development using angular.

The idea is to also provide security features (AuthN and AuthZ) so a Keycloak setup will be added.
`spring-boot-docker-compose` is disabled as my local setup uses podman.
If you use podman too, just run `podman compose -f src/main/resources/docker/compose.yml up -d`.

The client code for the frontend is generated with openapi-tools and ignored in the linter config.
```shell
cd frontend
npx openapi-generator-cli generate -i ../src/main/resources/static/openapi/openapi.yml -g typescript-fetch -o ./src/api-client
```
