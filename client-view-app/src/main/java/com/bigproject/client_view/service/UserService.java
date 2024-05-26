package com.bigproject.client_view.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public String getUser(OAuth2AuthenticationToken authentication) {
        return (String) authentication.getPrincipal().getAttributes().get("preferred_username");
    }
}
