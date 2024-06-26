package org.springframework.ai.openai.samples.helloworld.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.ResourceHandlerRegistry;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * @author: movie
 * @date: 2024/6/17 17:06
 */
@Configuration
public class StaticResourceConfig implements WebFluxConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/sse/**")
                .addResourceLocations("classpath:/sse/") ;
    }
}
