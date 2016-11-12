// This file configures a web server for testing the production build
// on your local machine.

import browserSync from 'browser-sync'; //eslint-disable-line
import historyApiFallback from 'connect-history-api-fallback';
import {chalkProcessing} from './chalkConfig';
import proxyMiddleware from 'http-proxy-middleware';

/* eslint-disable no-console */

console.log(chalkProcessing('Opening production build...'));

// Run Browsersync
browserSync({
  port: 3000,
  ui: {
    port: 3001
  },
  server: {
    baseDir: 'dist',
    middleware: [proxyMiddleware('/api', {target: 'http://localhost:8080', changeOrigin: true})]
  },

  files: [
    'src/*.html'
  ],

  middleware: [historyApiFallback(), proxyMiddleware('/api', {target: 'http://localhost:8080', changeOrigin: true})]
});
