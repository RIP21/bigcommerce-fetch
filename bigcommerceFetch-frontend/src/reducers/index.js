import {combineReducers} from 'redux';
import products from './productsReducer';
import items from './itemsReducer';


const rootReducer = combineReducers({
  products,
  items
});

export default rootReducer;
