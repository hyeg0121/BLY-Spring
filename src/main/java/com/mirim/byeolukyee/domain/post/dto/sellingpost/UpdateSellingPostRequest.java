package com.mirim.byeolukyee.domain.post.dto.sellingpost;

import com.mirim.byeolukyee.global.constant.status.SellingPostStatus;
import com.mirim.byeolukyee.domain.post.dto.UpdatePostRequeset;
import lombok.Getter;

@Getter
public class UpdateSellingPostRequest extends UpdatePostRequeset {
    private SellingPostStatus status;
}
