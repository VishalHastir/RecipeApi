package com.recipe.api.exception;

import java.util.List;
import java.util.stream.Collectors;
import com.recipe.api.model.GlobalResponse;
import com.recipe.api.util.CustomMessagesUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * The type Global exception handler.
 *
 * @author Vishal
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private final CustomMessagesUtil customMessagesUtil;

    /**
     * Instantiates a new Global exception handler.
     *
     * @param customMessagesUtil the custom messages util
     */
    @Autowired
    public GlobalExceptionHandler(CustomMessagesUtil customMessagesUtil) {
        this.customMessagesUtil = customMessagesUtil;
    }

    /**
     * Handle not found exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseBody
    public ResponseEntity<GlobalResponse> handleNotFoundException(DataNotFoundException ex) {
        HttpStatus status = ex.getStatus() == null ? HttpStatus.NOT_FOUND : ex.getStatus();
        return buildResponse(ex.getMessage(), status);
    }

    /**
     * Handle argument exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler({
            IllegalArgumentException.class,
            InvalidDataAccessApiUsageException.class
    })
    @ResponseBody
    public ResponseEntity<GlobalResponse> handleArgumentException(Exception ex) {
        return buildResponse(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle data integrity violation exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<GlobalResponse> handleDataIntegrityViolationException(Exception ex) {
        if (ex.getCause() instanceof org.hibernate.exception.ConstraintViolationException) {
            String message = customMessagesUtil.getMessage("recipe.unableToDelete");
            return buildResponse(message, HttpStatus.BAD_REQUEST);
        }

        return handleGlobalException();
    }

    /**
     * Handle method argument not valid exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseEntity<GlobalResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        String errorMessage = fieldErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return buildResponse(errorMessage, HttpStatus.BAD_REQUEST);
    }


    /**
     * Handle http message not readable exception response entity.
     *
     * @param ex the ex
     * @return the response entity
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseBody
    public ResponseEntity<GlobalResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {
        String message = customMessagesUtil.getMessage("json.invalidFormat");
        return buildResponse(message, HttpStatus.FORBIDDEN);
    }


    /**
     * Handle global exception response entity.
     *
     * @return the response entity
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<GlobalResponse> handleGlobalException() {
        String message = customMessagesUtil.getMessage("error.internalServerError");
        return buildResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<GlobalResponse> buildResponse(String message, HttpStatus status) {
        return new ResponseEntity<>(new GlobalResponse(message), status);
    }
}
