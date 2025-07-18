package com.rewe.pharmacy.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(RecipeHandler(), "/ws")
                .setAllowedOrigins("*");
    }
    @Bean
    public WebSocketHandler RecipeHandler() {
        return new RecipeHandler();
    }
}
