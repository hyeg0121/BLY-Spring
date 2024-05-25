package com.mirim.byeolukyee.global.exception;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class GeneralException extends RuntimeException{
    private final ErrorCode errorCode;
}
