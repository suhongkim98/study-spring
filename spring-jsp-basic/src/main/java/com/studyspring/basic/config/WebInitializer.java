package com.studyspring.basic.config;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;
//web.xml -->webInitializer	 

public class WebInitializer implements WebApplicationInitializer{
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {        
        
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation("com.studyspring.basic.config");
        
        ServletRegistration.Dynamic dispatcher = servletContext.addServlet("DispatcherServlet", new DispatcherServlet(context));
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
        
        
        // 인코딩 필터 적용
        FilterRegistration.Dynamic charaterEncodingFilter = servletContext.addFilter("charaterEncodingFilter", new CharacterEncodingFilter());
        charaterEncodingFilter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        charaterEncodingFilter.setInitParameter("encoding", "UTF-8");
        charaterEncodingFilter.setInitParameter("forceEncoding", "true");
    }    
}