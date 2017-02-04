import React, {Component, PropTypes} from "react";
import LoadingBubbles from "./svg/loading-bubbles";

export default class Loading extends Component {
  constructor() {
    super();
    this.state = {
      delayed: false
    };
  }

  componentWillMount() {
    const delayed = this.props.delay > 0;

    if (delayed) {
      this.setState({delayed: true});
      this._timeout = setTimeout(() => {
        this.setState({delayed: false});
      }, this.props.delay);
    }
  }

  componentWillUnmount() {
    this._timeout && clearTimeout(this._timeout);
  }

  render() {
    const className = this.props.className;
    const style = this.props.style;
    return (
      <div className={className} style={style}>
        <LoadingBubbles/>
      </div>
    );
  }
}
Loading.propTypes = {
  style: PropTypes.object,
  className: PropTypes.string,
};

