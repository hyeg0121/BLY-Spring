package com.mirim.byeolukyee.domain.post.dto.sellingcomment;

import com.mirim.byeolukyee.domain.post.dto.AddPostRequest;
import lombok.Getter;

@Getter
public class AddSellingCommentRequest extends AddPostRequest {
    private Long referenceItemId;
}
