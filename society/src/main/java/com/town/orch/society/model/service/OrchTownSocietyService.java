package com.town.orch.society.model.service;

import com.core.township.common.model.response.CoreResponse;
import com.town.orch.society.dto.TownSocietyRequest;
import com.town.orch.society.dto.TownSocietyResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface OrchTownSocietyService {
  Mono<CoreResponse<TownSocietyResponse>> registerSociety(TownSocietyRequest request);

  Mono<TownSocietyResponse> updateTownSocietyResponseData(
      TownSocietyRequest request, String TownSocietyResponseId);

  Mono<TownSocietyResponse> getTownSocietyResponseById(String TownSocietyResponseId);

  Flux<TownSocietyResponse> getAllSocieties();
}
