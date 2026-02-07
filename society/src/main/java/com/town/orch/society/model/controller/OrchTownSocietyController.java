package com.town.orch.society.model.controller;

import com.core.township.common.model.response.CoreResponse;
import com.town.orch.society.constant.TownApiEndpoints;
import com.town.orch.society.constant.TownSwaggerConstants;
import com.town.orch.society.dto.TownSocietyRequest;
import com.town.orch.society.dto.TownSocietyResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * Controller interface for managing housing societies.
 *
 * <p>Provides APIs for registering new societies and retrieving existing ones.
 */
@Tag(
        name = "TownSocietyResponse Registration APIs",
        description =
                "Manages the lifecycle and operations of housing societies within the Township platform. "
                        + "This includes registration of new societies with comprehensive validation and retrieval of society details.")
@RequestMapping(value = TownApiEndpoints.BASE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public interface OrchTownSocietyController {

    /**
     * Registers a new housing society.
     *
     * @param request the {@link TownSocietyRequest} containing society details
     * @return a {@link Mono} containing the {@link CoreResponse} with the created {@link TownSocietyResponse}
     */
    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Register a new society",
            description =
                    "Registers a new housing society. This operation requires a fully populated request body containing the society's name, "
                            + "address details (including city, state, country, pincode), and the creator's user ID.\n\n"
                            + "**Validation Rules:**\n"
                            + "<ul>\n"
                            + "  <li><b>Name:</b> Required, max 255 characters.</li>\n"
                            + "  <li><b>Address:</b> Required, max 500 characters.</li>\n"
                            + "  <li><b>Pincode:</b> Required, numeric format (4-10 digits).</li>\n"
                            + "  <li><b>CreatedByUserId:</b> Required UUID.</li>\n"
                            + "</ul>\n\n"
                            + "Returns the created society object with a generated UUID and status set to ACTIVE.",
            requestBody =
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Payload to register a new society",
                    required = true,
                    content =
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TownSocietyRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_ORG,
                                            ref = TownSwaggerConstants.REF_REQ_FULL_VALID),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.MINIMAL_VALID_REQUEST,
                                            ref = TownSwaggerConstants.REF_REQ_MINIMAL_VALID),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_PINCODE_FORMAT,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_PINCODE_FORMAT),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_PINCODE_LENGTH,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_PINCODE_LENGTH),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_MISSING_REQUIRED,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_MISSING_REQUIRED),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_NAME_TOO_LONG,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_NAME_TOO_LONG),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_EMPTY_STRINGS,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_EMPTY_STRINGS)
                            })),
            responses = {
                    @ApiResponse(
                            description = "Successful Response",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CoreResponse.class),
                                            examples = {
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_REGISTRATION_SUCCESS,
                                                            ref = TownSwaggerConstants.REF_RESP_SUCCESS),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_COUNTRY_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_COUNTRY_REQUIRED),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_STATE_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_STATE_REQUIRED),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_CITY_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_CITY_REQUIRED),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_ADDRESS_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_ADDRESS_REQUIRED),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_ADDRESS_MAX_LENGTH,
                                                            ref = TownSwaggerConstants.REF_RESP_ADDRESS_MAX_LENGTH),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_PINCODE_MISSING,
                                                            ref = TownSwaggerConstants.REF_RESP_PINCODE_MISSING),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_PINCODE_INVALID_FORMAT,
                                                            ref = TownSwaggerConstants.REF_RESP_PINCODE_INVALID_FORMAT),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_CREATED_BY_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_CREATED_BY_REQUIRED)
                                            })
                            })
            })
    Mono<CoreResponse<TownSocietyResponse>> registerSociety(
            @Valid @RequestBody(required = true) TownSocietyRequest request);

    /**
     * Registers a new housing society.
     *
     * @param request the {@link TownSocietyRequest} containing society details
     * @return a {@link Mono} containing the {@link CoreResponse} with the created {@link TownSocietyResponse}
     */
    @PutMapping(
            value = "/{societyId}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            summary = "Update an existing society",
            description =
                    "Updates an existing housing society. This operation requires a fully populated request body containing the society's name, "
                            + "address details (including city, state, country, pincode), and the creator's user ID.\n\n"
                            + "**Validation Rules:**\n"
                            + "<ul>\n"
                            + "  <li><b>Name:</b> Required, max 255 characters.</li>\n"
                            + "  <li><b>Address:</b> Required, max 500 characters.</li>\n"
                            + "  <li><b>Pincode:</b> Required, numeric format (4-10 digits).</li>\n"
                            + "  <li><b>CreatedByUserId:</b> Required UUID.</li>\n"
                            + "</ul>\n\n"
                            + "Returns the updated society object.",
            requestBody =
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Payload to update a society",
                    required = true,
                    content =
                    @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = TownSocietyRequest.class),
                            examples = {
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_ORG,
                                            ref = TownSwaggerConstants.REF_REQ_FULL_VALID),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.MINIMAL_VALID_REQUEST,
                                            ref = TownSwaggerConstants.REF_REQ_MINIMAL_VALID),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_PINCODE_FORMAT,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_PINCODE_FORMAT),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_PINCODE_LENGTH,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_PINCODE_LENGTH),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_MISSING_REQUIRED,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_MISSING_REQUIRED),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_NAME_TOO_LONG,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_NAME_TOO_LONG),
                                    @ExampleObject(
                                            name = TownSwaggerConstants.CREATE_INVALID_EMPTY_STRINGS,
                                            ref = TownSwaggerConstants.REF_REQ_INVALID_EMPTY_STRINGS)
                            })),
            responses = {
                    @ApiResponse(
                            description = "Successful Response",
                            responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            schema = @Schema(implementation = CoreResponse.class),
                                            examples = {
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_REGISTRATION_SUCCESS,
                                                            ref = TownSwaggerConstants.REF_RESP_SUCCESS),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_COUNTRY_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_COUNTRY_REQUIRED),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_STATE_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_STATE_REQUIRED),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_CITY_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_CITY_REQUIRED),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_ADDRESS_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_ADDRESS_REQUIRED),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_ADDRESS_MAX_LENGTH,
                                                            ref = TownSwaggerConstants.REF_RESP_ADDRESS_MAX_LENGTH),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_PINCODE_MISSING,
                                                            ref = TownSwaggerConstants.REF_RESP_PINCODE_MISSING),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_PINCODE_INVALID_FORMAT,
                                                            ref = TownSwaggerConstants.REF_RESP_PINCODE_INVALID_FORMAT),
                                                    @ExampleObject(
                                                            name = TownSwaggerConstants.SOCIETY_CREATED_BY_REQUIRED,
                                                            ref = TownSwaggerConstants.REF_RESP_CREATED_BY_REQUIRED)
                                            })
                            })
            })
    Mono<CoreResponse<TownSocietyResponse>> updateSociety(
            @Valid @RequestBody(required = true) TownSocietyRequest request,
            @Parameter(
                    description = "Unique ID of the organization to be updated",
                    schema = @Schema(type = "string", format = "uuid"),
                    example = "5d124154-24d0-4783-adda-aa0c0362a45b",
                    required = true)
            @PathVariable("societyId")
            String societyId);

    /**
     * Fetches the details of a society by its ID.
     *
     * @param societyId the UUID of the society to retrieve
     * @return a {@link Mono} containing the {@link CoreResponse} with the {@link TownSocietyResponse} details
     */
    @Operation(
            summary = "Fetch society by ID",
            description =
                    "Retrieves the details of a specific society based on the provided TownSocietyResponse ID (UUID). "
                            + "Returns the full society profile including address, timestamps, and status.")
    @GetMapping("/{societyId}")
    Mono<CoreResponse<TownSocietyResponse>> getSocietyById(
            @Parameter(
                    description = "Unique ID of the organization to be find",
                    schema = @Schema(type = "string", format = "uuid"),
                    example = "5d124154-24d0-4783-adda-aa0c0362a45b",
                    required = true)
            @PathVariable("societyId")
            String societyId);

    /**
     * Retrieves all registered housing societies.
     *
     * @return a {@link Flux} emitting all {@link TownSocietyResponse} entities
     */
    @Operation(
            summary = "Fetch all societies",
            description = "Retrieves a list of all registered housing societies.")
    @GetMapping
    Mono<CoreResponse<List<TownSocietyResponse>>> getAllSocieties();
}

