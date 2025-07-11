package com.example.chatgpt.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAiConfig {
    
    @Value("${spring.ai.openai.chat.api-key}")
    private String apiKey;

}