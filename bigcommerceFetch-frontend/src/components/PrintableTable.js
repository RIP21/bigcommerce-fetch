import React, {PropTypes} from 'react';


const PrintableTable = ({items}) => {

  const totalPrice = (Math.round(items.reduce((sum, item) => {
      return sum + (item.price * item.quantity);
    }, 0) * 100) / 100).toString();

  const priceToPrint = totalPrice.substring(totalPrice.indexOf('.') + 1, totalPrice.length).length == 2 || totalPrice === '0' ?
    totalPrice : `${totalPrice}0`;

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
      {items.map(item =>
        <PrintRow key={item.itemId.toString()}
                  item={item}
        />)
      }
      </tbody>
      <caption id="total-caption">Total: ${priceToPrint}</caption>
    </table>
  );
};

PrintableTable.propTypes = {
  items: PropTypes.array.isRequired
};

export default PrintableTable;


const PrintRow = ({item}) => {
  const calculatedPrice = (Math.round((item.price * item.quantity) * 100) / 100).toString();
  const priceToPrint = calculatedPrice.substring(calculatedPrice.indexOf('.') + 1, calculatedPrice.length).length == 2 || calculatedPrice === '0' ?
    calculatedPrice : `${calculatedPrice}0`;

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
      <td>${priceToPrint}</td>
      <td className="qty">{item.quantity}</td>
    </tr>
  );
};

PrintRow.propTypes = {
  item: PropTypes.object.isRequired
};
