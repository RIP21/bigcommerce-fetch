import * as types from '../constants/actionTypes';
import * as Empty from "../constants/emptyEntities";
import objectAssign from 'object-assign';

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

export function addNewRow() {
  return (dispatch, getState) => {
    const newItem = objectAssign({}, Empty.ITEM, {itemId: generateItemId(getState()), dateCreated: new Date()});
    dispatch(addItem(newItem));
  };
}

const generateItemId = (state) => {
  return Math.max.apply(Math, state.items.map(item => item.itemId)) + 1;
};
