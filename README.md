## SOFTWARE PACKAGES INFO

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

Run: `java -jar target/software_packages-1.0-SNAPSHOT.jar"

On `localhost:8080` we have the webpage.
