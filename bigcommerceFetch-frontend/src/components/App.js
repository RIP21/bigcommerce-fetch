// This component handles the App template used on every page.
import React, {PropTypes} from "react";
import {connect} from 'react-redux';

class App extends React.Component {

  constructor(props, context) {
    super(props, context);
  }


  render() {
    return (
        <div className="container">
          Hello world
        </div>
    );
  }
}


App.propTypes = {
  products: PropTypes.object.isRequired,
};


function mapStateToProps(state) {
  return {
    products: state.products
  };
}

export default connect(mapStateToProps)(App);
