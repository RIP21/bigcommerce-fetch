package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import com.rip21.bigcommerceFetch.domain.Value
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
@Log4j
class GenericFetchService {

    @Autowired
    RestTemplate rt;

    private List<String> failedLinks = new LinkedList<>();

    public List fetch(Class aClass) {
        List items = new LinkedList<>()
        for (int page = 1; true; page++) {
            def fetchedItems = doRequest(resolveUrl(aClass) + page, aClass)
            if (fetchedItems) {
                items.addAll(fetchedItems)
            } else break
        }
        return items + refetch(aClass)
    }

    private Object doRequest(String url, Class aClass) {
        try {
            rt.getForObject(url, Class.forName("[L${aClass.getName()};"))
        } catch (Exception e) {
            log.error("Cannot retrieve ${aClass.getSimpleName()} using this link $url", e)
            failedLinks << url
        }
    }

    private String resolveUrl(Class aClass) {
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
                throw new Exception("Please put Sku, Product and Value classes only")
        }
    }

    private List refetch(Class aClass) {
        def refetchedItems = this.failedLinks.collect { failedLink -> doRequest(failedLink, aClass) }
        this.failedLinks.clear()
        log.info("${refetchedItems.size()} items has been refetched")

        return refetchedItems.flatten()
    }


}
