import React, {PropTypes} from 'react';
import numeral from 'numeral';

const PrintableTable = ({items, totalPrice}) => {
  return (
    <table id="print-table">
      <h1>Order</h1>
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
      <tbody>
      {items.map(item => {
        debugger;
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
