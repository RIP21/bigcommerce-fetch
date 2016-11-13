import * as types from '../constants/actionTypes';

export function removeItem(item) {
  return {type: types.REMOVE_ITEM, item};
}

export function addItem(item) {
  return {type: types.ADD_ITEM, item};
}

export function updateItem(item) {
  return {type: types.UPDATE_ITEM, item};
}

export function clearItems() {
  return {type: types.CLEAR_ALL_ITEMS};
}
