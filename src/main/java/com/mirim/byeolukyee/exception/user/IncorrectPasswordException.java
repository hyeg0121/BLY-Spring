package com.mirim.byeolukyee.exception.user;

import com.mirim.byeolukyee.constant.code.ErrorCode;
import com.mirim.byeolukyee.exception.GeneralException;

public class IncorrectPasswordException extends GeneralException {
    public static final GeneralException EXCEPTION = new IncorrectPasswordException();
    private IncorrectPasswordException() {
        super(ErrorCode.INCORRECT_PASSWORD);
    }
}
