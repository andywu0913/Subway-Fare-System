const express = require('express');
const kafkaConfig = require('../config/kafka');
const { producer: kafkaProducer } = require('../manager/kafka');

const router = express.Router();

/**
 * The API to mock a passenger has swiped his ticket entering the speed gate.
 *
 * @param {Object} body Swipe action object
 * @param {String} body.stationID The ID of the station that the passenger enters
 * @param {String} body.ticketID The ID of the ticket that the passenger uses
 */
router.post('/enter', async (req, res) => {
  const { stationID, ticketID } = req.body;
  try {
    if (stationID === undefined || typeof stationID !== 'string') {
      throw new Error('Illegal field for stationID.');
    }

    if (ticketID === undefined || typeof ticketID !== 'string') {
      throw new Error('Illegal field for ticketID.');
    }

    const data = await kafkaProducer.send({
      topic: kafkaConfig.topicName,
      messages: [{ value: JSON.stringify({
        method: 'enter',
        stationID,
        ticketID,
        timestamp: Date.now(),
      }) }],
      timestamp: Date.now(),
      timeout: kafkaConfig.timeout,
    });

    return res.json({ success: true, error: null, data });
  } catch (error) {
    console.error(`Enter gate error: ${error.message}`);
    return res.json({ success: false, error: error.message, data: null });
  }
});

module.exports = router;
