package com.estsoft.demo.filter;

import jakarta.servlet.FilterConfig;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Write RequestURI info into log
 */
@Slf4j
public class FirstFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("FirstFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException{
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String requestURI = request.getRequestURI();

        log.info("FirstFilter requestURI: {}", requestURI);

        filterChain.doFilter(servletRequest, servletResponse);

        log.info("FirstFilter response");
    }

    @Override
    public void destroy() {
        log.info("FirstFilter destroy()");
    }
}
