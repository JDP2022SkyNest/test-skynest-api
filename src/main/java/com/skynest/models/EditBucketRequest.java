package com.skynest.models;

import com.skynest.utils.RandomGenerator;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class EditBucketRequest {
    private String name;
    private String description;
    private Boolean isPublic;

    public static EditBucketRequest generateValidBucketEditRequest() {
        return EditBucketRequest.builder()
                .name(RandomGenerator.generateRandomBucketName())
                .description(RandomGenerator.generateRandomBucketDescription())
                .isPublic(false)
                .build();
    }

}
