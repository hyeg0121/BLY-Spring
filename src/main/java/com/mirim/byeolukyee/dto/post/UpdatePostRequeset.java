package com.mirim.byeolukyee.dto.post;

import lombok.Getter;

@Getter
public class UpdatePostRequeset {
    protected String title;
    protected String description;
    protected Integer price;
    protected String location;

}
