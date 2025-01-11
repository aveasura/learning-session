package org.learning.session.controller.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;

// @WebFilter("/*") // "выключил" фильтр(аннотацию), так как перешел на thymeleaf, в котором установил кодировку.
public class EncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        servletResponse.setCharacterEncoding("UTF-8");
        servletResponse.setContentType("text/html;charset=UTF-8");

        // Пропускаем запрос дальше по цепочке
        filterChain.doFilter(servletRequest, servletResponse);
    }
}