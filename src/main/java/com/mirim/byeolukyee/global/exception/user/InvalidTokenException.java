package com.mirim.byeolukyee.global.exception.user;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.GeneralException;

public class InvalidTokenException extends GeneralException {

    public static GeneralException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
