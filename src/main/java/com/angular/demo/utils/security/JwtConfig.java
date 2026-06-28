package com.angular.demo.utils.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class JwtConfig {

  @Bean
  @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
  public Jwt currentRequestJwt() {
    return (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
  }
}
