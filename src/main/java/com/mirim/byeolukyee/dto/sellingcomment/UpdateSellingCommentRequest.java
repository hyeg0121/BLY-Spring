package com.mirim.byeolukyee.dto.sellingcomment;

import com.mirim.byeolukyee.constant.SellingCommentStatus;
import com.mirim.byeolukyee.dto.post.UpdatePostRequeset;
import lombok.Getter;

@Getter
public class UpdateSellingCommentRequest extends UpdatePostRequeset {
    private SellingCommentStatus status;
}
