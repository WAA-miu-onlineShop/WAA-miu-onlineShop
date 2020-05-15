package com.miu.waa.groupbravo.onlineshop.config;

import com.miu.waa.groupbravo.onlineshop.formatter.RoleFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer
//                .defaultContentType(MediaType.TEXT_HTML)
//                .parameterName("type")
//                .favorParameter(true)
//                .ignoreUnknownPathExtensions(false)
//                .ignoreAcceptHeader(false)
//                .useJaf(true);
//    }
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.enableContentNegotiation(
//                // Use either ItextPdfView or LowagiePdfView
//                new LowagiePdfView()
//        );
//    }

//    @Autowired
//    RoleFormatter roleFormatter;
//
//    @Override
//    public void addFormatters(FormatterRegistry registry) {
//        registry.addFormatter(roleFormatter);
//    }
}

