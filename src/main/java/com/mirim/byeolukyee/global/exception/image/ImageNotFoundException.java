package com.mirim.byeolukyee.global.exception.image;

import com.mirim.byeolukyee.global.constant.code.ErrorCode;
import com.mirim.byeolukyee.global.exception.GeneralException;

public class ImageNotFoundException extends GeneralException {
    public static GeneralException EXCEPTION = new ImageNotFoundException();
    private ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
