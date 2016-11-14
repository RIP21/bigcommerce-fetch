import React, {PropTypes} from "react";
import {connect} from 'react-redux';
import ItemRow from '../components/ItemRow';
import {bindActionCreators} from 'redux';
import * as itemActions from '../actions/itemActions';
import objectAssign from 'object-assign';
import * as Empty from '../constants/emptyEntities';
import {sortItemsByIdSelector} from "../selector/selectors";
import jquery from 'jquery';


// Teach Autosuggest how to calculate suggestions for any given input value.

// When suggestion is clicked, Autosuggest needs to populate the input element
// based on the clicked suggestion. Teach Autosuggest how to calculate the
// input value for every given suggestion.
const getSuggestionValue = suggestion => suggestion.sku;

const renderSuggestion = suggestion => (
  <div>
    {suggestion.sku}
  </div>
);

class AppRedux extends React.Component {

  constructor(props, context) {
    super(props, context);

    this.state = {
      suggestions: [],
    };
  }

  getSuggestions = value => {
    const inputValue = value.trim().toUpperCase();
    const inputLength = inputValue.length;
    return inputLength === 0 ? [] : this.props.products.filter(product => product.sku.includes(value)).slice(0, 10);
    /*  .filter(product =>
     product.sku.toUpperCase().slice(0, inputLength) === inputValue).slice(0, 10);*/
  };


  onAutosuggestChange = (event, newValue, item) => {
    const changedItem = objectAssign({}, item, {value: newValue});
    this.props.actions.updateItem(changedItem);
  };

  onChange = (event, item) => {
    const changedItem = objectAssign({}, item, {quantity: event.target.value});
    this.props.actions.updateItem(changedItem);
  };


  onBlur = (item) => {
    const {value} = item;
    const {products} = this.props;
    if (value && products) {
      const matchedProduct = products.find(product => product.sku == value);
      if (matchedProduct) {
        const changedItem = objectAssign({}, item, {
          disabled: false,

          sku: matchedProduct.sku,
          skuId: matchedProduct.skuId,
          optionValue: matchedProduct.value,
          skuOptionValueId: matchedProduct.skuOptionValueId,
          skuProductOptionId: matchedProduct.skuProductOptionId,
          productId: matchedProduct.productId,
          productName: matchedProduct.productName
        });
        this.props.actions.updateItem(changedItem);
      } else this.disableAndClean(item);
    } else this.disableAndClean(item);
  };

  disableAndClean = (item) => {
    const changedItem = objectAssign({}, item, {
      disabled: true,

      sku: '',
      skuId: '',
      skuOptionValueId: '',
      skuProductOptionId: '',
      productId: '',
      productName: '',
      optionValue: ''
    });
    this.props.actions.updateItem(changedItem);
  };


// Autosuggest will call this function every time you need to update suggestions.
// You already implemented this logic above, so just use it.
  onSuggestionsFetchRequested = ({value}) => {
    this.setState({
      suggestions: this.getSuggestions(value),
    });
  };

// Autosuggest will call this function every time you need to clear suggestions.
  onSuggestionsClearRequested = () => {
    this.setState({
      suggestions: []
    });
  };

  onSuggestionSelected = (event, suggestion, item) => {
    const changedItem = objectAssign({}, item, {
      disabled: false,
      value: suggestion.sku,

      sku: suggestion.sku,
      skuId: suggestion.skuId,
      skuOptionValueId: suggestion.skuOptionValueId,
      skuProductOptionId: suggestion.skuProductOptionId,
      productId: suggestion.productId,
      productName: suggestion.productName,
      optionValue: suggestion.value
    });
    this.props.actions.updateItem(changedItem);
  };

  onButtonClick = () => {
    const newItem = objectAssign({}, Empty.ITEM, {itemId: this.generateId(), dateCreated: new Date()});
    this.props.actions.addItem(newItem);
  };

  onRemoveButtonClick = (item) => {
    const {items, actions} = this.props;
    if (items.length != 1) actions.removeItem(item);
  };

  generateId() {
    return Math.max.apply(Math, this.props.items.map(item => item.itemId)) + 1;
  }

  addToCart = () => {
    const {items} = this.props;
    let queries = [];
    items.filter(item => item.quantity != 0 && item.disabled != true).map(validItem => {
      let query = {};
      query.action = "add";
      query["attribute[" + validItem.skuProductOptionId + "]"] = validItem.skuOptionValueId;
      query.product_id = validItem.productId;
      query.qty = validItem.quantity;
      queries.push(query);
    });

    if (queries.length > 0) {
      this.processRequest(queries);
      alert("All items ADDED to the cart.");
      this.props.actions.clearItems();
    } else {
      alert("NO items to add to cart");
    }

  };


  processRequest = (queries) => {
    if (queries.length > 0) {
      jquery.ajax({
        type: 'POST',
        url: window.location.origin + '/cart.php',
        data: queries.pop(),
        success: () => {
          this.processRequest(queries);
        }
      });
    }
  };

  render() {
    const {suggestions} = this.state;
    return (
      <div>
        <table width="100%">
          <thead>
          <tr>
            <th>SKU of Item</th>
            <th>Product Name:</th>
            <th>Description:</th>
            <th>Quantity:</th>
            <th/>
          </tr>
          </thead>
          <tbody>
          {this.props.items.map(item =>
            <ItemRow
              key={item.itemId.toString()}
              suggestions={suggestions}
              onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
              onSuggestionsClearRequested={this.onSuggestionsClearRequested}
              getSuggestionValue={getSuggestionValue}
              renderSuggestion={renderSuggestion}
              onSuggestionSelected={this.onSuggestionSelected}
              item={item}
              onChange={this.onChange}
              onAutosuggestChange={this.onAutosuggestChange}
              onBlur={this.onBlur}
              onRemoveButtonClick={this.onRemoveButtonClick}
            />
          )
          }
          </tbody>
        </table>
        <button style={{marginBottom: "5px"}} onClick={this.onButtonClick} className="btn btn-danger">Add item</button>
        <button onClick={this.addToCart} className="btn btn-danger">Add all to cart</button>
      </div>
    );
  }
}


AppRedux.propTypes = {
  products: PropTypes.array.isRequired,
  items: PropTypes.array.isRequired,
  actions: PropTypes.object.isRequired
};


function mapStateToProps(state) {
  return {
    products: state.products,
    items: sortItemsByIdSelector(state)
  };
}

function mapDispatchToProps(dispatch) {
  return {
    actions: bindActionCreators(itemActions, dispatch),
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(AppRedux);
