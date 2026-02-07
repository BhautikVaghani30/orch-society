package com.town.orch.processor.service;

import com.core.township.common.enums.TownSocietyStatus;
import com.core.township.common.utils.CoreJsonParserUtils;
import com.town.orch.processor.constant.TownApiResponseCode;
import com.town.orch.processor.dto.TownSocietyRequest;
import com.town.orch.processor.entities.Society;
import com.town.orch.processor.exception.CoreServiceException;
import com.town.orch.processor.mapper.TownSocietyMapper;
import com.town.orch.processor.model.service.TownSocietyService;
import com.town.orch.processor.repository.TownSocietyRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.mapstruct.factory.Mappers;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Service implementation for society-related business logic. Handles data processing and
 * interaction with the repository layer.
 * <a href="https://okxxx.it.com/v-41123">...</a>
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class TownSocietyServiceImpl implements TownSocietyService {

  private final TownSocietyRepository repository;
  private static final TownSocietyMapper mapper = Mappers.getMapper(TownSocietyMapper.class);

  /**
   * Processes the society registration request by converting the DTO to an entity, setting default
   * values (UUID, Status), and persisting it to the database.
   *
   * <p>This method handles duplicate society name scenarios by catching {@link
   * DuplicateKeyException} and mapping it to a business exception.
   *
   * @param request The {@link TownSocietyRequest} containing the society details.
   * @return A {@link Mono} emitting the persisted {@link Society} entity.
   * @throws CoreServiceException if a society with the same name already exists.
   */
  @Override
  public Mono<Society> processSocietyData(TownSocietyRequest request) {
    return Mono.fromCallable(
            () ->
                Optional.ofNullable(mapper.toEntity(request))
                    .map(
                        entity -> {
                          entity.setStatus(TownSocietyStatus.ACTIVE);
                          return entity;
                        })
                    .orElse(null))
        .doOnNext(
            entity -> {
              if (log.isDebugEnabled()) {
                log.debug(
                    "TownSocietyServiceImpl : processSocietyData :: Preparing to save society: {}",
                    CoreJsonParserUtils.getJsonFromObject(entity));
              }
            })
        .flatMap(repository::save)
        .onErrorMap(
            DuplicateKeyException.class,
            ex -> {
              log.warn(
                  "TownSocietyServiceImpl : processSocietyData :: Duplicate society name detected: {}",
                  ex.getMessage());
              return new CoreServiceException(TownApiResponseCode.SOCIETY_ALREADY_EXISTS);
            })
        .doOnSuccess(
            savedSociety ->
                log.debug(
                    "TownSocietyServiceImpl : processSocietyData :: Saved society successfully: {}",
                    savedSociety))
        .doOnError(
            error -> {
              if (!(error instanceof CoreServiceException)) {
                log.error(
                    "TownSocietyServiceImpl : processSocietyData :: Unexpected error saving society",
                    error);
              }
            });
  }

  @Override
  public Mono<Society> updateSocietyData(TownSocietyRequest request, String societyId) {
    if (log.isDebugEnabled()) {
      log.debug(
          "TownSocietyServiceImpl : updateSocietyData :: request : {} and societyId : {}",
          CoreJsonParserUtils.getJsonFromObject(request),
          societyId);
    }
    return repository
        .findById(societyId)
        .switchIfEmpty(Mono.error(new CoreServiceException(TownApiResponseCode.SOCIETY_NOT_FOUND)))
        .map(
            existingSociety -> {
              mapper.updateEntity(request, existingSociety);
              return existingSociety;
            })
        .doOnNext(
            entity -> {
              if (log.isDebugEnabled()) {
                log.debug(
                    "TownSocietyServiceImpl : updateSocietyData :: Preparing to update society: {}",
                    CoreJsonParserUtils.getJsonFromObject(entity));
              }
            })
        .flatMap(repository::save)
        .onErrorMap(
            DuplicateKeyException.class,
            ex -> {
              log.warn(
                  "TownSocietyServiceImpl : updateSocietyData :: Duplicate society name detected: {}",
                  ex.getMessage());
              return new CoreServiceException(TownApiResponseCode.SOCIETY_ALREADY_EXISTS);
            })
        .doOnSuccess(
            savedSociety ->
                log.debug(
                    "TownSocietyServiceImpl : updateSocietyData :: Updated society successfully: {}",
                    savedSociety))
        .doOnError(
            error -> {
              if (!(error instanceof CoreServiceException)) {
                log.error(
                    "TownSocietyServiceImpl : updateSocietyData :: Unexpected error updating society",
                    error);
              }
            });
  }

  /**
   * Retrieves the detailed profile of a society based on its unique identifier (UUID).
   *
   * @param societyId The unique UUID string of the society.
   * @return A {@link Mono} emitting the {@link Society} entity if found.
   * @throws CoreServiceException if no society is found with the provided ID.
   */
  @Override
  public Mono<Society> getSocietyById(String societyId) {
    return repository
        .findById(societyId)
        .switchIfEmpty(Mono.error(new CoreServiceException(TownApiResponseCode.SOCIETY_NOT_FOUND)))
        .doOnSuccess(
            society -> {
              if (log.isDebugEnabled()) {
                log.debug(
                    "TownSocietyServiceImpl : getSocietyById :: Society fetched successfully for ID: {} -> {}",
                    societyId,
                    CoreJsonParserUtils.getJsonFromObject(society));
              }
            })
        .doOnError(
            error -> {
              if (error instanceof CoreServiceException) {
                log.warn(
                    "TownSocietyServiceImpl : getSocietyById :: Society not found for ID: {}",
                    societyId);
              } else {
                log.error(
                    "TownSocietyServiceImpl : getSocietyById :: Error fetching society for ID: {}",
                    societyId,
                    error);
              }
            });
  }

  /**
   * Retrieves all registered societies from the database.
   *
   * @return A {@link Flux} that emits all found {@link Society} entities.
   */
  @Override
  public Flux<Society> getAllSocieties() {
    log.debug("TownSocietyServiceImpl :: getAllSocieties : Fetching all societies.");
    return repository.findAll()
        .doOnError(
            error ->
                log.error("TownSocietyServiceImpl :: getAllSocieties : Error fetching societies", error));
  }
}
