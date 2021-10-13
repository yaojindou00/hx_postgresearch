package com.geotools.gistools;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.solr.SolrAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@MapperScan("com.geotools.gistools.dao")
@SpringBootApplication(exclude = SolrAutoConfiguration.class)
public class GistoolsApplication extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(GistoolsApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(GistoolsApplication.class, args);
        System.setProperty("tomcat.util.http.parser.HttpParser.requestTargetAllow", "|{}");

    }

}
