package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.OptionSet
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class FetchService {

    @Autowired
    RestTemplate rt;

    public List<Sku> fetchNonDisabledSkis() {
        List<Sku> skus
        skus = fetchAllSkus()
        removeDisabledSku(skus)
        return skus
    }

    private List<Sku> fetchAllSkus() {
        List<Sku> skus = new LinkedList<>()
        String url = "https://store-2e83t.mybigcommerce.com/api/v2/products/skus.json?limit=250&page="
        for (int page = 1; true; page++) {
            def fetchedValues = rt.
                    getForObject(url + page, Sku[].class)
            if (fetchedValues) {
                skus.addAll(fetchedValues)
            } else break
        }
        return skus
    }

    public List<OptionSet> fetchOptionSets(List<Product> products) {
        List<OptionSet> optionSets = new LinkedList<>()
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

    private void removeDisabledSku(List<Sku> skus) {
        skus.removeAll { it.isPurchasingDisabled }
    }

    public List<Product> fetchAllAvailableProducts() {
        String url = "https://store-2e83t.mybigcommerce.com/api/v2/products.json?limit=250&is_visible=true&availability=available&page="
        List<Product> products = new LinkedList<>()
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
