# Reactive Voting System using Akka Persistence

This template uses

* [Akka Persistence](https://doc.akka.io/docs/akka/2.5/persistence.html?language=scala) for persistent actors,
* [Docker compose](https://docs.docker.com/compose) for running Cassandra,
* [Cassandra](https://cassandra.apache.org/doc/latest) as NoSQL database.

## Getting started

* fire up Cassandra using `docker-compose up` in the terminal
* run the application using `run` in the sbt console

## Checking the database

* in other terminal run `./cqlsh.sh`
```
cqlsh> SELECT * FROM akka.messages;     //this command displays the entire table 
```

For more commands checkout: [CQL Commands](https://docs.datastax.com/en/cql-oss/3.x/cql/cql_reference/cqlCommandsTOC.html)
