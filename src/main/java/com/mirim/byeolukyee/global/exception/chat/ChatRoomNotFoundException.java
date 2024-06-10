package com.mirim.byeolukyee.global.exception.chat;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.GeneralException;

public class ChatRoomNotFoundException extends GeneralException {
    public static final GeneralException EXCEPTION = new ChatRoomNotFoundException();
    public ChatRoomNotFoundException() {
        super(ErrorCode.CHATROOM_NOT_FOUND);
    }
}
