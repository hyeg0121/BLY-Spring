package com.mirim.byeolukyee.global.exception.post;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.GeneralException;

public class PostNotFoundException extends GeneralException {
    public static final GeneralException EXCEPTION = new PostNotFoundException();
    private PostNotFoundException() {
        super(ErrorCode.POST_NOT_FOUND);
    }
}
