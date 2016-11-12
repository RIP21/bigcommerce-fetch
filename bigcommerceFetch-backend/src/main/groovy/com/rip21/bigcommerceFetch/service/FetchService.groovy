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

    LinkedList<String> failedLinks = new LinkedList<>();

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
            try {
                def fetchedValues = rt.
                        getForObject(url + page, Sku[].class)
                if (fetchedValues) {
                    skus.addAll(fetchedValues)
                } else break
            } catch (Exception e) {
                println "Cannot retrieve SKUs using this link $url$page"
                e.printStackTrace()
                failedLinks << url + page
            }
        }
        return skus
    }

    public List<OptionSet> fetchOptionSets(List<Product> products) {
        List<OptionSet> optionSets = new LinkedList<>()
        for (product in products) {
            if (product.optionSetId != null) {
                try {
                    OptionSet[] optionSet = rt.
                            getForObject("https://store-2e83t.mybigcommerce.com/api/v2/optionsets/${product.optionSetId}/options.json",
                                    OptionSet[].class)
                    if (optionSet) optionSets.add(optionSet.first())
                } catch (Exception e) {
                    println "Cannot retrieve OptionSet using this link https://store-2e83t.mybigcommerce.com/api/v2/optionsets/${product.optionSetId}/options.json"
                    e.printStackTrace()
                    failedLinks << "https://store-2e83t.mybigcommerce.com/api/v2/optionsets/${product.optionSetId}/options.json"
                }
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

    public List<Object> refetch(List<String> failedLinks = this.failedLinks) {
        List<Object> refetchedItems = new LinkedList<>()
        failedLinks.each { String link ->
            switch (link) {
                case { link.contains("products.json") }:
                    try {
                        def fetchedValues = rt.
                                getForObject(link, Product[].class)
                        if (fetchedValues) {
                            refetchedItems.addAll(fetchedValues)
                        }
                        break
                    } catch (Exception e) {
                        println " Again cannot retrieve Products using this link $link"
                        e.printStackTrace()
                        break
                    }
                case { link.contains("optionsets") }:
                    try {
                        OptionSet[] optionSet = rt.
                                getForObject(link, OptionSet[].class)
                        if (optionSet) refetchedItems.add(optionSet.first())
                        break
                    } catch (Exception e) {
                        println "Again cannot retrieve OptionSet using this link $link"
                        e.printStackTrace()
                        break
                    }
                case { link.contains("skus.json") }:
                    try {
                        def fetchedValues = rt.
                                getForObject(link, Sku[].class)
                        if (fetchedValues) {
                            refetchedItems.addAll(fetchedValues)
                        }
                        break
                    } catch (Exception e) {
                        println "Again cannot retrieve SKUs using this link $link"
                        e.printStackTrace()
                        break
                    }
            }
        }

        failedLinks.clear()
        println("${refetchedItems.size()} items has been refetched")

        return refetchedItems
    }
}


