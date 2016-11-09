// This component handles the App template used on every page.
import React, {PropTypes} from "react";
import {connect} from 'react-redux';
import Autosuggest from 'react-autosuggest';


// Teach Autosuggest how to calculate suggestions for any given input value.


// When suggestion is clicked, Autosuggest needs to populate the input element
// based on the clicked suggestion. Teach Autosuggest how to calculate the
// input value for every given suggestion.
const getSuggestionValue = suggestion => suggestion.sku;

// Use your imagination to render suggestions.
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
      selected: {optionValue: ""}
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


  onChange = (event, {newValue}) => {
    this.setState({
      value: newValue
    });
  };

// Autosuggest will call this function every time you need to update suggestions.
  // You already implemented this logic above, so just use it.
  onSuggestionsFetchRequested = ({value}) => {
    this.setState({
      suggestions: this.getSuggestions(value)
    });
  };

  // Autosuggest will call this function every time you need to clear suggestions.
  onSuggestionsClearRequested = () => {
    this.setState({
      suggestions: []
    });
  };


  render() {
    const {value, suggestions} = this.state;

    // Autosuggest will pass through all these props to the input element.
    const inputProps = {
      placeholder: 'Type sku of the product',
      value,
      onChange: this.onChange
    };
    return (
      <div>
        <Autosuggest
          suggestions={suggestions}
          onSuggestionsFetchRequested={this.onSuggestionsFetchRequested}
          onSuggestionsClearRequested={this.onSuggestionsClearRequested}
          shouldRenderSuggestions={this.shouldRenderSuggestions}
          getSuggestionValue={getSuggestionValue}
          renderSuggestion={renderSuggestion}
          inputProps={inputProps}
        />
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
