# randomfiles.io-api
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=sqale_rating)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=reliability_rating)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api) 
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=security_rating)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api) 
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=bugs)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api) 
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=duplicated_lines_density)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api) 
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=ncloc)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=sqale_index)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=eusebiu-biroas_randomfiles.io-api&metric=coverage)](https://sonarcloud.io/summary/new_code?id=eusebiu-biroas_randomfiles.io-api) 

# [randomfiles.io](http://randomfiles.io) REST API

randomfiles.io-api is a Spring Boot Java project which exposes REST endpoints for generating and getting random files.

## Milestones

- [Milestone 1 (in progress)](https://github.com/eusebiu-biroas/randomfiles.io-api/milestone/1): In the first release
 users should be able to generate basic files for the following formats: PDF, XML, TXT, JSON, CSV, XLS, DOCX
                                                                                   
## Open endpoints

Open endpoints require no Authentication:
* Get random PDF file: `GET http://localhost:8080/api/rest/v1/pdf`
* Get a zipped batch of random PDF files: `GET http://localhost:8080/api/rest/v1/pdf/batch/{batchSize}`
* Get random XML file: `GET http://localhost:8080/api/rest/v1/xml`
* Get a zipped batch of random XML files: `GET http://localhost:8080/api/rest/v1/xml/batch/{batchSize}`
* Get random JSON file: `GET http://localhost:8080/api/rest/v1/json`
* Get a zipped batch of random JSON files: `GET http://localhost:8080/api/rest/v1/json/batch/{batchSize}`
* Get random TXT file: `GET http://localhost:8080/api/rest/v1/txt`
* Get a zipped batch of random TXT files: `GET http://localhost:8080/api/rest/v1/txt/batch/{batchSize}`
* Get random CSV file: `GET http://localhost:8080/api/rest/v1/csv`
* Get a zipped batch of random CSV files: `GET http://localhost:8080/api/rest/v1/csv/batch/{batchSize}`
* Get random XLS file: `GET http://localhost:8080/api/rest/v1/xls`
* Get a zipped batch of random XLS files: `GET http://localhost:8080/api/rest/v1/xls/batch/{batchSize}`
* Get random DOCX file: `GET http://localhost:8080/api/rest/v1/docx`
* Get a zipped batch of random DOCX files: `GET http://localhost:8080/api/rest/v1/docx/batch/{batchSize}`

## How To Use

To clone and run this application, you'll need [Git](https://git-scm.com), 
[JDK 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html),
 and [Maven](https://maven.apache.org/download.cgi) installed on your computer. 

From your command line:

```bash
# Clone this repository
$ git clone https://github.com/eusebiu-biroas/randomfiles.io-api

# Go into the repository
$ cd randomfiles.io-api
```

You can start the application in any of the following ways:


```
# Start it using spring-boot:run plugin
$ mvn spring-boot:run
```

or

```
# Build the package
$ mvn package

# Go into build folder
$ cd target

# run the compiled JAR
$ java -jar randomfiles.io-api-1.0-SNAPSHOT.jar
```
after the application is started you can call the endpoints using [Postman](https://www.getpostman.com) or even
directly your browser for [GET Open endpoints ](#open-endpoints).  

Note: after [Milestone 1](https://github.com/eusebiu-biroas/randomfiles.io-api/milestone/1) is complete, the application
will be live on [randomfiles.io](http://randomfiles.io).
