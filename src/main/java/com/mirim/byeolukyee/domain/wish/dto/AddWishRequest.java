package com.mirim.byeolukyee.domain.wish.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddWishRequest {
    @NotBlank(message = "유저 아이디는 공백일 수 없습니다.")
    private Long userId;

    @NotBlank(message = "글 아이디는 공백일 수 없습니다.")
    private Long postId;
}
