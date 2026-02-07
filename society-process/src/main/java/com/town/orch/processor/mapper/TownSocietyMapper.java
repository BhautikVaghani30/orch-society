/*
 * Copyright 2025 com.core
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 */

package com.town.orch.processor.mapper;

import com.town.orch.processor.dto.TownSocietyRequest;
import com.town.orch.processor.entities.Society;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(
    componentModel = "spring",
    builder = @Builder(disableBuilder = true),
    unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TownSocietyMapper {

  /**
   * Converts a DTO into Society entity. Performs trimming, default status assignment, UUID
   * conversion.
   */
  @Mapping(target = "societyId", expression = "java(java.util.UUID.randomUUID().toString())")
  @Mapping(target = "name", expression = "java(trim(request.getName()))")
  @Mapping(target = "country", expression = "java(trim(request.getCountry()))")
  @Mapping(target = "state", expression = "java(trim(request.getState()))")
  @Mapping(target = "city", expression = "java(trim(request.getCity()))")
  @Mapping(target = "address", expression = "java(trim(request.getAddress()))")
  @Mapping(target = "pincode", expression = "java(trim(request.getPincode()))")
  @Mapping(target = "landmark", expression = "java(trim(request.getLandmark()))")
  Society toEntity(TownSocietyRequest request);

  /**
   * Updates an existing Society entity with data from the request. Ignores immutable fields (ID,
   * Audit) and system fields (Status).
   */
  @Mapping(target = "societyId", ignore = true)
  @Mapping(target = "createdAt", ignore = true)
  @Mapping(target = "updatedAt", ignore = true)
  @Mapping(target = "status", ignore = true)
  @Mapping(target = "createdByUserId", ignore = true)
  @Mapping(target = "name", expression = "java(trim(request.getName()))")
  @Mapping(target = "country", expression = "java(trim(request.getCountry()))")
  @Mapping(target = "state", expression = "java(trim(request.getState()))")
  @Mapping(target = "city", expression = "java(trim(request.getCity()))")
  @Mapping(target = "address", expression = "java(trim(request.getAddress()))")
  @Mapping(target = "pincode", expression = "java(trim(request.getPincode()))")
  @Mapping(target = "landmark", expression = "java(trim(request.getLandmark()))")
  void updateEntity(TownSocietyRequest request, @MappingTarget Society entity);

  // --- Helper Methods ---

  default String trim(String value) {
    return value == null ? null : value.trim();
  }
}
