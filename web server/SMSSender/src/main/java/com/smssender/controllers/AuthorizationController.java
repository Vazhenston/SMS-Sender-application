package com.smssender.controllers;

import com.smssender.services.AuthorizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
public class AuthorizationController {
    private AuthorizationService authorizationService;

    @Autowired
    public AuthorizationController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public void askUrl(HttpServletResponse response) throws IOException{
        response.sendRedirect(authorizationService.getStartUrl());
    }

    @RequestMapping(value = "Callback", method = RequestMethod.GET)
    public void callback(@RequestParam("code") String code, HttpServletResponse response) throws IOException, GeneralSecurityException {
        authorizationService.setGoogleTokenResponse(code);
        response.sendRedirect("home_page");
    }
}
