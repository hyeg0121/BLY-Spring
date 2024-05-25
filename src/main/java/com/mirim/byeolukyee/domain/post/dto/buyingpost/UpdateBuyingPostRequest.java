package com.mirim.byeolukyee.domain.post.dto.buyingpost;

import com.mirim.byeolukyee.global.constant.status.BuyingPostStatus;
import com.mirim.byeolukyee.domain.post.dto.UpdatePostRequeset;
import lombok.Getter;

@Getter
public class UpdateBuyingPostRequest extends UpdatePostRequeset {
    private BuyingPostStatus status;
}
