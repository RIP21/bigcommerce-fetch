package com.rip21.bigcommerceFetch

import com.rip21.bigcommerceFetch.domain.OptionSet
import com.rip21.bigcommerceFetch.domain.Product
import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.HttpClient
import org.apache.http.conn.ssl.AllowAllHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.ssl.SSLContexts
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import spock.lang.Specification

import javax.net.ssl.SSLContext

class BigcommerceFetchApplicationTests extends Specification {

    void "Context loads"() {
        given:
            SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(null, new TrustSelfSignedStrategy()).build();
            SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, new AllowAllHostnameVerifier());
            BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
            credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("ryan", "c80e6d15c6cc70986a16175fb29bc1c1821f6fd6"));
            HttpClient httpClient = HttpClientBuilder.create()
                    .setSSLSocketFactory(connectionFactory)
                    .setDefaultCredentialsProvider(credentialsProvider)
                    .build();


            ClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            RestTemplate restTemplate = new RestTemplate(requestFactory);

            def products = new LinkedList<Product>()
            restTemplate.getForObject("https://store-2e83t.mybigcommerce.com/api/v2/products.json", Product[].class).each { product ->
                products.add(product);
            }

            List<Object> optionSets
            products.each { product ->
                def thing;
                product.optionSetId ? thing = restTemplate.getForObject("https://store-2e83t.mybigcommerce.com/api/v2/optionsets/${product.optionSetId}/options.json", OptionSet[].class) : true;
                def t
            }

        expect:
            true == true
    }

}
