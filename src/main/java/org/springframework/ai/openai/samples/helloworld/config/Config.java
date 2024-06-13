package org.springframework.ai.openai.samples.helloworld.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestClient;

/**
 * @author: movie
 * @date: 2024/6/13 14:11
 */
@Configuration
public class Config {
    @Bean
    ChatClient chatClient(ChatClient.Builder builder) {
        return builder.build();
    }

    @Bean
    public RestClient mqttApiRestClient(ObjectMapper objectMapper) {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        return RestClient.builder()
                .requestFactory(factory)
                .messageConverters(httpMessageConverters -> httpMessageConverters.add(4,
                        new MappingJackson2HttpMessageConverter(objectMapper)))
                .build();
    }


}
