# mongolink-example

[![Build Status](https://mongolink.ci.cloudbees.com/job/mongolink-example/badge/icon)](https://mongolink.ci.cloudbees.com/job/mongolink-example/)

## Launch the example

`mvn jetty:run`

## Usage

### Get all

`curl http://localhost:8080/fruits`

### Add

`curl -d "name=aFruit" http://localhost:8080/fruits`

### Get

`curl http://localhost:8080/fruits/fruitId`

### Modify

`curl -X PUT -d "name=anotherFruit" http://localhost:8080/fruits/fruitId`

### Delete

`curl -X DELETE http://localhost:8080/fruits/fruitId`

## Online example

http://mongolink-example.mongolink.cloudbees.net/