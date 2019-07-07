# Field Condition Statistics

## Description

A simple API able to handle two use cases:
  1. Record incoming field condition measurements
  2. Provide field condition statistics from the last 30 days

## Prerequisites:
* [java 8+][java-download]
* [maven 3.x.x][maven-download]
* [docker][docker-website] (optional)

Check the installed version of java
```bash
java -version
```
and maven
```bash
mvn --v
```

## History:

* The first implementation was done by storing and getting the statistics from a Collection in memory.
* The current implementation uses an in-memory database (H2) where it store and filter for occurrence between 2 dates 
(if not provided gives the statistics of the last 30 days). Another approach is letting the DB to calculate all the statistics, 
here is an example:
```sql
SELECT 
       MIN(vegetation) AS min,
       MAX(vegetation) AS max,
       AVG(vegetation) AS average
FROM field_conditions;
```
* A huge improvement could be the use of time series database (like InfluxDB or Prometheus), which are optimized for handling time series data, arrays of numbers indexed by time (a datetime or a datetime range). 
* Other valid approaches might be the use of ElasticSearch (provides a built-in stats query aggregator) or other streaming analytics tools (like Spark, Storm or Flink)

## Test, Build and Run instructions

- test:  
```bash
mvn clean verify
```
- package: 
```bash
mvn clean package
```
- run: 

if you have docker already installed, run this command
```bash
mvn clean package -Dmaven.test.skip=true && mvn com.spotify:dockerfile-maven-plugin:build && docker run -p 8080:8080 ledion/field-condition-statistics:latest
```
otherwise
```bash
mvn clean package -Dmaven.test.skip=true && java -jar target/field-condition-statistics-1.0.0-SNAPSHOT.jar
```

## API Documentation

You can consult the REST API spec [here][api-docs].

## Test the REST

You can try it [at this page][swagger-ui].


[java-download]: https://www.java.com/en/download/
[maven-download]: https://maven.apache.org/download.cgi
[docker-website]: https://www.docker.com/

[api-docs]: http://localhost:8080/field-condition-statistics/v2/api-docs
[swagger-ui]: http://localhost:8080/field-condition-statistics/swagger-ui.html#/field-condition-statistics-controller
