import axios from 'axios';

class ProductApi {

  static getAll() {
    return axios.get('/flattenedProducts');
  }
}

export default ProductApi;
