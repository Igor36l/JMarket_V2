package com.bigproject.manager.service;

import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class UserService {

    public String getUser(OAuth2AuthenticationToken authentication) {
        return (String) authentication.getPrincipal().getAttributes().get("preferred_username");
    }
}
