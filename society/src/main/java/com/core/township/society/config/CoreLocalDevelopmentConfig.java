package com.core.township.society.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Profile("local")
@ConfigurationProperties(prefix = "local")
@Component
@NoArgsConstructor
@Getter
@Setter
public class CoreLocalDevelopmentConfig implements WebFilter {

 private static final String COOKIE_ULTA_DSP_AT = "ULTA_DSP_AT";
 private static final String COOKIE_ULTA_DSP_RT = "ULTA_DSP_RT";
 private String apiKey;
 private String apiEnvironment;

 private Map<String, String> localCookies = new HashMap<>();

 public void applyDefaultHeaders(WebClient.Builder webClientBuilder) {
   if (apiKey != null && !apiKey.trim().isEmpty()) {
     webClientBuilder.defaultHeader("X-API-KEY", apiKey);
   }
   if (apiEnvironment != null && !apiEnvironment.trim().isEmpty()) {
     webClientBuilder.defaultHeader("X-ULTA-API-ENVIRONMENT", apiEnvironment);
   }
}

/**
  * For all incoming requests, locally cache the ULTA_DSP_AT and ULTA_DSP_RT cookies (if present)
  */
 @Override
 public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
   MultiValueMap<String, HttpCookie> cookies = exchange.getRequest().getCookies();
   if (cookies.containsKey(COOKIE_ULTA_DSP_AT) && cookies.containsKey(COOKIE_ULTA_DSP_RT)) {
     localCookies.put(COOKIE_ULTA_DSP_AT, cookies.get(COOKIE_ULTA_DSP_AT).get(0).getValue());
     localCookies.put(COOKIE_ULTA_DSP_RT, cookies.get(COOKIE_ULTA_DSP_RT).get(0).getValue());
   }
   return chain.filter(exchange);
}

/**
  * For outgoing requests, apply locally cached ULTA_DSP_AT and ULTA_DSP_RT cookies to the request (if present)
  */
 public void applyLocalCookies(WebClient.Builder webClientBuilder) {
   webClientBuilder.defaultRequest(request -> {
     if (localCookies.containsKey(COOKIE_ULTA_DSP_AT)) {
       request.cookie(COOKIE_ULTA_DSP_AT, localCookies.get(COOKIE_ULTA_DSP_AT));
     }
     if (localCookies.containsKey(COOKIE_ULTA_DSP_RT)) {
       request.cookie(COOKIE_ULTA_DSP_RT, localCookies.get(COOKIE_ULTA_DSP_RT));
     }
   });
}
}