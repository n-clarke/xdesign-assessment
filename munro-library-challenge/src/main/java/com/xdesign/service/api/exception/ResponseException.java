package com.xdesign.service.api.exception;

import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;

// todo : possibly remove???
public class ResponseException {

    private static final Gson gson = new Gson();

    public ResponseEntity<String> getResponseException(String errorMessage) {

        ResponseEntity<String> responseEntityException = null;

        ResponseEntity.badRequest();

        ResponseEntity.notFound();

        return responseEntityException;
    }
}
