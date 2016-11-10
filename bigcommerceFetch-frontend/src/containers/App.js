import React, {PropTypes} from "react";
import {connect} from 'react-redux';
import Autosuggest from 'react-autosuggest';


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

class App extends React.Component {

  constructor(props, context) {
    super(props, context);

    this.state = {
      value: '',
      suggestions: [],
      productName: '',
      description: '',
      price: '',
      quantity: 0,
      disabled: true
    };

  }

  getSuggestions = value => {
    const inputValue = value.trim().toUpperCase();
    const inputLength = inputValue.length;
    return inputLength === 0 ? [] : this.props.products
      .filter(product => product.sku != null)
      .filter(product =>
      product.sku.toUpperCase().slice(0, inputLength) === inputValue).slice(0, 10);
  };


  onAutosuggestChange = (event, {newValue}) => {
    this.setState({
      value: newValue
    });
  };

  onChange = (event) => {
    let quantity = event.target.value;
    return this.setState({quantity: quantity});
  };

  getPrice = (suggestion) => {
    return suggestion.skuPrice ? suggestion.skuPrice : suggestion.productPrice;
  };

  onBlur = () => {
    const {value} = this.state;
    const {products} = this.props;
    if (value && products) {
      const matchedProduct = products.find(product => product.sku == value);
      if (matchedProduct) {
        this.setState({
          productName: matchedProduct.productName,
          description: matchedProduct.optionValue,
          price: this.getPrice(matchedProduct),
          disabled: false
        });
      }
    }
  };


// Autosuggest will call this function every time you need to update suggestions.
// You already implemented this logic above, so just use it.
  onSuggestionsFetchRequested = ({value}) => {
    this.setState({
      suggestions: this.getSuggestions(value),
      disabled: true,
      productName: '',
      description: '',
      price: ''
    });
  };

// Autosuggest will call this function every time you need to clear suggestions.
  onSuggestionsClearRequested = () => {
    this.setState({
      suggestions: []
    });
  };

  onSuggestionSelected = (event, {suggestion}) => {
    this.setState({
      productName: suggestion.productName,
      description: suggestion.optionValue,
      price: this.getPrice(suggestion),
      disabled: false
    });
  };


  render() {
    const {value, suggestions, productName, description, price, quantity, disabled} = this.state;

    // Autosuggest will pass through all these props to the input element.
    const inputProps = {
      placeholder: 'Type sku of the product',
      value,
      onChange: this.onAutosuggestChange,
      onBlur: this.onBlur
    };
    return (
      <div className="container">
        <table className="table">
          <thead>
          <tr>
            <th>SKU of Item</th>
            <th>Product Name:</th>
            <th>Description:</th>
            <th>Price:</th>
            <th>Quantity:</th>
          </tr>
          </thead>
          <tbody>
          <tr>
            <td><Autosuggest
              suggestions={suggestions}
              onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
              onSuggestionsClearRequested={this.onSuggestionsClearRequested}
              getSuggestionValue={getSuggestionValue}
              renderSuggestion={renderSuggestion}
              onSuggestionSelected={this.onSuggestionSelected}
              inputProps={inputProps}
            /></td>
            <td>{productName}</td>
            <td>{description}</td>
            <td>{price}</td>
            <td><input disabled={disabled} min="0" max="100000" type="number" onChange={this.onChange}
                       value={quantity}/></td>
          </tr>
          </tbody>
        </table>
        <button className="btn btn-primary">Add item</button>
      </div>
    );
  }
}


App.propTypes = {
  products: PropTypes.array.isRequired
};


function mapStateToProps(state) {
  return {
    products: state.products
  };
}

export default connect(mapStateToProps)(App);
