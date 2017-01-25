/*eslint-disable import/default */
import React from "react";
import {render} from "react-dom";
import configureStore from "./redux/store/configureStore";
import {Provider} from "react-redux";
import {loadProducts} from './redux/modules/products';
import App from "./containers/App";
import "./styles/styles.css";

const store = configureStore();
store.dispatch(loadProducts());

render(
  <Provider store={store}>
    <App/>
  </Provider>,
  document.getElementById('app')
);
