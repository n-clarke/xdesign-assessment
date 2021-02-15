package com.xdesign.service.api.test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xdesign.service.api.constants.Constants;
import com.xdesign.service.api.model.HillData;
import org.slf4j.MDC;

import java.util.List;

public class DataFactory {

    private static final String FILE_SRC_EXPECTED_RESPONSE_NO_PARAMETERS = "expected_api_response_no_query_parameters.json";
    /**
     * todo : description
     *
     * @param hillCategory
     */
    public static void setMDCValueFilterHillCategory(String hillCategory) {
        MDC.put(Constants.PARAMETER_FILTER_HILL_CATEGORY, String.valueOf(hillCategory));
    }

    /**
     * todo : description
     *
     * @param sortByHeight
     */
    public static void setMDCValueSortByHeight(String sortByHeight) {
        MDC.put(Constants.PARAMETER_SORT_BY_HEIGHT, String.valueOf(sortByHeight));
    }

    /**
     * todo : description
     *
     * @param sortByName
     */
    public static void setMDCValueSortByName(String sortByName) {
        MDC.put(Constants.PARAMETER_SORT_BY_NAME, String.valueOf(sortByName));
    }

    /**
     * todo : description
     *
     * @param limit
     */
    public static void setMDCValueLimit(int limit) {
        MDC.put(Constants.PARAMETER_LIMIT_RESULTS, String.valueOf(limit));
    }

    /**
     * todo : description
     *
     * @param minHeight
     */
    public static void setMDCValueFilterMinHeight(double minHeight) {
        MDC.put(Constants.PARAMETER_FILTER_MIN_HEIGHT, String.valueOf(minHeight));
    }

    /**
     * todo : description
     *
     * @param maxHeight
     */
    public static void setMDCValueFilterMaxHeight(double maxHeight) {
        MDC.put(Constants.PARAMETER_FILTER_MAX_HEIGHT, String.valueOf(maxHeight));
    }

    /**
     * todo : description
     *
     * @param maxHeight
     */
    public static void setMDCValueFilterHeight(double minHeight, double maxHeight) {
        setMDCValueFilterMinHeight(minHeight);
        setMDCValueFilterMaxHeight(maxHeight);
    }

    /**
     * todo : description
     *
     * @param filterHillCategory
     * @param sortByHeight
     * @param sortByName
     * @param limitResults
     * @param filterMinHeight
     * @param filterMaxHeight
     */
    public static void setAllMDCValues(String filterHillCategory, String sortByHeight, String sortByName,
                                    int limitResults, double filterMinHeight, double filterMaxHeight) {
        MDC.put(Constants.PARAMETER_FILTER_HILL_CATEGORY, String.valueOf(filterHillCategory));
        MDC.put(Constants.PARAMETER_SORT_BY_HEIGHT, String.valueOf(sortByHeight));
        MDC.put(Constants.PARAMETER_SORT_BY_NAME, String.valueOf(sortByName));
        MDC.put(Constants.PARAMETER_LIMIT_RESULTS, String.valueOf(limitResults));
        MDC.put(Constants.PARAMETER_FILTER_MIN_HEIGHT, String.valueOf(filterMinHeight));
        MDC.put(Constants.PARAMETER_FILTER_MAX_HEIGHT, String.valueOf(filterMaxHeight));
    }

    /**
     * todo : description
     *
     * @param jsonFile
     * @return
     */
    public static List<HillData> createHillDataFromJSON(String jsonFile) {
        String readJSONResponseFile = FileReaderUtil.readJSONResponseFromFile(jsonFile);
        return JsonParserUtils.fromJson(readJSONResponseFile, new TypeReference<List<HillData>>() {
        });
    }

    /**
     * todo : description
     *
     * @param response
     * @return
     */
    public static List<HillData> createHillDataFromString(String response) {
        return JsonParserUtils.fromJson(response, new TypeReference<List<HillData>>() {
        });
    }



}
