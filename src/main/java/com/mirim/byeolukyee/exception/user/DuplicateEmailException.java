package com.mirim.byeolukyee.exception.user;

import com.mirim.byeolukyee.constant.code.ErrorCode;
import com.mirim.byeolukyee.exception.GeneralException;

public class DuplicateEmailException extends GeneralException {
    public static GeneralException EXCEPTION = new DuplicateEmailException();

    private DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL);
    }
}
