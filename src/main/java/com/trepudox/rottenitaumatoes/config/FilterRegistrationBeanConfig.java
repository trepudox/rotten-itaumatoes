package com.trepudox.rottenitaumatoes.config;

import com.trepudox.rottenitaumatoes.entrypoint.filter.*;
import com.trepudox.rottenitaumatoes.util.SecurityFilterUtil;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerExceptionResolver;

@Configuration
public class FilterRegistrationBeanConfig {
    //TODO: MAPEAR ENDPOINTS

    @Bean
    public FilterRegistrationBean<FirstFilter> firstFilterRegistration(HandlerExceptionResolver handlerExceptionResolver) {
        FilterRegistrationBean<FirstFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new FirstFilter(handlerExceptionResolver));
        registration.addUrlPatterns("*");
        registration.setOrder(1);

        return registration;
    }

    @Bean
    public FilterRegistrationBean<LeitorAuthFilter> leitorAuthFilterRegistration(SecurityFilterUtil securityFilterUtil) {
        FilterRegistrationBean<LeitorAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new LeitorAuthFilter(securityFilterUtil));
        registration.addUrlPatterns("/users/profile");
        registration.setOrder(2);

        return registration;
    }

    @Bean
    public FilterRegistrationBean<BasicoAuthFilter> basicoAuthFilterRegistration(SecurityFilterUtil securityFilterUtil) {
        FilterRegistrationBean<BasicoAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new BasicoAuthFilter(securityFilterUtil));
        registration.addUrlPatterns("");
        registration.setOrder(3);

        return registration;
    }

    @Bean
    public FilterRegistrationBean<AvancadoAuthFilter> avancadoAuthFilterRegistration(SecurityFilterUtil securityFilterUtil) {
        FilterRegistrationBean<AvancadoAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new AvancadoAuthFilter(securityFilterUtil));
        registration.addUrlPatterns("");
        registration.setOrder(4);

        return registration;
    }

    @Bean
    public FilterRegistrationBean<ModeradorAuthFilter> moderadorAuthFilterRegistration(SecurityFilterUtil securityFilterUtil) {
        FilterRegistrationBean<ModeradorAuthFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new ModeradorAuthFilter(securityFilterUtil));
        registration.addUrlPatterns("/users/give-mod");
        registration.setOrder(5);

        return registration;
    }

}
