package com.mirim.byeolukyee.domain.post.dto.sellingcomment;

import com.mirim.byeolukyee.global.constant.status.SellingCommentStatus;
import com.mirim.byeolukyee.domain.post.dto.UpdatePostRequeset;
import lombok.Getter;

@Getter
public class UpdateSellingCommentRequest extends UpdatePostRequeset {
    private SellingCommentStatus status;
}
