package com.balanz.recommendations.exception;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import com.balanz.recommendations.model.ApiError;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {

    /**
     * Excepciones tiradas de forma automatica por los validators
     */

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ApiError> handleConstraintViolationException(final ConstraintViolationException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }   

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiError> handleHttpMessageNotReadableException(final HttpMessageNotReadableException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }   

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(final MethodArgumentTypeMismatchException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }   

    /**
     * Negociacion de tipo de respuesta (Accept)
     */
    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    protected ResponseEntity<String> handleHttpMediaTypeNotAcceptableException(final HttpMediaTypeNotAcceptableException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity<ApiError> handleMissingServletRequestParameterException(final MissingServletRequestParameterException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }
    
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    protected ResponseEntity<ApiError> handleHttpRequestMethodNotSupportedException(final HttpRequestMethodNotSupportedException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_REQUEST, ex));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    protected ResponseEntity<ApiError> handleMarketDataException(final ResourceNotFoundException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.NOT_FOUND, ex.getMessage()));
    }
    
    // Handle default, ninguna excepcion que sea tirada a mano deberia caer aca.
    // El objetivo es dar una respuesta consistente ante errores inesperados
    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ApiError> defaultHandler(final Exception ex) {
        final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex);
        // log con el mismo timestamp que la respuesta HTTP para poder encontrarlo con mayor certeza en caso de ser necesario 
        log.error("Response timestamp: {}.", apiError.getFormatedTimestamp(), ex);
        return buildResponseEntity(apiError);
    }

    private static ResponseEntity<ApiError> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}
