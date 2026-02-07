package com.town.orch.society.service;

import com.core.township.common.model.response.CoreResponse;
import com.core.township.common.utils.CoreJsonParserUtils;
import com.town.orch.society.adapter.OrchTownSocietyProcessorAdapter;
import com.town.orch.society.dto.TownSocietyRequest;
import com.town.orch.society.dto.TownSocietyResponse;

import com.town.orch.society.model.service.OrchTownSocietyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrchTownSocietyServiceImpl implements OrchTownSocietyService {

  private final OrchTownSocietyProcessorAdapter townOrchSocietyProcessorAdapter;

  @Override
  public Mono<CoreResponse<TownSocietyResponse>> registerSociety(TownSocietyRequest request) {
    if (log.isDebugEnabled()) {
      log.debug(
          "OrchTownSocietyServiceImpl : processTownSocietyResponseData :: TownSocietyRequest - {}",
          CoreJsonParserUtils.getJsonFromObject(request));
    }
    return townOrchSocietyProcessorAdapter
        .registerSociety(request)
        .flatMap(
            result -> {
              if (log.isDebugEnabled()) {
                log.debug(
                    "OrchTownSocietyServiceImpl : processTownSocietyResponseData :: registerSociety response - {}",
                    CoreJsonParserUtils.getJsonFromObject(result));
              }
              return Mono.just(result);
            });
  }

  @Override
  public Mono<TownSocietyResponse> updateTownSocietyResponseData(
      TownSocietyRequest request, String TownSocietyResponseId) {
    return null;
  }

  @Override
  public Mono<TownSocietyResponse> getTownSocietyResponseById(String TownSocietyResponseId) {
    return null;
  }

  @Override
  public Flux<TownSocietyResponse> getAllSocieties() {
    return null;
  }
}
