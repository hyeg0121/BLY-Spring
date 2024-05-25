package com.mirim.byeolukyee.global.exception.user;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.GeneralException;

public class DuplicateEmailException extends GeneralException {
    public static GeneralException EXCEPTION = new DuplicateEmailException();

    private DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}
