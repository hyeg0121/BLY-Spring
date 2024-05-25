package com.mirim.byeolukyee.global.exception.user;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.GeneralException;

public class IncorrectPasswordException extends GeneralException {
    public static final GeneralException EXCEPTION = new IncorrectPasswordException();
    private IncorrectPasswordException() {
        super(ErrorCode.INCORRECT_PASSWORD);
    }
}
