package com.mirim.byeolukyee.global.constant.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SellingPostStatus {
    IN_PROGRESS("판매중"),
    RESERVED("예약중"),
    COMPLETE("판매완료");

    private String krName;

}
