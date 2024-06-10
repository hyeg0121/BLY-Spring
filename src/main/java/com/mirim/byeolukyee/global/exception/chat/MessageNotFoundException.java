package com.mirim.byeolukyee.global.exception.chat;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.GeneralException;

public class MessageNotFoundException extends GeneralException {
    public static final GeneralException EXCEPTION = new MessageNotFoundException();
    public MessageNotFoundException() {
        super(ErrorCode.MESSAGE_NOT_FOUND);
    }
}
