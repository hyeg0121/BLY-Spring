package com.mirim.byeolukyee.global.constant.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SellingCommentStatus {
    NOT_WON("낙찰되지 않음"),
    WON("낙찰");

    private String krName;
}
