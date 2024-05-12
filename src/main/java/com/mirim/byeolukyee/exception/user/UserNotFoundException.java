package com.mirim.byeolukyee.exception.user;


import com.mirim.byeolukyee.constant.code.ErrorCode;
import com.mirim.byeolukyee.exception.GeneralException;

public class UserNotFoundException extends GeneralException {
    public static final GeneralException EXCEPTION = new UserNotFoundException();
    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
