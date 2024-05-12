package com.mirim.byeolukyee.domain.post;

import com.mirim.byeolukyee.constant.status.SellingPostStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("SELLING_POST")
@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SellingPost extends Post {

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SellingPostStatus status = SellingPostStatus.IN_PROGRESS;

    @Builder.Default
    @OneToMany(
            mappedBy = "post",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    private List<PostImage> postImageList = new ArrayList<>();

    public void updatePost(String title, String description, Integer price, String location, SellingPostStatus status) {
        super.updatePost(title, description, price, location);
        this.status = status;
    }
}
