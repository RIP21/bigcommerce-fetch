/*eslint-disable import/default */
import "babel-polyfill";
import React from "react";
import {render} from "react-dom";
import configureStore from "./store/configureStore";
import {Provider} from "react-redux";
import {loadPosts} from './actions/postActions';
import "../node_modules/bootstrap/dist/css/bootstrap.min.css";
import "../node_modules/toastr/build/toastr.min.css";
import "../node_modules/simplemde/dist/simplemde.min.css";
import "./styles/styles.css";

import ReactGA from 'react-ga';

const store = configureStore();
store.dispatch(loadPosts());

// Create an enhanced history that syncs navigation events with the store

render(
  <Provider store={store}>
    <Router history={history} routes={routes} onUpdate={logPageView}/>
  </Provider>,
  document.getElementById('app')
);
