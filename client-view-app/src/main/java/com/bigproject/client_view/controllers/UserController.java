package com.bigproject.client_view.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/user")
    public String getUser(Model model, OAuth2AuthenticationToken authentication) {

        model.addAttribute("name", authentication.getPrincipal().getAttributes().get("preferred_username"));
        model.addAttribute("email", authentication.getPrincipal().getAttributes().get("email"));

        return "/users/user-info";
    }
}