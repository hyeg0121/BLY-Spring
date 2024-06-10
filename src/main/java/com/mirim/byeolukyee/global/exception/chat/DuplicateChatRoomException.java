package com.mirim.byeolukyee.global.exception.chat;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.GeneralException;

public class DuplicateChatRoomException extends GeneralException {
    public static final GeneralException EXCEPTION = new DuplicateChatRoomException();
    public DuplicateChatRoomException() {
        super(ErrorCode.DUPLICATE_CHATROOM);
    }
}
