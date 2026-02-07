package com.town.orch.society.controller;

import com.core.township.common.model.response.CoreResponse;
import com.core.township.common.utils.CoreJsonParserUtils;
import com.town.orch.society.dto.TownSocietyRequest;
import com.town.orch.society.dto.TownSocietyResponse;
import com.town.orch.society.model.controller.OrchTownSocietyController;
import com.town.orch.society.model.service.OrchTownSocietyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class TownSocietyControllerImpl implements OrchTownSocietyController {

  private final OrchTownSocietyService orchTownSocietyService;

  @Override
  public Mono<CoreResponse<TownSocietyResponse>> registerSociety(TownSocietyRequest request) {
    if (log.isDebugEnabled()) {
      log.debug(
          "TownSocietyControllerImpl : registerSociety :: request : {}",
          CoreJsonParserUtils.getJsonFromObject(request));
    }
    return orchTownSocietyService
        .registerSociety(request)
        .doOnSuccess(
            result -> {
              if (log.isDebugEnabled()) {
                log.debug(
                    "TownSocietyControllerImpl : registerSociety :: response : {}",
                    CoreJsonParserUtils.getJsonFromObject(result));
              }
            })
        .doOnError(
            error -> {
              log.error(
                  "TownSocietyControllerImpl : registerSociety :: error : {}",
                  error.getMessage(),
                  error);
            });
  }

  @Override
  public Mono<CoreResponse<TownSocietyResponse>> updateSociety(
      TownSocietyRequest request, String societyId) {
    return null;
  }

  @Override
  public Mono<CoreResponse<TownSocietyResponse>> getSocietyById(String societyId) {
    return null;
  }

  @Override
  public Mono<CoreResponse<List<TownSocietyResponse>>> getAllSocieties() {
    return null;
  }
}
