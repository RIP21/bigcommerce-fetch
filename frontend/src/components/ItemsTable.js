import React from 'react';
import ItemRow from './ItemRow';
import numeral from 'numeral';

const TableHeader = () => {
  return (
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
  );
};

const ItemsTable = ({items, totalPrice, ...rest }) => {
  return (
    <table id="order-form">
      <TableHeader/>
      <tbody>
      {items.map(item => {
          const calculatedPrice = numeral((item.price * item.quantity).toString()).format('$0,0.00');
          return (<ItemRow
            key={item.itemId.toString()}
            {...rest}
            item={item}
            totalPrice={calculatedPrice}
          />);
        }
      )
      }
      </tbody>
      <caption id="total-caption">Total: {totalPrice}</caption>
    </table>
  );
};

export default ItemsTable;
