package com.mirim.byeolukyee.dto.sellingcomment;

import com.mirim.byeolukyee.dto.post.AddPostRequest;
import lombok.Getter;

@Getter
public class AddSellingCommentRequest extends AddPostRequest {
    private Long referenceItemId;
}
