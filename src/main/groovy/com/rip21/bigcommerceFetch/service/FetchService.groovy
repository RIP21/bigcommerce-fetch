package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.OptionSet
import com.rip21.bigcommerceFetch.domain.Product
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class FetchService {

    @Autowired
    RestTemplate rt;

    public List<Product> fetchProducts() {
        List<Product> products;
        products = fetchAllProducts()
        removeNoneVisibleProducts(products)
        return products;
    }

    public List<OptionSet> fetchOptionSets(List<Product> products) {
        List<OptionSet> optionSets = new LinkedList<OptionSet>()
        for (product in products) {
            if (product.optionSetId != null) {
                OptionSet[] optionSet = rt.
                        getForObject("https://store-2e83t.mybigcommerce.com/api/v2/optionsets/${product.optionSetId}/options.json",
                                OptionSet[].class)
                if (optionSet) optionSets.add(optionSet.first())
            }
        }
        return optionSets
    }

    private void removeNoneVisibleProducts(List<Product> products) {
        products.removeAll { !it.isVisible }
    }

    private List<Product> fetchAllProducts() {
        String url = "https://store-2e83t.mybigcommerce.com/api/v2/products.json?limit=250&page="
        List<Product> products = new LinkedList<Product>()
        for (int page = 1; true; page++) {
            def fetchedValues = rt.
                    getForObject(url + page, Product[].class)
            if (fetchedValues) {
                products.addAll(fetchedValues)
            } else break
        }
        return products
    }
}
