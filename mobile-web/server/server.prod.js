const path = require('path');
const express = require('express');

const { chalkSuccess } = require('./config/chalk.config');
const proxyConfig = require('./config/proxy.prod.config');
const proxyBuild = require('./utils/proxy.build');

// create app server
const app = express();
const port = 3000;

// host proxy
app.use(proxyBuild(proxyConfig));

// use index.html
app.use(express.static(path.join(__dirname, 'dist/css')));
app.use(express.static(path.join(__dirname, 'dist/js')));
app.use(express.static(path.join(__dirname, 'dist')));

app.use((req, res) => {
  res.sendFile(path.join(__dirname, 'dist', 'index.html'));
});

app.listen(port, error => {
  if (error) {
    console.error(error);
  } else {
    console.info(
      chalkSuccess(
        '==> 🌎  Listening on port %s. ' + 'Open up http://localhost:%s/ in your browser.'
      ),
      port,
      port
    );
  }
});
