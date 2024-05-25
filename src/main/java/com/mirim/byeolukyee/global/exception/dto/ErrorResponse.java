package com.mirim.byeolukyee.global.exception.dto;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatus httpStatus;
    private Integer statusCode;
    private String errorMessage;

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getHttpStatus(), errorCode.getHttpStatus().value(), errorCode.getErrorMessage());
    }
}
