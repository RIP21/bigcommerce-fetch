package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import org.springframework.stereotype.Service

@Service
class FilteringService {

    private void removeNoneVisibleProducts(List<Product> products) {
        products.removeAll { !it.isVisible }
    }

    private void removeDisabledSku(List<Sku> skus) {
        skus.removeAll { it.isPurchasingDisabled }
    }

}
