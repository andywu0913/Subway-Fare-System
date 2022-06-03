const express = require('express');

const app = express();

app.use(express.json());

// The API to mock a passenger has swiped his ticket entering the speed gate.
app.use(require('./routes/enter'));

// The API to mock a passenger has swiped his ticket exiting the speed gate.
app.use(require('./routes/exit'));

module.exports = app;
