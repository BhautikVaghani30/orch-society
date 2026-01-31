package com.core.township.society.constant;

public class TownApiResponseCode {

  /* Request DTO validation error codes */
  public static final String NAME_REQUIRED = "O_S_E_101_NAME_REQUIRED";
  public static final String NAME_MAX_LENGTH = "O_S_E_102_NAME_MAX_LENGTH";
  public static final String ADDRESS_COUNTRY_REQUIRED = "O_S_E_103_ADDRESS_COUNTRY_REQUIRED";
  public static final String ADDRESS_STATE_REQUIRED = "O_S_E_104_ADDRESS_STATE_REQUIRED";
  public static final String ADDRESS_CITY_REQUIRED = "O_S_E_105_ADDRESS_CITY_REQUIRED";
  public static final String ADDRESS_REQUIRED = "O_S_E_106_ADDRESS_REQUIRED";
  public static final String ADDRESS_MAX_LENGTH = "O_S_E_107_ADDRESS_MAX_LENGTH";
  public static final String PINCODE_REQUIRED = "O_S_E_108_PINCODE_REQUIRED";
  public static final String PINCODE_INVALID_FORMAT = "O_S_E_109_PINCODE_INVALID_FORMAT";
  public static final String CREATED_BY_ID_REQUIRED = "O_S_E_110_CREATED_BY_ID_REQUIRED";

  public static final String SOCIETY_ALREADY_EXISTS = "O_S_E_111_SOCIETY_ALREADY_EXISTS";
  public static final String SOCIETY_REGISTRATION_SUCCESSFULLY =
      "O_S_S_112_SOCIETY_REGISTRATION_SUCCESSFULLY";
  public static final String SOCIETY_NOT_FOUND = "O_S_S_113_SOCIETY_NOT_FOUND";

  public static final String SOCIETY_FOUND = "O_S_S_114_SOCIETY_FOUND";
  public static final String SOCIETY_REGISTRATION_UPDATED =
      "O_S_S_115_SOCIETY_REGISTRATION_UPDATED";

  public static final String SOCIETY_REGISTRATION_FAIL =
      "O_S_E_116_SOCIETY_REGISTRATION_FAIL";

}
