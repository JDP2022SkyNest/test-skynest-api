package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateBucketResponse {
    private String bucketId;
    private String createdById;
    private String name;
    private String companyId;
    private String description;
    private String size;
    private String isPublic;
}
