package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class FolderResponse {
    private UUID id;
    private String createdOn;
    private String modifiedOn;
    private String deletedOn;
    private String name;
    private UUID createdById;
    private UUID parentFolderId;
    private UUID bucketId;
    private List<String> tags;
}
