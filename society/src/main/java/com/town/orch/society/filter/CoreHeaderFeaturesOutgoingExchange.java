package com.town.orch.society.filter;

import java.util.Map;
import java.util.Optional;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeFunction;
import reactor.core.publisher.Mono;

/**
* Reactive request filter to propagate HTTP headers from reactive context to the outgoing WebClient
* request.
*/
public class CoreHeaderFeaturesOutgoingExchange implements ExchangeFilterFunction {

 @Override
 public Mono<ClientResponse> filter(
     ClientRequest clientRequest, ExchangeFunction exchangeFunction) {
     return Mono.deferContextual(Mono::just)
       .flatMap(
           context -> {
             Optional<Map<String, String>> headerFeatureMap =
                 context.getOrEmpty("ulta-header-feature-context");

             ClientRequest newRequest =
                 headerFeatureMap
                     .map(
                         headers -> {
                           if (!headers.isEmpty()) {
                             return ClientRequest.from(clientRequest)
                                 .headers(
                                     httpHeaders ->
                                         headers.forEach(
                                             httpHeaders::add))
                                 .build();
                           }
                           return clientRequest;
                         })
                     .orElse(clientRequest);

             return exchangeFunction.exchange(newRequest);
           });
}
}