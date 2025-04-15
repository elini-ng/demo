package com.estsoft.demo.filter;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Filter configurations
 * - Filter register into bean
 * - Filter chain sequence
 * - URL pattern
 */
@Configuration
public class FilterConfig {

    @Bean

    public FilterRegistrationBean<Filter> firstFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new FirstFilter());
        filter.setOrder(1);
        filter.addUrlPatterns("/test");

        return filter;
    }

    @Bean
    public FilterRegistrationBean<Filter> secondFilter() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new SecondFilter());
        filter.setOrder(2);
        filter.addUrlPatterns("/test");

        return filter;
    }
}
