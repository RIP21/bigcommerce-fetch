import * as Empty from "../../constants/constants";
import objectAssign from 'object-assign';

const REMOVE_ITEM = 'items/REMOVE';
const UPDATE_ITEM = 'items/UPDATE';
const ADD_ITEM = 'items/ADD';
const CLEAR_ALL_ITEMS = 'items/CLEAR_ALL';
const RESTORE_ITEMS = "items/RESTORE";

const initialState = [
  Object.assign({}, Empty.ITEM, {itemId: 0}),
  Object.assign({}, Empty.ITEM, {
    itemId: 1,
    skuId: 'ABC-123', sku: 'ABC-123', value: 'ABC-123',
    productName: "Lorem epsum ololoolololo mountain dew",
    optionValue: "Lorem epsum ololoolololo mountain dew",
    tinyImg: "https://cdn6.bigcommerce.com/s-2e83t/products/3481/images/869/3-mtbn3xx_3__58804.1467947960.50.50.jpg?c=2",
    standardImg: "https://cdn6.bigcommerce.com/s-2e83t/products/3481/images/869/3-mtbn3xx_3__58804.1467947960.451.416.jpg?c=2"
  }),
  Object.assign({}, Empty.ITEM, {
      itemId: 2,
      skuId: 'ABC-123', sku: 'ABC-123', value: 'ABC-123',
      productName: "Lorem epsum ololoolololo mountain dew",
      optionValue: "Lorem epsum ololoolololo mountain dew",
      tinyImg: "https://cdn6.bigcommerce.com/s-2e83t/products/3481/images/869/3-mtbn3xx_3__58804.1467947960.50.50.jpg?c=2",
      standardImg: "https://cdn6.bigcommerce.com/s-2e83t/products/3481/images/869/3-mtbn3xx_3__58804.1467947960.451.416.jpg?c=2"
    },
  ),
  /*   Object.assign({}, Empty.ITEM, {itemId: 3}),
   Object.assign({}, Empty.ITEM, {itemId: 4}),
   Object.assign({}, Empty.ITEM, {itemId: 5}),
   Object.assign({}, Empty.ITEM, {itemId: 6}),
   Object.assign({}, Empty.ITEM, {itemId: 7}),
   Object.assign({}, Empty.ITEM, {itemId: 8}),
   Object.assign({}, Empty.ITEM, {itemId: 9}),
   Object.assign({}, Empty.ITEM, {itemId: 10}),
   Object.assign({}, Empty.ITEM, {itemId: 11}),
   Object.assign({}, Empty.ITEM, {itemId: 12}),
   Object.assign({}, Empty.ITEM, {itemId: 13}),
   Object.assign({}, Empty.ITEM, {itemId: 14}),
   Object.assign({}, Empty.ITEM, {itemId: 15}),
   Object.assign({}, Empty.ITEM, {itemId: 16}),
   Object.assign({}, Empty.ITEM, {itemId: 17}),
   Object.assign({}, Empty.ITEM, {itemId: 18}),
   Object.assign({}, Empty.ITEM, {itemId: 19}),
   Object.assign({}, Empty.ITEM, {itemId: 20}),
   Object.assign({}, Empty.ITEM, {itemId: 21}),
   Object.assign({}, Empty.ITEM, {itemId: 22}),
   Object.assign({}, Empty.ITEM, {itemId: 23}),
   Object.assign({}, Empty.ITEM, {itemId: 24}),
   Object.assign({}, Empty.ITEM, {itemId: 25}),
   Object.assign({}, Empty.ITEM, {itemId: 26}),
   Object.assign({}, Empty.ITEM, {itemId: 27}),
   Object.assign({}, Empty.ITEM, {itemId: 28}),
   Object.assign({}, Empty.ITEM, {skuId: 'ABC-123', sku: 'ABC-123', value: 'ABC-123' ,itemId: 29}),*/
];

export default function items(state = initialState, action) {
  switch (action.type) {

    case ADD_ITEM:
      return [...state,
        objectAssign({}, action.item)];

    case UPDATE_ITEM:
      return [...state.filter(item => item.itemId !== action.item.itemId),
        objectAssign({}, action.item)];

    case REMOVE_ITEM:
      return [...state.filter(item => item.itemId !== action.item.itemId)];

    case RESTORE_ITEMS:
      debugger;
      return [...action.items];

    case CLEAR_ALL_ITEMS:
      return [...initialState];

    default:
      return state;
  }
}

const generateItemId = (state) => {
  return Math.max.apply(Math, state.items.map(item => item.itemId)) + 1;
};

export function removeItem(item) {
  return {type: REMOVE_ITEM, item};
}

export function addItem(item) {
  return {type: ADD_ITEM, item};
}

export function updateItem(item) {
  return {type: UPDATE_ITEM, item};
}

export function clearItems() {
  return {type: CLEAR_ALL_ITEMS};
}

export function addNewRow() {
  return (dispatch, getState) => {
    const newItem = objectAssign({}, Empty.ITEM, {itemId: generateItemId(getState()), dateCreated: new Date()});
    dispatch(addItem(newItem));
  };
}

export function restoreFromCookies(items) {
  return {
    items,
    type: RESTORE_ITEMS
  }
}


