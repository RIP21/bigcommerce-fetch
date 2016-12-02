export const ITEM = {
  itemId: 0,
  value: '',
  quantity: 0,
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

export const STYLE_FOR_PRINT = `<style>
   #print-table {
        width: 100%;
        margin: auto;
        text-align: center;

      }

        #print-table td, #print-table th {
        border-left: none !important;
        border-right: none !important;
      }

        #print-table th {
        border-top: 3px solid;
        border-bottom: 3px solid;
      }
        #total-caption {

        padding-top: 25px;
        font-weight: bold;
        font-size: 16px;
        caption-side: bottom;
        text-align: right;
      }

      </style>`;
