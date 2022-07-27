package com.skynest.models;

import com.skynest.utils.RandomGenerator;
import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@With
public class CreateFolderRequest {
    private String name;
    private UUID parentFolderId;
    private UUID bucketId;

    public static CreateFolderRequest generateValidFolderCreationRequest(UUID bucketId) {
        return CreateFolderRequest.builder()
                .name(RandomGenerator.generateRandomFolderName())
                .parentFolderId(null)
                .bucketId(bucketId)
                .build();
    }
}
