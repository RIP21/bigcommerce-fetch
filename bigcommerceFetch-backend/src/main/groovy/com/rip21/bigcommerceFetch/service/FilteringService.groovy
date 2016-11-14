package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.domain.FlatSku
import org.springframework.stereotype.Service

@Service
class FilteringService {

    List<FlatSku> filterInvalidRecords(List<FlatSku> list) {
        List<FlatSku> filtered = new LinkedList<>(list);
        filtered.retainAll { it.skuOptionValueId && it.productId && !it.isPurchasingDisabled && it.isVisible }
        return filtered
    }

}
