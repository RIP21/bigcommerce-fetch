import React, {PropTypes} from "react";
import Autosuggest from 'react-autosuggest';

const ItemRow = ({
  suggestions,
  onSuggestionsFetchRequested,
  onSuggestionsClearRequested,
  getSuggestionValue,
  renderSuggestion,
  onSuggestionSelected,
  onChange,
  onAutosuggestChange,
  onBlur,
  onRemoveButtonClick,
  item,
  totalPrice
}) => {

  const inputProps = {
    placeholder: 'Type sku of the product',
    value: item.value,
    onChange: (event, {newValue}) => onAutosuggestChange(event, newValue, item),
    onBlur: () => onBlur(item)
  };

  return (
    <tr>
      <td>
        {item.tinyImg ?
          <div>
            <div id="showthumb">
              <img src={item.tinyImg} className="skuimg"/>
            </div>
            <div className="thumb">
              <img src={item.standardImg} className="img-thumbnail skuthumb"/>
            </div>
          </div> : <div/>}
      </td>
      <td className="autosuggest-cell"><Autosuggest
        id={item.itemId.toString()}
        suggestions={suggestions}
        onSuggestionsFetchRequested={onSuggestionsFetchRequested}
        onSuggestionsClearRequested={onSuggestionsClearRequested}
        getSuggestionValue={getSuggestionValue}
        renderSuggestion={renderSuggestion}
        onSuggestionSelected={(event, {suggestion}) => onSuggestionSelected(event, suggestion, item)}
        inputProps={inputProps}
      /></td>
      <td className="text-cell">{item.productName}</td>
      <td className="text-cell">{item.optionValue}</td>
      <td>{totalPrice}</td>
      <td className="qty"><input className="qty-textbox" disabled={item.disabled} min="0" max="100000" type="number"
                                 onChange={(event) => onChange(event, item)}
                                 value={item.quantity}/></td>
      <td>
        <button id="cart-btn" className="btn btn-danger" onClick={() => onRemoveButtonClick(item)}>Remove item</button>
      </td>
    </tr>
  );
};

ItemRow.propTypes = {
  suggestions: PropTypes.array.isRequired,
  onSuggestionsFetchRequested: PropTypes.func.isRequired,
  onSuggestionsClearRequested: PropTypes.func.isRequired,
  getSuggestionValue: PropTypes.func.isRequired,
  renderSuggestion: PropTypes.func.isRequired,
  onSuggestionSelected: PropTypes.func.isRequired,
  onChange: PropTypes.func.isRequired,
  onAutosuggestChange: PropTypes.func.isRequired,
  onBlur: PropTypes.func.isRequired,
  onRemoveButtonClick: PropTypes.func.isRequired,
  item: PropTypes.object.isRequired
};

export default ItemRow;
