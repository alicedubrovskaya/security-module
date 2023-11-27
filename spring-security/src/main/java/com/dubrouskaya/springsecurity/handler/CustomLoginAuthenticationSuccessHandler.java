package com.dubrouskaya.springsecurity.handler;

import com.dubrouskaya.springsecurity.service.LoginAttemptService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomLoginAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    private LoginAttemptService loginAttemptService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
        User user = (User) authentication.getPrincipal();
        loginAttemptService.loginSuccess(user.getUsername());
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
