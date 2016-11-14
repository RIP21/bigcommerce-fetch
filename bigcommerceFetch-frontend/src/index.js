/*eslint-disable import/default */
import "babel-polyfill";
import React from "react";
import {render} from "react-dom";
import configureStore from "./store/configureStore";
import {Provider} from "react-redux";
import {loadProducts} from './actions/productsActions';
import AppRedux from "./containers/AppRedux";
import "./styles/styles.css";

if (process.env.NODE_ENV !== 'production') {
  module.exports = require('../node_modules/bootstrap/dist/css/bootstrap.min.css');
}


const store = configureStore();
store.dispatch(loadProducts());

// Create an enhanced history that syncs navigation events with the store

render(
  <Provider store={store}>
    <AppRedux/>
  </Provider>,
  document.getElementById('app')
);
