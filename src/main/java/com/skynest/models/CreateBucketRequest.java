package com.skynest.models;

import com.skynest.utils.RandomGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;
import lombok.Builder;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class CreateBucketRequest {

    private String name;
    private String description;

    public static CreateBucketRequest generateValidBucketCreationRequest() {
        return CreateBucketRequest.builder()
                .name(RandomGenerator.generateRandomBucketName())
                .description(RandomGenerator.generateRandomBucketDescription())
                .build();
    }
}
