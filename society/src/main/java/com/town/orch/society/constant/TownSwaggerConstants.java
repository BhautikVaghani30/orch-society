package com.town.orch.society.constant;

public class TownSwaggerConstants {
  public static final String CREATE_ORG_REQUEST_FLOW_EXAMPLES =
      "component/examples/society_process_requests.yaml#/components/examples/";
  public static final String CREATE_ORG_RESPONSE_FLOW_EXAMPLES =
      "component/examples/society_process_response.yaml#/components/examples/";
  public static final String CREATE_ORG = "FullValidRequest";
  public static final String MINIMAL_VALID_REQUEST = "MinimalValidRequest";
  public static final String CREATE_INVALID_PINCODE_FORMAT = "InvalidPincodeFormat";
  public static final String CREATE_INVALID_PINCODE_LENGTH = "InvalidPincodeLength";
  public static final String CREATE_INVALID_MISSING_REQUIRED = "InvalidMissingRequired";
  public static final String CREATE_INVALID_NAME_TOO_LONG = "InvalidNameTooLong";
  public static final String CREATE_INVALID_EMPTY_STRINGS = "InvalidEmptyStrings";
  public static final String SOCIETY_REGISTRATION_SUCCESS = "SocietyRegistrationSuccess";
  public static final String SOCIETY_COUNTRY_REQUIRED = "SocietyCountryRequired";
  public static final String SOCIETY_STATE_REQUIRED = "SocietyStateRequired";
  public static final String SOCIETY_CITY_REQUIRED = "SocietyCityRequired";
  public static final String SOCIETY_ADDRESS_REQUIRED = "SocietyAddressRequired";
  public static final String SOCIETY_ADDRESS_MAX_LENGTH = "SocietyAddressMaxLength";
  public static final String SOCIETY_PINCODE_MISSING = "SocietyPincodeMissing";
  public static final String SOCIETY_PINCODE_INVALID_FORMAT = "SocietyPincodeInvalidFormat";
  public static final String SOCIETY_CREATED_BY_REQUIRED = "SocietyCreatedByRequired";

  // --- Pre-constructed References for Swagger Annotations ---

  // Request References
  public static final String REF_REQ_FULL_VALID = CREATE_ORG_REQUEST_FLOW_EXAMPLES + CREATE_ORG;
  public static final String REF_REQ_MINIMAL_VALID =
      CREATE_ORG_REQUEST_FLOW_EXAMPLES + MINIMAL_VALID_REQUEST;
  public static final String REF_REQ_INVALID_PINCODE_FORMAT =
      CREATE_ORG_REQUEST_FLOW_EXAMPLES + CREATE_INVALID_PINCODE_FORMAT;
  public static final String REF_REQ_INVALID_PINCODE_LENGTH =
      CREATE_ORG_REQUEST_FLOW_EXAMPLES + CREATE_INVALID_PINCODE_LENGTH;
  public static final String REF_REQ_INVALID_MISSING_REQUIRED =
      CREATE_ORG_REQUEST_FLOW_EXAMPLES + CREATE_INVALID_MISSING_REQUIRED;
  public static final String REF_REQ_INVALID_NAME_TOO_LONG =
      CREATE_ORG_REQUEST_FLOW_EXAMPLES + CREATE_INVALID_NAME_TOO_LONG;
  public static final String REF_REQ_INVALID_EMPTY_STRINGS =
      CREATE_ORG_REQUEST_FLOW_EXAMPLES + CREATE_INVALID_EMPTY_STRINGS;

  // Response References
  public static final String REF_RESP_SUCCESS =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_REGISTRATION_SUCCESS;
  public static final String REF_RESP_COUNTRY_REQUIRED =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_COUNTRY_REQUIRED;
  public static final String REF_RESP_STATE_REQUIRED =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_STATE_REQUIRED;
  public static final String REF_RESP_CITY_REQUIRED =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_CITY_REQUIRED;
  public static final String REF_RESP_ADDRESS_REQUIRED =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_ADDRESS_REQUIRED;
  public static final String REF_RESP_ADDRESS_MAX_LENGTH =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_ADDRESS_MAX_LENGTH;
  public static final String REF_RESP_PINCODE_MISSING =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_PINCODE_MISSING;
  public static final String REF_RESP_PINCODE_INVALID_FORMAT =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_PINCODE_INVALID_FORMAT;
  public static final String REF_RESP_CREATED_BY_REQUIRED =
      CREATE_ORG_RESPONSE_FLOW_EXAMPLES + SOCIETY_CREATED_BY_REQUIRED;
}
