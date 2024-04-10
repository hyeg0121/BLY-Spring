package com.mirim.byeolukyee.exception.handler;

import com.mirim.byeolukyee.exception.*;
import com.mirim.byeolukyee.exception.error.ErrorCode;
import com.mirim.byeolukyee.exception.error.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(ErrorCode.USER_NOT_FOUND));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDuplicateEmailException(DuplicateEmailException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(ErrorCode.DUPLICATE_EMAIL));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleIncorrectPasswordException(IncorrectPasswordException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(ErrorCode.INCORRECT_PASSWORD));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handlePostNotFoundException(PostNotFoundException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(ErrorCode.POST_NOT_FOUND));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleImageNotFoundException(ImageNotFoundException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(ErrorCode.IMAGE_NOT_FOUND));
    }
}
