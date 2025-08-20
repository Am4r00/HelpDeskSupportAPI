package com.helpdesk.supportapi.exceptions;

import com.helpdesk.supportapi.dto.ResponseErrorDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.NativeWebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final NativeWebRequest nativeWebRequest;

    public GlobalExceptionHandler(NativeWebRequest nativeWebRequest) {
        this.nativeWebRequest = nativeWebRequest;
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> treatUserNotFound(UserNotFoundException exception) {
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<ResponseErrorDTO> treatEmailAlreadyUsed(EmailAlreadyUsedException exception) {
        return buildErrorResponse(exception, HttpStatus.ALREADY_REPORTED);
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<ResponseErrorDTO> treatEmailNotFound(EmailNotFoundException exception) {
        return buildErrorResponse(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InvalidStatusException.class)
    public ResponseEntity<ResponseErrorDTO> treatInvalidStatus(InvalidStatusException exception) {
        return buildErrorResponse(exception, HttpStatus.MULTI_STATUS);
    }

    private ResponseEntity<ResponseErrorDTO> buildErrorResponse(RuntimeException exception, HttpStatus httpStatus) {
        ResponseErrorDTO error = new ResponseErrorDTO(
                httpStatus.value(),
                exception.getMessage(),
                LocalDateTime.now());

        return new ResponseEntity<>(error, httpStatus);
    }
}
