package com.mclowry.linguaserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class SpringWebMvcConfiguration implements WebMvcConfigurer {

    @Autowired
    private LinguaServerConfigurationProperties config;

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder();
        if (config.isPrettyPrint()) {
            builder = builder.indentOutput(true);
        }
        converters.add(new MappingJackson2HttpMessageConverter(builder.build()));
    }
    
}
