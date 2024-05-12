package com.mirim.byeolukyee.exception;


import com.mirim.byeolukyee.constant.ErrorCode;

public class UserNotFoundException extends GeneralException {
    public static final GeneralException EXCEPTION = new UserNotFoundException();
    private UserNotFoundException() {
        super(ErrorCode.USER_NOT_FOUND);
    }
}
