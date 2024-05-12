package com.mirim.byeolukyee.exception.image;

import com.mirim.byeolukyee.constant.code.ErrorCode;
import com.mirim.byeolukyee.exception.GeneralException;

public class ImageNotFoundException extends GeneralException {
    public static GeneralException EXCEPTION = new ImageNotFoundException();
    private ImageNotFoundException() {
        super(ErrorCode.IMAGE_NOT_FOUND);
    }
}
