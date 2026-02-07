package com.town.orch.society.adapter;

import com.core.township.common.model.response.CoreResponse;
import com.town.orch.society.constant.TownApiResponseCode;
import com.town.orch.society.dto.TownSocietyRequest;
import com.town.orch.society.dto.TownSocietyResponse;
import com.town.orch.society.exception.CoreServiceException;
import com.town.orch.society.intorceptore.LoggingInterceptor;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.core.township.society.intorceptore.*;
import reactor.core.publisher.Mono;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrchTownSocietyProcessorAdapter {
  private static final ParameterizedTypeReference<CoreResponse<TownSocietyResponse>>
      getCoreResponseTypeReference =
          new ParameterizedTypeReference<CoreResponse<TownSocietyResponse>>() {};

  private WebClient orchSocietyWebClient;

  private final LoggingInterceptor loggingInterceptor;

  @PostConstruct
  public void init() {
    orchSocietyWebClient =
        loggingInterceptor.getOrchWebClient("http://localhost:8083", "orchsocietyservice");
  }

  /**
   * Calls the society-processor to register a new society.
   *
   * @return
   */
  public Mono<CoreResponse<TownSocietyResponse>> registerSociety(
      TownSocietyRequest townSocietyRequest) {

    return orchSocietyWebClient
        .method(HttpMethod.POST)
        .uri("/api/v1/societies")
        .body(Mono.just(townSocietyRequest), TownSocietyRequest.class)
        .exchangeToMono(result -> result.bodyToMono(getCoreResponseTypeReference))
        .doOnSuccess(
            result -> {
              log.debug("Successfully received response from society-processor for registration.");
              if (result == null) {
                throw new CoreServiceException(TownApiResponseCode.SOCIETY_REGISTRATION_FAIL);
              }
            })
        .doOnError(
            ex -> {
              if (ex instanceof CoreServiceException) {
                log.error(
                    "A known service exception occurred during society registration: {}",
                    ((CoreServiceException) ex).getError().getCode(),
                    ex);
                throw (CoreServiceException) ex;
              }

              // Log the full unexpected exception. This resolves the InvalidDefinitionException
              // by letting the logging framework handle the exception object correctly.
              log.error(
                  "An unexpected error occurred while calling the society-processor service.", ex);

              // Wrap the unexpected exception in a generic service exception for the client
              throw new CoreServiceException(TownApiResponseCode.SOCIETY_REGISTRATION_FAIL);
            });
  }
}
