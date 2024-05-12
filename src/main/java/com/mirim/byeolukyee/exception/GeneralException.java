package com.mirim.byeolukyee.exception;

import com.mirim.byeolukyee.constant.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException{
    private final ErrorCode errorCode;
}
