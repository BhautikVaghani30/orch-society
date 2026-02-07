package com.town.orch.society.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Constants for Society API endpoints.
 * Use a private constructor to prevent instantiation of utility/constant classes.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class TownApiEndpoints {

    /** Base path for all society related operations. */
    public static final String BASE_PATH = "/api/v1/societies";

    /** Example for sub-resources */
    public static final String REGISTER = "/register";
    public static final String GET_BY_ID = "/{societyId}";
}