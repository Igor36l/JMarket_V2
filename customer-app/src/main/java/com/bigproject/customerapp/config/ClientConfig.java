package com.bigproject.customerapp.config;

import com.bigproject.customerapp.client.WebClientFavouriteProductsClient;
import com.bigproject.customerapp.client.WebClientProductReviewsClient;
import com.bigproject.customerapp.client.WebClientProductsClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.reactive.function.client.ServerOAuth2AuthorizedClientExchangeFilterFunction;
import org.springframework.security.oauth2.client.web.server.ServerOAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    @Scope("prototype")
    public WebClient.Builder ceoServicesWebClientBuilder(
            ReactiveClientRegistrationRepository clientRegistrationRepository,
            ServerOAuth2AuthorizedClientRepository authorizedClientRepository
    ){
        ServerOAuth2AuthorizedClientExchangeFilterFunction filter = new ServerOAuth2AuthorizedClientExchangeFilterFunction(
                clientRegistrationRepository, authorizedClientRepository);
        filter.setDefaultClientRegistrationId("keycloak");
        return WebClient.builder()
                .filter(filter);
    }


    @Bean
    public WebClientFavouriteProductsClient webClientFavouriteProductsClient(
            @Value("${ceo.services.feedback.uri:http://localhost:8084}")
            String feedbackBaseUrl,
            WebClient.Builder ceoServicesWebClientBuilder
    ){
        return new WebClientFavouriteProductsClient(ceoServicesWebClientBuilder
                .baseUrl(feedbackBaseUrl)
                .build());
    }

    @Bean
    public WebClientProductReviewsClient webClientProductReviewsClient(
            @Value("${ceo.services.feedback.uri:http://localhost:8084}")
            String feedbackBaseUrl,
            WebClient.Builder ceoServicesWebClientBuilder
    ){
        return new WebClientProductReviewsClient(ceoServicesWebClientBuilder
                .baseUrl(feedbackBaseUrl)
                .build());
    }

    @Bean
    public WebClientProductsClient webClientProductsClient(
            @Value("${ceo.services.catalogue.uri:http://localhost:8081}")
            String catalogueBaseUrl,
            WebClient.Builder ceoServicesWebClientBuilder
    ){
        return new WebClientProductsClient(ceoServicesWebClientBuilder
                .baseUrl(catalogueBaseUrl)
                .build());
    }


}
