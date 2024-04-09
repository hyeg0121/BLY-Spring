package com.mirim.byeolukyee.dto.sellingcomment;

import com.mirim.byeolukyee.dto.post.AddPostRequestDto;
import lombok.Getter;

@Getter
public class AddSellingCommentRequestDto extends AddPostRequestDto {
    private Long referenceItemId;
}
