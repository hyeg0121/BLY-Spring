package com.mirim.byeolukyee.exception;

import com.mirim.byeolukyee.constant.ErrorCode;

public class InvalidTokenException extends GeneralException {

    public static GeneralException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
