import axios from 'axios';

class ProductApi {


  static getAll() {
    return axios.get(getLink());
  }
}
const getLink = () => {
  if (process.env.NODE_ENV === 'production') {
    return 'http://159.203.4.149:8080/api/flatSkus';
  } else return '/api/flatSkus';
};


export default ProductApi;
