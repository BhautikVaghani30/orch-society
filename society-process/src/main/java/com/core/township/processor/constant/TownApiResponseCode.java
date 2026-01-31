package com.core.township.processor.constant;

public class TownApiResponseCode {

  /* Request DTO validation error codes */
  public static final String NAME_REQUIRED = "E_SCTY_P_101_NAME_REQUIRED";
  public static final String NAME_MAX_LENGTH = "E_SCTY_P_102_NAME_MAX_LENGTH";
  public static final String ADDRESS_COUNTRY_REQUIRED = "E_SCTY_P_103_ADDRESS_COUNTRY_REQUIRED";
  public static final String ADDRESS_STATE_REQUIRED = "E_SCTY_P_104_ADDRESS_STATE_REQUIRED";
  public static final String ADDRESS_CITY_REQUIRED = "E_SCTY_P_105_ADDRESS_CITY_REQUIRED";
  public static final String ADDRESS_REQUIRED = "E_SCTY_P_106_ADDRESS_REQUIRED";
  public static final String ADDRESS_MAX_LENGTH = "E_SCTY_P_107_ADDRESS_MAX_LENGTH";
  public static final String PINCODE_REQUIRED = "E_SCTY_P_108_PINCODE_REQUIRED";
  public static final String PINCODE_INVALID_FORMAT = "E_SCTY_P_109_PINCODE_INVALID_FORMAT";
  public static final String CREATED_BY_ID_REQUIRED = "E_SCTY_P_110_CREATED_BY_ID_REQUIRED";

  public static final String SOCIETY_ALREADY_EXISTS = "E_SCTY_P_111_SOCIETY_ALREADY_EXISTS";
  public static final String SOCIETY_REGISTRATION_SUCCESSFULLY =
      "S_SCTY_P_112_SOCIETY_REGISTRATION_SUCCESSFULLY";
  public static final String SOCIETY_NOT_FOUND = "S_SCTY_P_113_SOCIETY_NOT_FOUND";

  public static final String SOCIETY_FOUND = "S_SCTY_P_114_SOCIETY_FOUND";
  public static final String SOCIETY_REGISTRATION_UPDATED =
      "S_SCTY_P_115_SOCIETY_REGISTRATION_UPDATED";
}
