package com.mirim.byeolukyee.dto.buyingpost;

import com.mirim.byeolukyee.constant.BuyingPostStatus;
import com.mirim.byeolukyee.dto.post.UpdatePostRequeset;
import lombok.Getter;

@Getter
public class UpdateBuyingPostRequest extends UpdatePostRequeset {
    private BuyingPostStatus status;
}
