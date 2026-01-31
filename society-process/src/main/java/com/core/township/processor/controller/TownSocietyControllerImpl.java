package com.core.township.processor.controller;

import com.core.township.common.enums.CoreApiStatus;
import com.core.township.common.model.response.CoreMessage;
import com.core.township.common.model.response.CoreMeta;
import com.core.township.common.model.response.CoreResponse;
import com.core.township.common.utils.CoreJsonParserUtils;
import com.core.township.processor.constant.TownApiResponseCode;
import com.core.township.processor.dto.TownSocietyRequest;
import com.core.township.processor.entities.Society;
import com.core.township.processor.model.controller.TownSocietyController;
import com.core.township.processor.model.service.TownSocietyService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

/**
 * Controller implementation for handling society-related HTTP requests in a non-blocking, reactive
 * manner. Implements the {@link TownSocietyController} interface.
 */
@RequiredArgsConstructor
@RestController
@Slf4j
public class TownSocietyControllerImpl implements TownSocietyController {

  private final TownSocietyService townSocietyService;

  /**
   * Handles the asynchronous registration of a new society.
   *
   * @param request The request body containing the society details.
   * @return A {@link Mono} emitting a {@link CoreResponse} with the created {@link Society} data.
   */
  @Override
  public Mono<CoreResponse<Society>> registerSociety(TownSocietyRequest request) {
    if (log.isDebugEnabled()) {
      log.debug(
          "TownSocietyControllerImpl : registerSociety :: townSocietyRequest : {}",
          CoreJsonParserUtils.getJsonFromObject(request));
    }

    return townSocietyService
        .processSocietyData(request)
        .map(
            society ->
                buildSuccessResponse(
                    society, TownApiResponseCode.SOCIETY_REGISTRATION_SUCCESSFULLY))
        .doOnSuccess(
            response -> {
              if (log.isDebugEnabled()) {
                log.debug(
                    "TownSocietyControllerImpl :: registerSociety : response: {}",
                    CoreJsonParserUtils.getJsonFromObject(response));
              }
            })
        .doOnError(
            error ->
                log.error(
                    "TownSocietyControllerImpl :: registerSociety : error: {}",
                    error.getMessage(),
                    error));
  }

  @Override
  public Mono<CoreResponse<Society>> updateSociety(TownSocietyRequest request, String societyId) {
    if (log.isDebugEnabled()) {
      log.debug(
          "TownSocietyControllerImpl : updateSociety :: townSocietyRequest : {} and societyId : {}",
          CoreJsonParserUtils.getJsonFromObject(request),
          societyId);
    }

    return townSocietyService
        .updateSocietyData(request, societyId)
        .map(
            society ->
                buildSuccessResponse(society, TownApiResponseCode.SOCIETY_REGISTRATION_UPDATED))
        .doOnSuccess(
            response -> {
              if (log.isDebugEnabled()) {
                log.debug(
                    "TownSocietyControllerImpl :: updateSociety : response: {}",
                    CoreJsonParserUtils.getJsonFromObject(response));
              }
            })
        .doOnError(
            error ->
                log.error(
                    "TownSocietyControllerImpl :: updateSociety : error: {}",
                    error.getMessage(),
                    error));
  }

  /**
   * Retrieves a society by its unique identifier.
   *
   * @param societyId The unique ID of the society to fetch.
   * @return A {@link Mono} emitting a {@link CoreResponse} containing the found {@link Society}.
   */
  @Override
  public Mono<CoreResponse<Society>> getSocietyById(String societyId) {
    log.debug("TownSocietyControllerImpl :: getSocietyById : societyId : {}", societyId);
    return townSocietyService
        .getSocietyById(societyId)
        .map(society -> buildSuccessResponse(society, TownApiResponseCode.SOCIETY_FOUND))
        .doOnSuccess(
            response -> {
              if (log.isDebugEnabled()) {
                log.debug(
                    "TownSocietyControllerImpl :: getSocietyById : response: {}",
                    CoreJsonParserUtils.getJsonFromObject(response));
              }
            })
        .doOnError(
            error ->
                log.error(
                    "TownSocietyControllerImpl :: getSocietyById : error: {}",
                    error.getMessage(),
                    error));
  }

  @Override
  public Mono<CoreResponse<List<Society>>> getAllSocieties() {
    log.debug("TownSocietyControllerImpl :: getAllSocieties : Fetching all societies.");
    return townSocietyService
        .getAllSocieties()
        .collectList()
        .map(societies -> buildSuccessResponse(societies, TownApiResponseCode.SOCIETY_FOUND))
        .doOnError(
            error ->
                log.error(
                    "TownSocietyControllerImpl :: getAllSocieties : Error fetching societies",
                    error));
  }

  /**
   * A generic helper method to build a standardized successful {@link CoreResponse}.
   *
   * @param data The payload to be included in the response.
   * @param messageCode A code representing the success message.
   * @return A configured {@link CoreResponse} instance.
   * @param <T> The type of the data payload.
   */
  private <T> CoreResponse<T> buildSuccessResponse(T data, String messageCode) {
    return CoreResponse.<T>create(
        builder ->
            builder
                .withMeta(CoreMeta.builder().status(CoreApiStatus.SUCCESS.getValue()).build())
                .withData(data)
                .withMessages(
                    Collections.singletonList(
                        CoreMessage.builder()
                            .code(messageCode)
                            .type(CoreMessage.CoreMessageType.INFO.toString())
                            .build())));
  }
}
