package com.mirim.byeolukyee.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirim.byeolukyee.constant.SellingCommentStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@DiscriminatorValue("SELLING_COMMENT")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellingComment extends Post {
    @JsonIgnore
    @ManyToOne(targetEntity = BuyingPost.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "reference_item_id")
    private BuyingPost referenceItem;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SellingCommentStatus status = SellingCommentStatus.NOT_WON;

    public void updatePost(String title, String description, Integer price, String location, SellingCommentStatus status) {
        super.updatePost(title, description, price, location);
        this.status = status;
    }
}
