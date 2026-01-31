package com.core.township.processor.dto;

import com.core.township.processor.constant.TownApiResponseCode;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serial;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Request payload for registering a new society.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Schema(description = "Request payload for registering a new society.")
public class TownSocietyRequest implements Serializable {

  @Serial private static final long serialVersionUID = 1L;

  /** The name of the society. */
  @Schema(
      description = "Name of the society",
      example = "Greenwood Park Residences",
      maxLength = 255)
  @NotBlank(message = TownApiResponseCode.NAME_REQUIRED)
  @Size(max = 255, message = TownApiResponseCode.NAME_MAX_LENGTH)
  private String name;

  /** The country where the society is located. */
  @Schema(description = "Country where the society is located", example = "USA")
  @NotBlank(message = TownApiResponseCode.ADDRESS_COUNTRY_REQUIRED)
  private String country;

  /** The state where the society is located. */
  @Schema(description = "State where the society is located", example = "California")
  @NotBlank(message = TownApiResponseCode.ADDRESS_STATE_REQUIRED)
  private String state;

  /** The city where the society is located. */
  @Schema(description = "City where the society is located", example = "San Francisco")
  @NotBlank(message = TownApiResponseCode.ADDRESS_CITY_REQUIRED)
  private String city;

  /** The detailed address of the society. */
  @Schema(
      description = "Detailed address line",
      example = "123 Innovation Drive, Suite 100",
      maxLength = 500)
  @NotBlank(message = TownApiResponseCode.ADDRESS_REQUIRED)
  @Size(max = 500, message = TownApiResponseCode.ADDRESS_MAX_LENGTH)
  private String address;

  /** The postal code of the area. */
  @Schema(description = "Postal code", example = "94105", pattern = "^[0-9]{4,10}$")
  @NotBlank(message = TownApiResponseCode.PINCODE_REQUIRED)
  @Pattern(regexp = "^[0-9]{4,10}$", message = TownApiResponseCode.PINCODE_INVALID_FORMAT)
  private String pincode;

  /** A nearby landmark for easier location. */
  @Schema(description = "Nearby landmark", example = "Near the Bay Bridge")
  private String landmark;

  /** The UUID of the user initiating the registration. */
  @Schema(
      description = "UUID of the user creating the society",
      example = "a9b8c7d6-e5f4-g3h2-i1j0-k9l8m7n6o5p4")
  @NotBlank(message = TownApiResponseCode.CREATED_BY_ID_REQUIRED)
  private String createdByUserId;
}
