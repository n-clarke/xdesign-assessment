package com.xdesign.service.api.controller;

import com.xdesign.service.api.constants.Constants;
import com.xdesign.service.api.model.MunroModel;
import com.xdesign.service.api.service.MainService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;

@RestController
public class Controller {

    public static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private MainService service;

    @ApiOperation(
            value = "GET Request : Get Munro Data",
            notes = "API that allows sorting and filtering of munro data.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "OK", response = MunroModel.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = Serializable.class)
    })
    @GetMapping(value = Constants.BASE_API_ENDPOINT + Constants.API_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getService(
            @RequestParam(value = Constants.PARAMETER_FILTER_HILL_CATEGORY, required = false, defaultValue = "either") String filterHillCategory,
            @RequestParam(value = Constants.PARAMETER_SORT_BY_HEIGHT, required = false) String sortByHeight,
            @RequestParam(value = Constants.PARAMETER_SORT_BY_NAME, required = false) String sortByName,
            @RequestParam(value = Constants.PARAMETER_LIMIT_RESULTS, required = false) String limitResults,
            @RequestParam(value = Constants.PARAMETER_FILTER_MIN_HEIGHT, required = false) String filterMinHeight,
            @RequestParam(value = Constants.PARAMETER_FILTER_MAX_HEIGHT, required = false) String filterMaxHeight
    ) {
        MDC.put(Constants.PARAMETER_FILTER_HILL_CATEGORY, String.valueOf(filterHillCategory));
        MDC.put(Constants.PARAMETER_SORT_BY_HEIGHT, String.valueOf(sortByHeight));
        MDC.put(Constants.PARAMETER_SORT_BY_NAME, String.valueOf(sortByName));
        MDC.put(Constants.PARAMETER_LIMIT_RESULTS, String.valueOf(limitResults));
        MDC.put(Constants.PARAMETER_FILTER_MIN_HEIGHT, String.valueOf(filterMinHeight));
        MDC.put(Constants.PARAMETER_FILTER_MAX_HEIGHT, String.valueOf(filterMaxHeight));

        ResponseEntity<String> response = service.getResponse();
        LOGGER.info("SENDING_API_SUCCESSFUL_RESPONSE\n" + "HTTP Status : " + "200");
        return response;
    }

}
