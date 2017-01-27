import React from 'react';
import Autosuggest from 'react-autosuggest';
import numeral from 'numeral';

const TableHeader = () => {
  return (
    <thead>
    <tr>
      <th>Item # (SKU)</th>
      <th>Product name/Description</th>
      <th>Price</th>
      <th>Quantity</th>
    </tr>
    </thead>
  );
};

const FirstRow = ({item, suggestions, onSuggestionSelected, onAutosuggestChange, onBlur, onChange, ...rest}) => {
  const calculatedPrice = numeral((item.price * item.quantity).toString()).format('$0,0.00');
  const inputProps = {
    placeholder: 'Type sku of the product',
    value: item.value,
    onChange: (event, {newValue}) => onAutosuggestChange(event, newValue, item),
    onBlur: () => onBlur(item)
  };
  return (
    <tr>
      <td>
        <Autosuggest
          id={item.itemId.toString()}
          suggestions={suggestions}
          onSuggestionSelected={(event, {suggestion}) => onSuggestionSelected(event, suggestion, item)}
          inputProps={inputProps}
          {...rest}
        /></td>
      <td>{item.productName}</td>
      <td>{calculatedPrice}</td>
      <td><input disabled={item.disabled} min="0" max="100000" type="number"
                 onChange={(event) => onChange(event, item)}
                 value={item.quantity}/>
      </td>
    </tr>
  );
};

const SecondRow = ({item, onRemoveButtonClick}) => {
  return (
    <tr key={`${item.itemId.toString()}-bottom`}>
      <td>
        {item.tinyImg ?
          <div>
            <div id="showthumb">
              <img src={item.tinyImg} className="skuimg" alt="thumbnail-img"/>
            </div>
            <div className="thumb">
              <img src={item.standardImg} alt="standard-img" className="img-thumbnail skuthumb"/>
            </div>
          </div> : <div/>}
      </td>
      <td colSpan="2">{item.optionValue}</td>
      <td>
        <button id="cart-btn" className="btn btn-danger" onClick={() => onRemoveButtonClick(item)}>Remove item
        </button>
      </td>
    </tr>
  );
};

const constructRows = (props) => {
  const {items} = props;
  const rows = [];
  for (let item of items) {
    rows.push(
      <FirstRow key={`${item.itemId.toString()}-top`} item={item} {...props} />
    );
    rows.push(
      <SecondRow key={`${item.itemId.toString()}-bottom`} item={item} {...props}/>
    )
  }
  return rows;
};

const CondensedItemsTable = ({totalPrice, ...props}) => {
  return (
    <table id="order-form">
      <TableHeader/>
      <tbody>
      {constructRows(props)} {/* This hack is needed to be able to use colSpan */}
      </tbody>
      <caption id="total-caption">Total: {totalPrice}</caption>
    </table>
  );
};

export default CondensedItemsTable;



