import React from 'react';
import CondensedItemRow from './CondensedItemRow';
import numeral from 'numeral';

const TableHeader = () => {
  return (
    <thead>
    <tr>
      <th>Item # (SKU)</th>
      <th>Product name/Description</th>
      <th>Price</th>
      <th>Quantity</th>
    </tr>
    </thead>
  );
};

const CondensedItemsTable = ({items, totalPrice, ...rest}) => {
  return (
    <table>
      <TableHeader/>
      <tbody>
      {items.map(item => {
          const calculatedPrice = numeral((item.price * item.quantity).toString()).format('$0,0.00');
          return (

            <CondensedItemRow
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

export default CondensedItemsTable;



