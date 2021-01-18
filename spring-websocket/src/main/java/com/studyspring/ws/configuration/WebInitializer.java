package com.studyspring.ws.configuration;

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
// spring은 디스패처 설정파일을 실행하기 위해 WebApplicationInitializer 구현체를 찾아 자동으로 실행한다
public class WebInitializer implements WebApplicationInitializer{
  @Override
  public void onStartup(ServletContext servletContext) throws ServletException {        
      
      AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
      context.setConfigLocation("com.studyspring.ws.configuration");
      
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