package com.rewe.pharmacy.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@Component
public class ServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        Arrays.asList(httpRequest.getHeaderNames())
                .forEach(headerName -> {
                    log.info("Http Header: ", headerName, httpRequest.getHeader(String.valueOf(headerName)));
                });
       chain.doFilter(httpRequest, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
