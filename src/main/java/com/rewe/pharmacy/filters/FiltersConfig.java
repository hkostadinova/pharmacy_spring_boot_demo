package com.rewe.pharmacy.filters;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;

@Configuration
@RequiredArgsConstructor
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean<Filter1> registrationBeanFilter1(Filter1 filter1) {
        final FilterRegistrationBean<Filter1> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(filter1);
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean<Filter2> registrationBeanFilter2(Filter2 filter2) {
        final FilterRegistrationBean<Filter2> registrationBean =
                new FilterRegistrationBean<>();

        registrationBean.setFilter(filter2);
        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
        return registrationBean;
    }
}
