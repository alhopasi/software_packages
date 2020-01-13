# SOFTWARE PACKAGES INFO

## RUN IN CLOUD:

[https://softwarepackages.herokuapp.com](https://softwarepackages.herokuapp.com)

## RUN LOCAL:

RUN WITH DOCKER

OR

RUN MANUALLY.

### Docker

`docker-compose up`

On `localhost:5000` we have alpine linux running.

On `localhost:5001` we have ubuntu linux running.

### Manually

Requires Java 11!

Package first: `mvn package`  (or download package from releases on github)

Run: `java -jar target/software_packages-1.0-SNAPSHOT.jar`

On `localhost:8080` we have the webpage.

[![CircleCI](https://circleci.com/gh/alhopasi/software_packages.svg?style=svg)](https://circleci.com/gh/alhopasi/software_packages)
