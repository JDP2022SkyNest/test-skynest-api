package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BucketResponse {
    private UUID bucketId;
    private UUID createdById;
    private String name;
    private UUID companyId;
    private String description;
    private String size;
    private String isPublic;
}
