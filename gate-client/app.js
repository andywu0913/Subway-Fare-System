const express = require('express');
const path = require('path');

const app = express();

app.use(express.json());
app.use(express.static(path.join(__dirname, 'public')));

app.use(require('./routes/enter'));
app.use(require('./routes/exit'));

module.exports = app;
