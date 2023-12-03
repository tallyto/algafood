package com.tallyto.algafood.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ApiRetirementHandler apiDeprecationHandler;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedMethods("*");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
       registry.addInterceptor(apiDeprecationHandler);

    }

    @Bean
    public Filter ShallowEtagHeaderFilter() {

        return new ShallowEtagHeaderFilter();
    }
}
