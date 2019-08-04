# Aerospike and Kafka
Using Aerospike with Kafka - outbound from Aerospike

![Kafka Outbound](assets/kafka_outbound.png)

### Components
The solution consists of these components
- [Zookeeper](https://zookeeper.apache.org/)
- [Kafka](https://kafka.apache.org/)
- [Aerospike Enterprise Edition](https://www.aerospike.com/products/product-matrix/)
- [Aerospike Connector for Kafka](https://www.aerospike.com/docs/connectors/enterprise/kafka/)
- Site Tracking traffic sumulator, written in Java, to simulate tracking of users visites to a web site (Site Tracking)
- Kafka Cli to view the messages

![Component Diagram](http://www.plantuml.com/plantuml/proxy?src=https://raw.githubusercontent.com/helipilot50/aerospike-and-kafka/master/assets/component-diagram.puml)



## Companion Code
This exeammple uses Docker and Docker Compose to create the environment to track users visits to a website, as log level data, then export it to a Kafka topic for downstream aggregation, transformation and analysis.

### deployment
Docker containers


Clone the repository using:
```bash
https://github.com/helipilot50/aerospike-and-kafka.git
```

#### Build Docker images
```bash
sudo docker-compose build
sudo docker-compose up
```
#### Run the example
```bash
sudo docker-compose up
```
