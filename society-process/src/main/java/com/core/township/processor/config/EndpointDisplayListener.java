package com.core.township.processor.config;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.support.RouterFunctionMapping;
import org.springframework.web.reactive.result.method.annotation.RequestMappingHandlerMapping;

@Component
public class EndpointDisplayListener {

    @EventListener
    public void handleContextRefresh(ContextRefreshedEvent event) {
        ApplicationContext context = event.getApplicationContext();
        
        // 1. Find Annotated Controllers (@RestController)
        context.getBeansOfType(RequestMappingHandlerMapping.class).forEach((name, mapping) -> {
            mapping.getHandlerMethods().forEach((key, value) -> 
                System.out.println("Mapped Controller: " + key));
        });

        // 2. Find Functional Routes (RouterFunction)
        context.getBeansOfType(RouterFunctionMapping.class).forEach((name, mapping) -> {
            // Note: Functional routes are harder to 'pretty print' directly 
            // but the mapping object will log them if debug is enabled.
            System.out.println("Router Function Mapping found: " + name);
        });
    }
}