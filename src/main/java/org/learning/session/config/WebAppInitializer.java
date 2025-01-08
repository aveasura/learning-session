package org.learning.session.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return null; // Если нет корневой конфигурации
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebConfig.class}; // Конфигурация MVC
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"}; // Все запросы перенаправляются в DispatcherServlet
    }
}