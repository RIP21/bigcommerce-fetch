import React, {PropTypes} from 'react';
import numeral from 'numeral';
import '../images/Header.PNG';
import '../images/Footer.png';

const PrintableTable = ({items, totalPrice}) => {

  return (
    <div id="print-me">
      <img id="header" src="http://cdn5.bigcommerce.com/s-2e83t/templates/__custom/images/QuickHeader.png"/>
      <table id="print-table">
        <thead>
        <tr>
          <th/>
          <th>Item # (SKU)</th>
          <th>Product name</th>
          <th>Description</th>
          <th>Price</th>
          <th>Quantity</th>
        </tr>
        </thead>
        <tfoot>
        <tr>
          <td id="spacer"/>
        </tr>
        </tfoot>
        <tbody>
        {items.map(item => {
          const calculatedPrice = numeral((item.price * item.quantity).toString()).format('$0,0.00');
          return (<PrintRow key={item.itemId.toString()}
                            item={item}
                            totalPrice={calculatedPrice}
          />);
        })
        }
        </tbody>
        <caption id="total-caption">Total: {totalPrice}</caption>
      </table>
      <img id="footer" src="http://cdn5.bigcommerce.com/s-2e83t/templates/__custom/images/QuickFooter.png"/>
    </div>
  );
};

PrintableTable.propTypes = {
  items: PropTypes.array.isRequired,
  totalPrice: PropTypes.string.isRequired
};

export default PrintableTable;


const PrintRow = ({item, totalPrice}) => { //eslint-disable-line
  return (
    <tr>
      <td>
        {item.tinyImg ?
          <div id="showthumb">
            <img src={item.tinyImg} className="skuimg"/>
          </div>
          : <div/>
        }
      </td>
      <td className="autosuggest-cell"> {item.sku}</td>
      <td className="text-cell">{item.productName}</td>
      <td className="text-cell">{item.optionValue}</td>
      <td>{totalPrice}</td>
      <td className="qty">{item.quantity}</td>
    </tr>
  );
};

PrintRow.propTypes = {
  item: PropTypes.object.isRequired,
  totalPrice: PropTypes.string.isRequired
};
