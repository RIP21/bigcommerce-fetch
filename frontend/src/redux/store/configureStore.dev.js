import {createStore, applyMiddleware} from 'redux';
import rootReducer from '../modules/index';
import reduxImmutableStateInvariant from 'redux-immutable-state-invariant';
import thunk from 'redux-thunk';
import promiseMiddleware from 'redux-promise-middleware';
import { composeWithDevTools } from 'redux-devtools-extension';

export default function configureStore(initialState) {
   return createStore(rootReducer, initialState, composeWithDevTools(
     applyMiddleware(thunk, promiseMiddleware(), reduxImmutableStateInvariant())
    )
  );
}

