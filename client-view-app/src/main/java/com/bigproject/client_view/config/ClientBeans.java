package com.bigproject.client_view.config;


import com.bigproject.client_view.client.favouriteClient.RestClientFavouriteProductClient;
import com.bigproject.client_view.client.productClient.RestClientProductsRestClient;
import com.bigproject.client_view.client.reviewClient.RestClientProductsReviewsClient;
import com.bigproject.client_view.security.OAuthClientHttpRequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.DefaultOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizedClientRepository;
import org.springframework.web.client.RestClient;

@Configuration
public class ClientBeans {

    @Bean
    public RestClientProductsRestClient productsRestClient(
            @Value("${ceo.services.catalogue.uri:http://localhost:8081}") String catalogueBaseUri,
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${ceo.service.catalogue.registration-id:keycloak}") String registrationId){
        return new RestClientProductsRestClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(
                        new OAuthClientHttpRequestInterceptor(
                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
                                        authorizedClientRepository),
                               registrationId))
                .build());
    }

    @Bean
    public RestClientFavouriteProductClient favouriteProductRestClient(
            @Value("${ceo.services.feedback.uri:http://localhost:8084}") String catalogueBaseUri,
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${ceo.services.catalogue.registration-id:keycloak}") String registrationId) {
        return new RestClientFavouriteProductClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(
                        new OAuthClientHttpRequestInterceptor(
                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
                                        authorizedClientRepository),
                                registrationId))
                .build());
    }


    @Bean
    public RestClientProductsReviewsClient productReviewsRestClient(
            @Value("${ceo.services.feedback.uri:http://localhost:8084}") String catalogueBaseUri,
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientRepository authorizedClientRepository,
            @Value("${ceo.services.catalogue.registration-id:keycloak}") String registrationId) {
        return new RestClientProductsReviewsClient(RestClient.builder()
                .baseUrl(catalogueBaseUri)
                .requestInterceptor(
                        new OAuthClientHttpRequestInterceptor(
                                new DefaultOAuth2AuthorizedClientManager(clientRegistrationRepository,
                                        authorizedClientRepository),
                                registrationId))
                .build());
    }
}
