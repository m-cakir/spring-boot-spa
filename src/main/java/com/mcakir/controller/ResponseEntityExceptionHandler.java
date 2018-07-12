package com.mcakir.controller;

import com.mcakir.data.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

// https://gist.github.com/matsev/4519323

@ControllerAdvice
public class ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ApiResponse methodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
        List<String> errors = new ArrayList<>(fieldErrors.size() + globalErrors.size());
        String error;
        for (FieldError fieldError : fieldErrors) {
            error = fieldError.getField() + ", " + fieldError.getDefaultMessage();
            errors.add(error);
        }
        for (ObjectError objectError : globalErrors) {
            error = objectError.getObjectName() + ", " + objectError.getDefaultMessage();
            errors.add(error);
        }

        return ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .errors(errors).build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ResponseBody
    ApiResponse mediaTypeNotSupported(HttpMediaTypeNotSupportedException ex) {
        String unsupported = "Unsupported content type: " + ex.getContentType();
        String supported = "Supported content types: " + MediaType.toString(ex.getSupportedMediaTypes());

        return ApiResponse.builder()
                .status(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                .error(supported)
                .error(unsupported)
                .build();
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    ApiResponse messageNotReadable(HttpMessageNotReadableException ex) {

        String message = ex.getMessage();
        String description = null;

        Throwable mostSpecificCause = ex.getMostSpecificCause();
        if (mostSpecificCause != null) {
            description = mostSpecificCause.getClass().getName();
            message = mostSpecificCause.getMessage();
        }

        return ApiResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(message)
                .description(description)
                .build();
    }
}
