package com.mirim.byeolukyee.domain.post.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mirim.byeolukyee.domain.wish.entity.Wish;
import com.mirim.byeolukyee.global.constant.status.SellingPostStatus;
import com.mirim.byeolukyee.domain.image.entity.PostImage;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @Builder.Default
    @JsonIgnore
    @OneToMany(mappedBy = "post", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<Wish> wishes = new HashSet<>();
    public void updatePost(String title, String description, Integer price, String location, SellingPostStatus status) {
        super.updatePost(title, description, price, location);
        this.status = status;
    }
}
