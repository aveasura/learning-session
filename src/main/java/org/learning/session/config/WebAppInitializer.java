package org.learning.session.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        // return null - если нет корневой конфигурации
        return new Class[]{AppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // Конфигурация MVC
        return new Class[]{WebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // Все запросы перенаправляются в DispatcherServlet
        return new String[]{"/"};
    }
}