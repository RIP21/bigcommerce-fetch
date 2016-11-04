import {combineReducers} from 'redux';
import products from './productsReducer'


const rootReducer = combineReducers({
  products
});

export default rootReducer;
