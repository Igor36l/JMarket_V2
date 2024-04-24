package com.bigproject.manager.config;

import com.bigproject.manager.client.RestClientProductsRestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientProductsRestClient productsRestClient(
            @Value("${ceo.service.catalogue.uri:http://localhost:8081}") String catalogueBaseUri,
            @Value("${ceo.service.catalogue.username:}") String catalogueUsername,
            @Value("${ceo.service.catalogue.password:}") String cataloguePassword){
        return new RestClientProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(
                        new BasicAuthenticationInterceptor(catalogueUsername, cataloguePassword))
                .build());
    }
}
