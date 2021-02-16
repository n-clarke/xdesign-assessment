package com.xdesign.service.api.service;

import com.google.gson.Gson;
import com.xdesign.service.api.constants.Constants;
import com.xdesign.service.api.constants.HillCategory;
import com.xdesign.service.api.model.HillData;
import com.xdesign.service.api.test.utils.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;

import static com.xdesign.service.api.test.utils.DataFactory.createHillDataFromJSON;
import static com.xdesign.service.api.test.utils.DataFactory.createHillDataFromString;

@DisplayName("Performing a Unit Test for MainService")
public class MainServiceTest {

    public static final String FILE_SRC_EXPECTED_RESPONSE_NO_QUERY_PARAMETERS = "expected_api_response_no_query_parameters.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_CATEGORY_TOP = "expected_api_response_query_parameters_category_top.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_CATEGORY_MUN = "expected_api_response_query_parameters_category_mun.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_CATEGORY_EITHER = "expected_api_response_query_parameters_category_either.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_HEIGHT_ASC = "expected_api_response_query_parameters_sort_by_height_asc.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_HEIGHT_DESC = "expected_api_response_query_parameters_sort_by_height_desc.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_NAME_ASC = "expected_api_response_query_parameters_sort_by_name_asc.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_NAME_DESC = "expected_api_response_query_parameters_sort_by_name_desc.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_LIMIT_1 = "expected_api_response_query_parameters_limit_results_to_one.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_LIMIT_10 = "expected_api_response_query_parameters_limit_results_to_ten.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_TWO = "expected_api_response_query_parameters_filter_by_max_height_of_two.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_TEN = "expected_api_response_query_parameters_filter_by_max_height_of_ten.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_SEVENTEEN_POINT_ONE = "expected_api_response_query_parameters_filter_by_max_height_of_seventeen_point_one.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_TWO = "expected_api_response_query_parameters_filter_by_min_height_of_two.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_TEN = "expected_api_response_query_parameters_filter_by_min_height_of_ten.json";
    public static final String FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_SEVENTEEN_POINT_ONE = "expected_api_response_query_parameters_filter_by_min_height_of_seventeen_point_one.json";

    @Mock
    private static final Gson gson = new Gson();

    private MainService mainService = new MainService();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(mainService, "gson", gson);
    }

    @BeforeEach
    public void beforeEach() {
        // Default STATE
        DataFactory.setAllMDCValues(HillCategory.EITHER.toString(), Constants.BLANK, Constants.BLANK, 602, 0, 602);
    }

    @Nested
    @DisplayName("When Valid GET Munro Response")
    class GivenValidGetResponse {

        @Nested
        @DisplayName("When Default Response With No Query Parameters")
        class WhenDefaultResponse {

            @Test
            @DisplayName("Then Return Full Response")
            @Tag("Validation")
            void thenReturnFullResponse() {
                DataFactory.setMDCValueFilterHillCategory(HillCategory.EITHER.toString());

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_NO_QUERY_PARAMETERS);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }
        }

        @Nested
        @DisplayName("When Query Parameters Category Are Set")
        class WhenQueryParamHillCategoryIsSet {

            @Test
            @DisplayName("Then Return Full Response using 'MUN' hill category")
            @Tag("Validation")
            void thenReturnFullResponseUsingCategoryMUN() {
                DataFactory.setMDCValueFilterHillCategory(HillCategory.MUN.toString());

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_CATEGORY_MUN);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return Full Response using 'top' hill category")
            @Tag("Validation")
            void thenReturnFullResponseUsingCategoryTOP() {
                DataFactory.setMDCValueFilterHillCategory(HillCategory.TOP.toString());

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_CATEGORY_TOP);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return Full Response using 'either' hill category")
            @Tag("Validation")
            void thenReturnFullResponseUsingCategoryEITHER() {
                DataFactory.setMDCValueFilterHillCategory(HillCategory.EITHER.toString());

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_CATEGORY_EITHER);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }
        }

        @Nested
        @DisplayName("When Query Parameters Sort By Height Are Set")
        class WhenQueryParamSortByHeightIsSet {

            @Test
            @DisplayName("Then Return Full Response using 'ASC' order")
            @Tag("Validation")
            void thenReturnFullResponseUsingASC() {
                DataFactory.setMDCValueSortByHeight(Constants.ASC);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_HEIGHT_ASC);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return Full Response using 'DESC' order")
            @Tag("Validation")
            void thenReturnFullResponseUsingDESC() {
                DataFactory.setMDCValueSortByHeight(Constants.DESC);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_HEIGHT_DESC);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }
        }

        @Nested
        @DisplayName("When Query Parameters Sort By Name Are Set")
        class WhenQueryParamSortByNameIsSet {

            @Test
            @DisplayName("Then Return Full Response using 'ASC' order")
            @Tag("Validation")
            void thenReturnFullResponseUsingASC() {
                DataFactory.setMDCValueSortByName(Constants.ASC);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_NAME_ASC);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return Full Response using 'DESC' order")
            @Tag("Validation")
            void thenReturnFullResponseUsingDESC() {
                DataFactory.setMDCValueSortByName(Constants.DESC);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_NAME_DESC);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }
        }

        @Nested
        @DisplayName("When Query Parameters Result Limit is Set")
        class WhenQueryParamLimitResultsSet {

            @Test
            @DisplayName("Then Return 1 Result")
            @Tag("Validation")
            void thenReturnFullResponseUsingResultLimitOfOne() {
                DataFactory.setMDCValueLimit(1);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_LIMIT_1);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return 10 Results")
            @Tag("Validation")
            void thenReturnFullResponseUsingResultLimitOfTen() {
                DataFactory.setMDCValueLimit(10);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_LIMIT_10);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }
        }

        @Nested
        @DisplayName("When Query Parameters Filter By Max Height")
        class WhenQueryParamMaxHeightIsSet {

            @Test
            @DisplayName("Then Return Results Under 2")
            @Tag("Validation")
            void thenReturnFullResponseUsingMaxHeightOfTwo() {
                DataFactory.setMDCValueFilterMaxHeight(2);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_TWO);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return Results Under 10")
            @Tag("Validation")
            void thenReturnFullResponseUsingMaxHeightOfTen() {
                DataFactory.setMDCValueFilterMaxHeight(10);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_TEN);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return Results Under 17.1")
            @Tag("Validation")
            void thenReturnFullResponseUsingMaxHeightOfSeventeenPointOne() {
                DataFactory.setMDCValueFilterMaxHeight(17.1);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MAX_HEIGHT_OF_SEVENTEEN_POINT_ONE);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }
        }

        @Nested
        @DisplayName("When Query Parameters Filter By Min Height")
        class WhenQueryParamMinHeightIsSet {

            @Test
            @DisplayName("Then Return Results Above 2")
            @Tag("Validation")
            void thenReturnFullResponseUsingMinHeightOfTwo() {
                DataFactory.setMDCValueFilterMinHeight(2);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_TWO);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return Results Above 10")
            @Tag("Validation")
            void thenReturnFullResponseUsingMinHeightOfTen() {
                DataFactory.setMDCValueFilterMinHeight(10);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_TEN);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }

            @Test
            @DisplayName("Then Return Results Above 17.1")
            @Tag("Validation")
            void thenReturnFullResponseUsingMinHeightOfSeventeenPointOne() {
                DataFactory.setMDCValueFilterMinHeight(17.1);

                ResponseEntity<String> actualResponse = mainService.getResponse();

                List<HillData> expectedHillData = createHillDataFromJSON(FILE_SRC_EXPECTED_RESPONSE_QUERY_PARAMETERS_MIN_HEIGHT_OF_SEVENTEEN_POINT_ONE);
                List<HillData> actualHillData = createHillDataFromString(actualResponse.getBody());

                Assert.assertEquals(HttpStatus.OK, actualResponse.getStatusCode());
                Assert.assertNotNull(actualResponse.getBody());
                Assert.assertEquals(expectedHillData, actualHillData);
            }
        }
    }

}
