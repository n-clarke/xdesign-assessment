package com.xdesign.service.api.test.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.xdesign.service.api.constants.Constants;
import com.xdesign.service.api.model.HillData;
import org.slf4j.MDC;

import java.util.List;

public class DataFactory {

    /**
     * Sets MDC Value for Filter Hill Category
     *
     * @param hillCategory for a given category (value="MUN" or "TOP" or "EITHER")
     */
    public static void setMDCValueFilterHillCategory(String hillCategory) {
        MDC.put(Constants.PARAMETER_FILTER_HILL_CATEGORY, String.valueOf(hillCategory));
    }

    /**
     * Sets MDC Value for Sort By Height
     *
     * @param sortByHeight for a order (value="ASC" or "DESC")
     */
    public static void setMDCValueSortByHeight(String sortByHeight) {
        MDC.put(Constants.PARAMETER_SORT_BY_HEIGHT, String.valueOf(sortByHeight));
    }

    /**
     * Sets MDC Value for Sort By Name
     *
     * @param sortByName for a order (value="ASC" or "DESC")
     */
    public static void setMDCValueSortByName(String sortByName) {
        MDC.put(Constants.PARAMETER_SORT_BY_NAME, String.valueOf(sortByName));
    }

    /**
     * Sets MDC Value for Limit Results
     *
     * @param limit for a given limit of shown results
     */
    public static void setMDCValueLimit(int limit) {
        MDC.put(Constants.PARAMETER_LIMIT_RESULTS, String.valueOf(limit));
    }

    /**
     * Sets MDC Value for Filter Min Height
     *
     * @param minHeight value for the min height
     */
    public static void setMDCValueFilterMinHeight(double minHeight) {
        MDC.put(Constants.PARAMETER_FILTER_MIN_HEIGHT, String.valueOf(minHeight));
    }

    /**
     * Sets MDC Value for Filter Max Height
     *
     * @param maxHeight value for the max height
     */
    public static void setMDCValueFilterMaxHeight(double maxHeight) {
        MDC.put(Constants.PARAMETER_FILTER_MAX_HEIGHT, String.valueOf(maxHeight));
    }

    /**
     * Sets MDC Value for Filter Height
     *
     * @param minHeight value for the min height
     * @param maxHeight value for the max height
     */
    public static void setMDCValueFilterHeight(double minHeight, double maxHeight) {
        setMDCValueFilterMinHeight(minHeight);
        setMDCValueFilterMaxHeight(maxHeight);
    }

    /**
     * Sets MDC Value for All Query Parameters
     *
     * @param hillCategory for a given category (value="MUN" or "TOP" or "EITHER")
     * @param sortByHeight for a order (value="ASC" or "DESC")
     * @param sortByName   for a order (value="ASC" or "DESC")
     * @param limit        for a given limit of shown results
     * @param minHeight    value for the min height
     * @param maxHeight    value for the max height
     */
    public static void setAllMDCValues(String hillCategory, String sortByHeight, String sortByName,
                                       int limit, double minHeight, double maxHeight) {
        MDC.put(Constants.PARAMETER_FILTER_HILL_CATEGORY, String.valueOf(hillCategory));
        MDC.put(Constants.PARAMETER_SORT_BY_HEIGHT, String.valueOf(sortByHeight));
        MDC.put(Constants.PARAMETER_SORT_BY_NAME, String.valueOf(sortByName));
        MDC.put(Constants.PARAMETER_LIMIT_RESULTS, String.valueOf(limit));
        MDC.put(Constants.PARAMETER_FILTER_MIN_HEIGHT, String.valueOf(minHeight));
        MDC.put(Constants.PARAMETER_FILTER_MAX_HEIGHT, String.valueOf(maxHeight));
    }

    /**
     * Creates a Hill Data Object from JSON File
     *
     * @param jsonFile for a given json file
     * @return {@linkplain List<HillData>}
     */
    public static List<HillData> createHillDataFromJSON(String jsonFile) {
        String readJSONResponseFile = FileReaderUtil.readJSONResponseFromFile(jsonFile);
        return JsonParserUtils.fromJson(readJSONResponseFile, new TypeReference<List<HillData>>() {
        });
    }

    /**
     * Creates a Hill Data Object from a given String
     *
     * @param response for a given response
     * @return {@linkplain List<HillData>}
     */
    public static List<HillData> createHillDataFromString(String response) {
        return JsonParserUtils.fromJson(response, new TypeReference<List<HillData>>() {
        });
    }


}
