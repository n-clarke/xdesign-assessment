package com.xdesign.service.api.service;

import com.google.gson.Gson;
import com.xdesign.service.api.constants.Constants;
import com.xdesign.service.api.constants.HillCategory;
import com.xdesign.service.api.model.HillData;
import com.xdesign.service.api.model.MunroModel;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

import static com.xdesign.service.api.utils.CSVUtil.getMunroData;

@Service
public class MainService {

    @Autowired
    private static final Gson gson = new Gson();

    /**
     * A method to return a HashSet of Hill Category Enums
     *
     * @return {@linkplain HashSet<String>}
     */
    private static HashSet<String> getHillCategoryEnums() {
        HashSet<String> values = new HashSet<>();

        for (HillCategory category : HillCategory.values()) {
            values.add(category.name());
        }

        return values;
    }

    /**
     * A method to filter the munro data by hill category
     *
     * @param munroData for a given MunroModel
     */
    private static void filterByHillCategory(MunroModel munroData) {
        String filterHillCategory = MDC.get(Constants.PARAMETER_FILTER_HILL_CATEGORY);

        if (filterHillCategory != null) {
            HashSet<String> hillCategories = getHillCategoryEnums();
            // todo : review contains
            if (hillCategories.contains(filterHillCategory.toUpperCase())) {
                List<HillData> hillDataList = new ArrayList<>();
                for (HillData hillData : munroData.getItems()) {
                    if (!hillData.getHillCategory().equals(Constants.BLANK) && (hillData.getHillCategory().equals(filterHillCategory.toUpperCase()) || filterHillCategory.toUpperCase().equals(HillCategory.EITHER.toString()))) {
                        hillDataList.add(hillData);
                    }

                    munroData.setItems(hillDataList);
                }
            } else {
                // todo : log error
            }
        }
    }

    /**
     * A method to filter the munro data by a given min and max
     * height
     *
     * @param munroData for a given MunroModel
     */
    private static void filterByHeight(MunroModel munroData) {
        // Height
        double minHeight = 0;
        double maxHeight = munroData.getItems().size();
        boolean isHeightParamActive = false;

        String filterMinHeight = MDC.get(Constants.PARAMETER_FILTER_MIN_HEIGHT);
        if (filterMinHeight != null && !filterMinHeight.equals(Constants.NULL)) {
            minHeight = Double.parseDouble(filterMinHeight);
            isHeightParamActive = true;
        }

        String filterMaxHeight = MDC.get(Constants.PARAMETER_FILTER_MAX_HEIGHT);
        if (filterMaxHeight != null && !filterMaxHeight.equals(Constants.NULL)) {
            maxHeight = Double.parseDouble(filterMaxHeight);
            isHeightParamActive = true;
        }

        if (isHeightParamActive) {
            if (minHeight > maxHeight) {
                // todo : log error
                // max height is less than the minimum height
            }

            List<HillData> hillDataList = new ArrayList<>();

            // todo : Param : minHeight && maxHeight
            for (HillData hillData : munroData.getItems()) {
                boolean isSmallerEqualToMaxHeight = hillData.getHeight() <= maxHeight;
                boolean isGreaterEqualToMinHeight = hillData.getHeight() >= minHeight;

                if (isSmallerEqualToMaxHeight && isGreaterEqualToMinHeight) {
                    hillDataList.add(hillData);
                }
            }

            munroData.setItems(hillDataList);
        }
    }

    /**
     * A method to sort the Munro Data in either ascending or descending order
     * given a specified query param and ordering by comparator
     *
     * @param mdcValue    for a given mdc value set via the query params
     * @param munroData   for a given MunroModel
     * @param compareById for a given Comparator<HillData> object selection
     */
    private static void sortMunroData(String mdcValue, MunroModel munroData, Comparator<HillData> compareById) {
        if (!mdcValue.equals(Constants.NULL)) {
            if (mdcValue.toLowerCase().equals(Constants.ASC)) {
                // asc
                munroData.getItems().sort(compareById);
            } else if (mdcValue.toLowerCase().equals(Constants.DESC)) {
                // desc
                munroData.getItems().sort(compareById.reversed());
            } else {
                // todo : log error
            }
        }
    }

    /**
     * A method to sort the Munro Data in either ascending or descending order by name
     *
     * @param munroData for a given MunroModel
     */
    private static void sortByName(MunroModel munroData) {
        String sortByName = MDC.get(Constants.PARAMETER_SORT_BY_NAME);

        if (sortByName != null) {
            sortMunroData(sortByName, munroData, Comparator.comparing(HillData::getName));
        }
    }

    /**
     * A method to sort the Munro Data in either ascending or descending order by Height
     *
     * @param munroData for a given MunroModel
     */
    private static void sortByHeight(MunroModel munroData) {
        String sortByHeight = MDC.get(Constants.PARAMETER_SORT_BY_HEIGHT);

        if (sortByHeight != null) {
            sortMunroData(sortByHeight, munroData, Comparator.comparing(HillData::getHeight));
        }
    }

    /**
     * A method to limit the displayed results
     *
     * @param munroData for a given MunroModel
     */
    private static void limitResults(MunroModel munroData) {
        // todo errors 0, -1, Greater than total records
        String limitResults = MDC.get(Constants.PARAMETER_LIMIT_RESULTS);

        if (limitResults != null && !limitResults.equals(Constants.NULL)) {
            int limitResultsMDCValue = Integer.parseInt(limitResults) - 1;

            if (limitResultsMDCValue < munroData.getItems().size()) {
                List<HillData> hillDataList = new ArrayList<>();

                for (int i = 0; i <= limitResultsMDCValue; i++) {
                    hillDataList.add(munroData.getItems().get(i));
                }

                munroData.setItems(hillDataList);
            } else {
                // todo log error ???
            }
        }
    }

    /**
     * Returns the ResponseEntity<String> based on the performed actions of the query params
     * on the munro data
     *
     * @return {@linkplain ResponseEntity<String>}
     */
    public ResponseEntity<String> getResponse() {
        MunroModel munroData = getMunroData();

        filterByHillCategory(munroData);

        filterByHeight(munroData);

        sortByName(munroData);

        sortByHeight(munroData);

        limitResults(munroData);

        return ResponseEntity.ok(gson.toJson(munroData.getItems()));
    }
}
