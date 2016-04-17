package com.emc.awg;

import com.shedhack.exception.controller.spring.config.EnableExceptionController;
import com.shedhack.thread.context.config.EnableThreadContextAspect;
import com.shedhack.trace.request.api.service.TraceRequestService;
import com.shedhack.trace.request.filter.DefaultLoggingHandler;
import com.shedhack.trace.request.filter.RequestTraceFilter;
import com.shedhack.trace.request.jpa.config.EnableTraceRequestJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.client.RestTemplate;

import javax.sql.DataSource;

@SpringBootApplication
@EnableTraceRequestJpa
@EnableExceptionController
@EnableThreadContextAspect
@PropertySources(value = {
        @PropertySource(value = "classpath:/git.properties"),
})
public class Application {
    
    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Value("${spring.application.name}")
    private String appName;

    @Autowired
    private TraceRequestService jpaTraceRequestService;


    @Bean
    public FilterRegistrationBean requestIdFilterRegistrationBean() {
        FilterRegistrationBean filter = new FilterRegistrationBean();
        filter.setFilter(new RequestTraceFilter(appName, jpaTraceRequestService, new DefaultLoggingHandler()));
        return filter;
    }


    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

    /**
     * This DS isn't used but has been added to show that the DS required by
     * Trace-Request isn't causing any conflicts.
     */
    @Primary
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/trace_request");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres@123");
        return dataSource;
    }




}
