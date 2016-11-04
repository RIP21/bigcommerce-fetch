package com.rip21.bigcommerceFetch.config

import org.apache.http.auth.AuthScope
import org.apache.http.auth.UsernamePasswordCredentials
import org.apache.http.client.HttpClient
import org.apache.http.conn.ssl.AllowAllHostnameVerifier
import org.apache.http.conn.ssl.SSLConnectionSocketFactory
import org.apache.http.conn.ssl.TrustSelfSignedStrategy
import org.apache.http.impl.client.BasicCredentialsProvider
import org.apache.http.impl.client.HttpClientBuilder
import org.apache.http.ssl.SSLContexts
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.ClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

import javax.net.ssl.SSLContext

@Configuration
class HttpConnectionConfig {

    public static final String LOGIN = "ryan"
    public static final String API_KEY = "c80e6d15c6cc70986a16175fb29bc1c1821f6fd6"

    @Bean
    SSLContext sslContext() {
        return SSLContexts.custom().loadTrustMaterial(null,
                new TrustSelfSignedStrategy())
                .build()
    }

    @Bean
    SSLConnectionSocketFactory sslConnectionSocketFactory(SSLContext sslContext) {
        return new SSLConnectionSocketFactory(sslContext,
                new AllowAllHostnameVerifier());
    }

    @Bean
    BasicCredentialsProvider basicCredentialsProvider() {
        BasicCredentialsProvider bcp = new BasicCredentialsProvider()
        bcp.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(LOGIN, API_KEY))
        return bcp

    }

    @Bean
    HttpClient httpClient(SSLConnectionSocketFactory sslConnectionSocketFactory, BasicCredentialsProvider basicCredentialsProvider) {
        return HttpClientBuilder.create()
                .setSSLSocketFactory(sslConnectionSocketFactory)
                .setDefaultCredentialsProvider(basicCredentialsProvider)
                .build();
    }

    @Bean
    ClientHttpRequestFactory clientHttpRequestFactory(HttpClient httpClient) {
        return new HttpComponentsClientHttpRequestFactory(httpClient)
    }

    @Bean
    RestTemplate restTemplate(ClientHttpRequestFactory clientHttpRequestFactory) {
        return new RestTemplate(clientHttpRequestFactory)
    }

}
