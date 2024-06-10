package com.mirim.byeolukyee.global.exception.handler;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.chat.ChatRoomNotFoundException;
import com.mirim.byeolukyee.global.exception.chat.DuplicateChatRoomException;
import com.mirim.byeolukyee.global.exception.chat.MessageNotFoundException;
import com.mirim.byeolukyee.global.exception.dto.ErrorResponse;
import com.mirim.byeolukyee.global.exception.image.ImageNotFoundException;
import com.mirim.byeolukyee.global.exception.post.PostNotFoundException;
import com.mirim.byeolukyee.global.exception.user.DuplicateEmailException;
import com.mirim.byeolukyee.global.exception.user.IncorrectPasswordException;
import com.mirim.byeolukyee.global.exception.user.InvalidTokenException;
import com.mirim.byeolukyee.global.exception.user.UserNotFoundException;
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

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleInvalidTokenException(InvalidTokenException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleChatRoomNotFoundException(ChatRoomNotFoundException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleDuplicateChatRoomException(DuplicateChatRoomException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleMessageNotFoundException(MessageNotFoundException e) {
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
                .body(ErrorResponse.of(e.getErrorCode()));
    }
}
