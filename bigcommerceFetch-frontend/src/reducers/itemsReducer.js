import * as types from '../constants/actionTypes';
import initialState from '../constants/initialState';
import objectAssign from 'object-assign';

export default function items(state = initialState.items, action) {
  switch (action.type) {

    case types.ADD_ITEM:
      return [...state,
        objectAssign({}, action.item)];

    case types.UPDATE_ITEM:
      return [...state.filter(item => item.itemId !== action.item.itemId),
        objectAssign({}, action.item)];

    case types.REMOVE_ITEM:
      return [...state.filter(item => item.itemId !== action.item.itemId)];

    default:
      return state;
  }
}
