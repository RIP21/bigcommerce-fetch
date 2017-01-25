export const ITEM = {
  itemId: 0,
  value: '',
  quantity: 1,
  disabled: true,
  dateCreated: new Date(),

  sku: '',
  skuId: '',
  skuOptionValueId: '',
  skuProductOptionId: '',
  productId: '',
  productName: '',
  optionValue: '',
  tinyImg: '',
  standardImg: '',
  price: '0'
};

/*export const ITEM = {
  itemId: 0,
  value: 'ET-33333',
  quantity: 7,
  disabled: true,
  dateCreated: new Date(),

  sku: 'ET-33333',
  skuId: 'ET-33333',
  skuOptionValueId: '',
  skuProductOptionId: '',
  productId: 'A ID OF PRODUCT',
  productName: 'AWESOME THING HERE IS DEVICE',
  optionValue: 'Extra awesome sized 30mm x 65mm thing.',
  tinyImg: 'https://cdn6.bigcommerce.com/s-2e83t/products/3408/images/540/91_large__90199.1463003823.50.50.jpg?c=2',
  standardImg: 'https://cdn6.bigcommerce.com/s-2e83t/products/3408/images/540/91_large__90199.1463003823.451.416.jpg?c=2',
  price: '2.333333348'
 };*/

export const STYLE_FOR_PRINT = `<style>

#header, #footer {
    width: 210mm;
}

#header {
    position: static;
    height: auto;
    padding-bottom: 30px;
}

#footer {
    padding-top: 15mm;
    position: static;
    height: auto;
    bottom: 0;
}

img {
    width: 100%;
}

html, body {
    width: 210mm;
    min-height: 297mm;
    /* to centre page on screen*/
    margin-left: auto;
    margin-right: auto;
}

body {
    font-size: 14px;
    font-family: "Lato", "Helvetica Neue", Helvetica, Arial, Sans-Serif;
    font-weight: normal;
    position: relative;
}

#print-table {
    width: 100%;
    margin: auto;
    text-align: center;
    border-spacing: 0;
}

#print-table td, #print-table th {
    border-left: none !important;
    border-right: none !important;
    border-bottom: 1px #ccc solid;
    min-height: 41px;
}

#print-table th {
    border-top: 3px solid;
    border-bottom: 3px solid;
}

#total-caption {
    padding-top: 25px;
    font-weight: bold;
    caption-side: bottom;
    text-align: right;
}

.text-cell {
    text-align: left;
}

</style>`;
