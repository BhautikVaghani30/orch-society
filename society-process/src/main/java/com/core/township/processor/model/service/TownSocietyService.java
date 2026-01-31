package com.core.township.processor.model.service;

import com.core.township.processor.dto.TownSocietyRequest;
import com.core.township.processor.entities.Society;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TownSocietyService {
    Mono<Society> processSocietyData(TownSocietyRequest request);
    Mono<Society> updateSocietyData(TownSocietyRequest request, String societyId);
    Mono<Society> getSocietyById(String societyId);
    Flux<Society> getAllSocieties();
}
