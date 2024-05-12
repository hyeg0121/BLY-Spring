package com.mirim.byeolukyee.dto.post.sellingpost;

import com.mirim.byeolukyee.constant.status.SellingPostStatus;
import com.mirim.byeolukyee.dto.post.UpdatePostRequeset;
import lombok.Getter;

@Getter
public class UpdateSellingPostRequest extends UpdatePostRequeset {
    private SellingPostStatus status;
}
