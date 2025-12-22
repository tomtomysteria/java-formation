// test.js
const { handler } = require('./index');

// Simuler un "event" comme AWS Lambda le ferait
const mockEvent = {
  queryStringParameters: {
    name: 'Tom'
  }
};

handler(mockEvent).then(response => {
  console.log("Réponse Lambda :", response);
});
