package com.svce.shortener;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoggingFilter implements Filter {
    @Autowired
    CustomLoggerService logger;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        logger.log("backend", "info", "api", "Incoming " + req.getMethod() + " " + req.getRequestURI());
        try {
            chain.doFilter(request, response);
        } catch (Exception e) {
            logger.log("backend", "error", "api", "Exception: " + e.getMessage());
            throw e;
        }
    }
}