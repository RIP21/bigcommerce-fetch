import {createStore, applyMiddleware} from 'redux';
import rootReducer from '../modules/index';
import thunk from 'redux-thunk';
import promiseMiddleware from 'redux-promise-middleware';

export default function configureStore(initialState) {
  return createStore(
    rootReducer,
    initialState,
    applyMiddleware(thunk, promiseMiddleware())
  );
}
