import React from 'react';
import CondensedItemsTable from './CondensedItemsTable';
import ItemsTable from './ItemsTable';

const QuickOrderTable = ({dimensions, ...rest}) => {
  if (dimensions.width > 900) {
    return <div><ItemsTable {...rest}/></div>
  } else {
    return <div><CondensedItemsTable {...rest}/></div>
  }
};

export default QuickOrderTable;
