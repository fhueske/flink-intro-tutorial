# Apache Flink® Introduction Tutorial

----

This repository contains the setup for a Apache Flink introduction tutorial. The tutorial covers:

* Introduction to Apache Flink and stateful stream processing
* Implementation a simple stateful stream processing application for Apache Flink
* Querying data streams with Flink SQL

## Preparing the Tutorial Environment

To follow this tutorial, you will need to download Docker images and Maven artifacts. 
In order to avoid over-utilization of the conference network, we recommend to prepare for the tutorial at home/work and download the required components beforehand. Also make sure that you have all required software available on your machine.

### Requirements

A notebook with at least 8 GB memory and 4 CPU cores.

The following software is required:

* [Git](https://git-scm.com/)
* [Maven](http://maven.apache.org/) 3.2+
* Java 1.8+
* A Java IDE (IntelliJ, Eclipse, Netbeans, ...)
* [Docker](https://www.docker.com/products/docker-desktop) (incl. Docker Compose)

We recommend to run Docker with at least 3-4 GB memory and 2-3 CPU cores.

### Downloading the Required Artifacts

1. Cloning this repository and enter the folder

```bash
git clone https://github.com/fhueske/flink-intro-tutorial
cd flink-intro-tutorial
```

2. Download the Docker containers for the operations playground

```bash
cd operations-playground
docker-compose pull
cd ..
```

3. Download the Docker containers for the SQL playground

```bash
cd sql-playground
docker-compose pull
cd ..
```

4. Download Apache Flink Maven artifacts

```bash
cd fraud-detection-job
mvn clean package
cd ..
```

## Starting and Stopping a Docker Playground

The tutorial is based on two Docker-compose playgrounds, i.e., sand-boxed environments that consist of Docker containers running services like Flink and Kafka. The environments can be easily started and stopped.

* The Operations Playground is located in `./operations-playground`
* The SQL Playground is located in `./sql-playground`

### Starting a Docker Playground

To start a playground change into the respective folder and run

* Linux & macOS

```bash
docker-compose up -d
```

* Windows

```bash
set COMPOSE_CONVERT_WINDOWS_PATHS=1
docker-compose up -d
```

You can check if the playground was successfully started by running

```
docker-compose ps
```

All containers should in the `Up` state, except for the `client` container of the operations playground which should be in `Exit 0` state.

When the Docker container are started, you can access Flink's WebUI at [http://localhost:8081](http://localhost:8081).

### Stopping a Docker Playground

To stop a playground run the following command from the same folder.

* Linux, macOS, & Windows

```bash
docker-compose down
```

----

*Apache Flink, Flink®, Apache®, the squirrel logo, and the Apache feather logo are either registered trademarks or trademarks of The Apache Software Foundation.*