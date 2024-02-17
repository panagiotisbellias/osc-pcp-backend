# Personal Code Portfolio Backend

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Git configuration
```bash
git init
git add
git config --local user.name "Panagiotis Bellias"
git config --local user.email "belliaspan@gmail.com"
git commit -m "Initial commit"
git checkout -b development
git checkout development && git merge <TASK-BRANCH>
```

## Quarkus dependencies installation

### Environment: Ubuntu 22.04

#### Java 17

```bash
sudo apt update
sudo apt upgrade
apt-cache search openjdk | grep openjdk-17
sudo apt install openjdk-17-jre
sudo apt install openjdk-17-jdk
java --version
```

#### Maven 3.9.6

```bash
sudo apt update
sudo apt upgrade
sudo apt install maven
mvn -version
```

## Quarkus initialization

```bash
mvn io.quarkus.platform:quarkus-maven-plugin:3.6.6:create -DprojectGroupId='gr.pcp' -DprojectArtifactId='personal-code-portfolio' -Dextensions='resteasy-reactive'
mv personal-code-portfolio/* ../
mv personal-code-portfolio/.* ../
```

## Database Connection

### Set up database server

Many options available to set up a database server. As example, instructions are provided
here for [postgres](https://www.postgresql.org/).

* Using standalone installation (Linux/Debian environment)
```bash
sudo apt-get update
sudo apt-get install postgresql
sudo systemctl start postgresql
```

More info: https://www.geekbits.io/postgresql-default-username-and-password/   
If you want to change the default credentials you can use [psql utility](https://www.postgresguide.com/utilities/psql/)
or [pgAdmin](https://www.pgadmin.org/)

* Using [docker](https://www.docker.com/) engine and `docker run` command
```bash
docker run \
--name postgres \
-v postgresql:/var/lib/postgresql \
-v postgresql_data:/var/lib/postgresql/data \
-e POSTGRES_DATABASE=<DATABASE> \
-e POSTGRES_USER=<USER> \
-e POSTGRES_PASSWORD=<PASSWORD> \
-p <PORT>:5432 \
postgres
```

* Using docker engine with docker-compose.yml file

    * Create a docker-compose.yml with this content:
```text
version: '3.9'
services:
    postgres:
        image: postgres
        container_name: postgres
        volumes:
          - postgresql:/var/lib/postgresql
          - postgresql_data:/var/lib/postgresql/data
        environment:
          - POSTGRES_DB=<DATABASE>
          - POSTGRES_USER=<USER>
          - POSTGRES_PASSWORD=<PASSWORD>
        ports:
          - <PORT>:5432

volumes:
  postgresql:
  postgresql_data:
```

and run `docker compose up -d`

Also, make sure you have executed this to initiate database with a new user. Provide the <PASSWORD> when it will be asked.
```bash
psql -h localhost -p <PORT> -U <USER> -d <DATABASE> -f init.sql
```

### Configure application.properties

Make sure to adjust properly the following properties:

```properties
quarkus.datasource.username=<USER>
quarkus.datasource.password=<PASSWORD>
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:<PORT>/<DATABASE>
quarkus.hibernate-orm.database.default-schema=<SCHEMA>

quarkus.flyway.schemas=<SCHEMA>
quarkus.flyway.migrate-at-start=true
```

If you want to change them, configure properly the postgres instance and inform [application.properties](src/main/resources/application.properties)

## Caching support

### Set up redis server

[redis](https://redis.io/).

* Using standalone installation (Linux/Debian environment)
```bash
curl -fsSL https://packages.redis.io/gpg | sudo gpg --dearmor -o /usr/share/keyrings/redis-archive-keyring.gpg

echo "deb [signed-by=/usr/share/keyrings/redis-archive-keyring.gpg] https://packages.redis.io/deb $(lsb_release -cs) main" | sudo tee /etc/apt/sources.list.d/redis.list

sudo apt-get update
sudo apt-get install redis
```

* Using [docker](https://www.docker.com/) engine and `docker run` command
```bash
docker run \
--name redis \
-v redis-data:/data \
-p 6379:6379 \
redis
```

* Using docker engine with docker-compose.yml file

  * Create a docker-compose.yml with this content:
```text
version: '3.9'
services:
    redis:
    image: redis
    ports:
      - 6379:6379
    volumes:
      - redis-data:/data

volumes:
  redis-data:
```

and run `docker compose up -d`

### Configure application.properties

Make sure to adjust properly the following properties:

```properties
quarkus.redis.hosts=redis://localhost:6379
quarkus.cache.redis.value-type=jakarta.ws.rs.core.Response
quarkus.cache.redis.expire-after-write=10m
```

If you want you can configure otherwise the redis instance and inform [application.properties](src/main/resources/application.properties)

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/personal-code-portfolio-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.

## Provided Code

### RESTEasy Reactive

Easily start your Reactive RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started-reactive#reactive-jax-rs-resources)
