package com.skynest.models;

import com.skynest.utils.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EditBucketRequest {
    private String name;
    private String description;
    private Boolean isPublic;

    public static EditBucketRequest generateValidEditBucketRequest() {
        return EditBucketRequest.builder()
                .name(RandomGenerator.generateRandomBucketName())
                .description(RandomGenerator.generateRandomBucketDescription())
                .isPublic(false)
                .build();
    }
}
