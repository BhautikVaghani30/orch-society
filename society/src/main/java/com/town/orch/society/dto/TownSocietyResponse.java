package com.town.orch.society.dto;

import com.core.township.common.enums.TownSocietyStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Society information. Used for API requests and responses to decouple the
 * API layer from the persistence layer.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TownSocietyResponse {
  private String societyId;
  private String name;
  private String country;
  private String state;
  private String city;
  private String address;
  private String pincode;
  private String landmark;
  private String createdByUserId;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private TownSocietyStatus status;
}
