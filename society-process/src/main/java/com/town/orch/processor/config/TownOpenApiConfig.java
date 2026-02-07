package com.town.orch.processor.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class TownOpenApiConfig {

  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI().components(new Components());
  }
}