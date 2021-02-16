package com.xdesign.service.api.constants;

public class ErrorConstants {

    public static final String INVALID_QUERY_PARAM = "Invalid Query Parameter";
    public static final String INVALID_QUERY_PARAM_MIN_LIMIT = INVALID_QUERY_PARAM + "value must be greater than 0 records.";
    public static final String INVALID_QUERY_PARAM_SORTING = INVALID_QUERY_PARAM + "Sort by value must either be 'asc' or 'desc' regardless of case sensitivity.";
    public static final String INVALID_QUERY_PARAM_HEIGHT_COMBINATION = INVALID_QUERY_PARAM + "'s max height is less than the minimum height";
}
