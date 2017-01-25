package com.rip21.bigcommerceFetch.service

import com.rip21.bigcommerceFetch.dao.FlatSkuRepository
import com.rip21.bigcommerceFetch.domain.FlatSku
import com.rip21.bigcommerceFetch.domain.Product
import com.rip21.bigcommerceFetch.domain.Sku
import com.rip21.bigcommerceFetch.domain.Value
import groovy.util.logging.Log4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@Log4j
class SchedulerService {

    @Autowired
    GenericFetchService fetchService

    @Autowired
    FlattenerService flattenerService

    @Autowired
    FilteringService filteringService

    @Autowired
    FlatSkuRepository flatSkuRepository


    void updateItems() {
        List<FlatSku> flatSkus
        log.info("Data starts to fetch")
        try {
            flatSkus = fetchAndFilter()
            log.info("FlatSkus: ${flatSkus.size()}")
            if (flatSkus) {
                cleanAndPersist(flatSkus)
                log.info("Data fetched successfully")
            } else throw new Exception("FlatSkus list is empty!")
        }
        catch (e) {
            log.error("Error when update cache!", e)
        }
    }

    private List<FlatSku> fetchAndFilter() {
        List<FlatSku> flatSkus
        flatSkus = filteringService.filterInvalidRecords(
                flattenerService.flatten(
                        fetchService.fetch(Sku.class),
                        fetchService.fetch(Product.class),
                        fetchService.fetch(Value.class)
                ))
        flatSkus
    }

    private void cleanAndPersist(List<FlatSku> flatSkus) {
        flatSkuRepository.deleteAll()
        flatSkuRepository.save(flatSkus)
    }

}
