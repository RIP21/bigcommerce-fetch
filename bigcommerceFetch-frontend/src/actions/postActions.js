import ProductApi from '../api/productApi';
import * as types from '../constants/actionTypes';

export function loadPosts() {
  return dispatch => {
    return dispatch({
      type: types.LOAD_PRODUCTS,
      payload: ProductApi.getAll()
    })
  };
}


