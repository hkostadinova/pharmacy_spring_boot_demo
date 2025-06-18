package com.rewe.pharmacy.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Arrays;

@Slf4j
@RequiredArgsConstructor
@Component
public class Filter1 extends OncePerRequestFilter  {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        final HttpServletRequest httpRequest = request;
        Arrays.asList(httpRequest.getHeaderNames())
                .forEach(headerName -> {
                    log.info("Filter1 : ", headerName, httpRequest.getHeader(String.valueOf(headerName)));
                });
        chain.doFilter(httpRequest, response);
    }
}