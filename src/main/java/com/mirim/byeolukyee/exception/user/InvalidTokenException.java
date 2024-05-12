package com.mirim.byeolukyee.exception.user;

import com.mirim.byeolukyee.constant.code.ErrorCode;
import com.mirim.byeolukyee.exception.GeneralException;

public class InvalidTokenException extends GeneralException {

    public static GeneralException EXCEPTION = new InvalidTokenException();

    public InvalidTokenException() {
        super(ErrorCode.INVALID_TOKEN);
    }
}
