package com.mirim.byeolukyee.dto.sellingcomment;

import com.mirim.byeolukyee.constant.SellingCommentStatus;
import com.mirim.byeolukyee.dto.post.UpdatePostRequesetDto;
import lombok.Getter;

@Getter
public class UpdateSellingCommentRequestDto extends UpdatePostRequesetDto {
    private SellingCommentStatus status;
}
