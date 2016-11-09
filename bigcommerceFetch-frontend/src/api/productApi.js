import axios from 'axios';

class ProductApi {

  static getAll() {
    return axios.get('api/flattenedProducts');
  }
}

export default ProductApi;
