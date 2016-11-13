import axios from 'axios';

class ProductApi {

  static getAll() {
    return axios.get('http://138.197.134.10:8080/api/flattenedProducts');
  }
}

export default ProductApi;
