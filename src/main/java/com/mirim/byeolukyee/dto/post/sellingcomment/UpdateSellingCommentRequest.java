package com.mirim.byeolukyee.dto.post.sellingcomment;

import com.mirim.byeolukyee.constant.status.SellingCommentStatus;
import com.mirim.byeolukyee.dto.post.UpdatePostRequeset;
import lombok.Getter;

@Getter
public class UpdateSellingCommentRequest extends UpdatePostRequeset {
    private SellingCommentStatus status;
}
