import {combineReducers} from 'redux';
import products from './products';
import items from './items';


const rootReducer = combineReducers({
  products,
  items
});

export default rootReducer;
