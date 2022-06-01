const { Kafka } = require('kafkajs');
const kafkaConfig = require('../config/kafka');

const kafka = new Kafka(kafkaConfig.connection);

const producer = kafka.producer();

producer.connect();

module.exports = {
  producer,
};
