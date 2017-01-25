import axios from 'axios';

export const LOAD_PRODUCTS = 'products/LOAD_ALL';

export default function products(state = [], action) {
  switch (action.type) {

    case `${LOAD_PRODUCTS}_PENDING`:
      return state;
    case `${LOAD_PRODUCTS}_FULFILLED`:
      return action.payload.data._embedded.flatSkus;
    case `${LOAD_PRODUCTS}_REJECTED`:
      return state;

    default:
      return state;
  }
}

const getLink = () => {
  if (process.env.NODE_ENV === 'production') {
    return 'http://138.197.141.28:8080/api/flatSkus';
  } else return 'http://localhost:8080/api/flatSkus';
};

export function loadProducts() {
  return dispatch => {
    return dispatch({
      type: LOAD_PRODUCTS,
      payload: axios.get(getLink())
    });
  };
}

