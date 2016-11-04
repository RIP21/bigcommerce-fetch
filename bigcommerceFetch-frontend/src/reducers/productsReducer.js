import * as types from '../constants/actionTypes';
import initialState from '../constants/initialState';

export default function products(state = initialState.products, action) {
  switch (action.type) {

    case `${types.LOAD_PRODUCTS}_PENDING`:
      return state;
    case `${types.LOAD_PRODUCTS}_FULFILLED`:
      return action.payload.data._embedded.flattenedProducts;
    case `${types.LOAD_PRODUCTS}_REJECTED`:
      return state;

    default:
      return state;
  }
}
