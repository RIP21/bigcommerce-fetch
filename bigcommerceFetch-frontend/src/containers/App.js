import React, {PropTypes} from "react";
import {connect} from 'react-redux';
import ItemRow from '../components/ItemRow';
import ModalLoading from '../components/ModalLoading';
import {bindActionCreators} from 'redux';
import * as itemActions from '../actions/itemActions';
import objectAssign from 'object-assign';
import {sortItemsByIdSelector} from "../selector/selectors";
import jquery from 'jquery';


// Teach Autosuggest how to calculate suggestions for any given input value.

// When suggestion is clicked, Autosuggest needs to populate the input element
// based on the clicked suggestion. Teach Autosuggest how to calculate the
// input value for every given suggestion.

const getSuggestionValue = suggestion => suggestion.sku;

/*eslint-disable react/no-multi-comp*/
const renderSuggestion = suggestion => (
  <div>
    {suggestion.sku}
  </div>
);


class App extends React.Component {

  constructor(props, context) {
    super(props, context);

    this.state = {
      value: "",
      show: false,
      now: 0,
      totalRequests: 0,
      suggestions: []
    };
  }

  onModalRedirect = () => {
    this.setState({show: false});
    window.location = "/cart.php";
  };

  onModalClose = () => {
    this.setState({show: false});
  };

  onQuantityChange = (event, item) => {
    const changedItem = objectAssign({}, item, {quantity: event.target.value});
    this.props.actions.updateItem(changedItem);
  };


  getSuggestions = value => {
    const inputValue = value.trim().toUpperCase();
    const inputLength = inputValue.length;
    return inputLength === 0 ? [] : this.props.products.filter(product => product.sku.includes(inputValue)).slice(0, 10);
  };

  onAutosuggestChange = (event, newValue, item) => {
    const changedItem = objectAssign({}, item, {value: newValue});
    this.props.actions.updateItem(changedItem);
  };


  onAutosuggestBlur = (item) => {
    const {value} = item;
    const {products} = this.props;
    if (value && products) {
      const matchedProduct = products.find(product => product.sku == value.toUpperCase());
      if (matchedProduct) {
        this.fetchPrice(matchedProduct, item, this.refreshItem);
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
      optionValue: '',
      tinyImg: '',
      standardImg: ''
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

  refreshItem = (data, newItem, item) => {
    const changedItem = objectAssign({}, item, {
      disabled: false,
      value: newItem.sku,

      sku: newItem.sku,
      skuId: newItem.skuId,
      skuOptionValueId: newItem.skuOptionValueId,
      skuProductOptionId: newItem.skuProductOptionId,
      productId: newItem.productId,
      productName: newItem.productName,
      optionValue: newItem.value,
      tinyImg: newItem.tinyImg,
      standardImg: newItem.standardImg,
      price: data.details.unformattedPrice
    });
    this.props.actions.updateItem(changedItem);
  };

  onSuggestionSelected = (event, suggestion, item) => {
    this.fetchPrice(suggestion, item, this.refreshItem);
    this.props.actions.addNewRow();
  };

  fetchPrice = (newItem, item, callback) => {
    let query = {};
    query.action = "add";
    query.product_id = newItem.productId;
    query.variation_id = '';
    query.currency_id = '';
    query["attribute[" + newItem.skuProductOptionId + "]"] = '';
    query["attribute[" + newItem.skuProductOptionId + "]"] = newItem.skuOptionValueId;
    query.qty = '1';
    jquery.ajax({
      type: 'POST',
      url: 'http://taipancanada.com/remote.php',
      data: query,
      success: (data) => {
        callback(data, newItem, item);
      }
    });
  };


  onAddButtonClick = () => {
    this.props.actions.addNewRow();
  };

  onRemoveButtonClick = (item) => {
    const {items, actions} = this.props;
    if (items.length != 1) actions.removeItem(item);
  };


  addAllToCart = () => {
    const {items} = this.props;
    let queue = this.buildRequestsQueue(items);
    if (queue.length > 0) {
      this.showModal();
      this.setState({totalRequests: queue.length});
      this.processRequest(queue);
      this.props.actions.clearItems();
    } else {
      alert("NO items to add to cart");
    }
  };

  showModal = () => {
    this.setState({show: true});
  };

  buildRequestsQueue = (items) => {
    let queries = [];
    items.filter(item => item.quantity != 0 && item.disabled != true).map(validItem => {
      let query = {};
      query.action = "add";
      query["attribute[" + validItem.skuProductOptionId + "]"] = validItem.skuOptionValueId;
      query.product_id = validItem.productId;
      query.qty = validItem.quantity;
      queries.push(query);
    });
    return queries;
  };


  processRequest = (queries) => {
    this.visualizeProgress(queries);
    if (queries.length > 0) {
      jquery.ajax({
        type: 'POST',
        url: window.location.origin + '/cart.php',
        data: queries.pop(),
        success: () => {
          this.processRequest(queries);
        },
        error: () => {
          alert("There is problem adding items to the cart, please try again.");
          this.onModalClose();
        }
      });
    }
  };

  visualizeProgress = (queries) => {
    const {totalRequests} = this.state;
    this.setState({now: Math.floor((((totalRequests - queries.length) / totalRequests) * 100))});
  };

  render() {
    const {suggestions, now, show} = this.state;
    return (
      <div>
        <ModalLoading now={now}
                      show={show}
                      onClose={this.onModalClose}
                      onRedirect={this.onModalRedirect}/>
        <table id="order-form">
          <thead>
          <tr>
            <th/>
            <th>Item # (SKU)</th>
            <th>Product name</th>
            <th>Description</th>
            <th>Price</th>
            <th>Quantity</th>
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
              onChange={this.onQuantityChange}
              onAutosuggestChange={this.onAutosuggestChange}
              onBlur={this.onAutosuggestBlur}
              onRemoveButtonClick={this.onRemoveButtonClick}
            />
          )
          }
          </tbody>
        </table>
        <div id="buttons-block">
          <button id="cart-btn" onClick={this.onAddButtonClick} className="btn btn-danger">Add item</button>
          <br/>
          <button id="cart-btn" onClick={this.addAllToCart} className="btn btn-danger">Add all to cart</button>
        </div>
      </div >
    );
  }
}


App.propTypes = {
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

export default connect(mapStateToProps, mapDispatchToProps)(App);
