package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import com.rip21.bigcommerceFetch.domain.Value
import com.sun.javaws.exceptions.InvalidArgumentException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class GenericFetchService {

    @Autowired
    RestTemplate rt;

    List<String> failedLinks = new LinkedList<>();

    public List fetch(Class aClass) {

        List items = new LinkedList<>()
        String url = resolveUrl(aClass)

        for (int page = 1; true; page++) {
            try {
                def fetchedItems = rt.getForObject(url + page, Class.forName("[L${aClass.getName()};"))
                if (fetchedItems) {
                    items.addAll(fetchedItems)
                } else break
            } catch (Exception e) {
                println "Cannot retrieve ${aClass.getSimpleName()} using this link $url$page"
                e.printStackTrace()
                failedLinks << url + page
            }
        }

        return items
    }

    public String resolveUrl(Class aClass) {
        switch (aClass) {
            case Sku:
                return "https://store-2e83t.mybigcommerce.com/api/v2/products/skus.json?limit=250&page="
                break
            case Product:
                return "https://store-2e83t.mybigcommerce.com/api/v2/products.json?limit=250&page="
                break
            case Value:
                return "https://store-2e83t.mybigcommerce.com/api/v2/options/values.json?limit=250&page="
                break
            default:
                throw new InvalidArgumentException("Please put Sku, Product and Value classes only")
        }
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
            try {
                def fetchedValues = rt.
                        getForObject(url + page, Product[].class)
                if (fetchedValues) {
                    products.addAll(fetchedValues)
                } else break
            } catch (Exception e) {
                println "Cannot retrieve Products using this link $url$page"
                e.printStackTrace()
                failedLinks << url + page
            }
        }
        return products
    }
}
