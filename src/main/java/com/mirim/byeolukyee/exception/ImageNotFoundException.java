package com.mirim.byeolukyee.exception;

import com.mirim.byeolukyee.constant.ErrorCode;

public class ImageNotFoundException extends GeneralException {
    public static GeneralException EXCEPTION = new ImageNotFoundException();
    private ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
