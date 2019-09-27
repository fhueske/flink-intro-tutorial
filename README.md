# Apache Flink® Introduction Tutorial

This repository provides an introduction tutorial for Apache Flink.

The tutorial covers:

* Introduction to Apache Flink and stateful stream processing.
* Implementation a simple stateful stream processing application for Apache Flink.
* Querying data streams with Flink SQL.

## Preparing the Tutorial Environment

To follow this tutorial, you will need some software installed and a couple of Docker images and Maven artifacts on your machine.
To avoid over-utilization of the conference network and save time, we recommend to install all software and download the required docker images and Maven artifacts beforehand before attending the tutorial.

### Required Software

We recommend a notebook with at least 8 GB memory and 4 CPU cores.

The following software is required:

* [Git](https://git-scm.com/)
* [Maven](http://maven.apache.org/) 3.2+
* Java 1.8+
* A Java IDE (IntelliJ, Eclipse, Netbeans, ...)
* [Docker](https://www.docker.com/products/docker-desktop) (incl. Docker Compose)

We recommend to run Docker with at least 3-4 GB memory and 2-3 CPU cores.

### Downloading the Docker Images and Maven Artifacts

1. Cloning this repository and enter the tutorial folder

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

4. Download the Maven artifacts

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

```bash
docker-compose up -d
```

**Note:** If you are using Windows, you need to share the Windows drive (for example `C:`) that you are starting the playground from with Docker. This is done in the "Shared Drives" tab of the Docker settings. Docker will mount the Flink configuration into the Flink containers and use the Windows file system to store checkpoint and savepoint data. 

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

## Questions or Problems with the Setup?

If you have any questions, please open a [issue](https://github.com/fhueske/flink-intro-tutorial/issues) or write me an email to *fhueske [at] apache.org*.

----

*Apache Flink, Flink®, Apache®, the squirrel logo, and the Apache feather logo are either registered trademarks or trademarks of The Apache Software Foundation.*