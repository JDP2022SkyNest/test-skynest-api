package com.skynest.models;

import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
public class FileResponse {
    private UUID id;
    private String createdOn;
    private String modifiedOn;
    private String deletedOn;
    private String name;
    private UUID createdById;
    private UUID parentFolderId;
    private UUID bucketId;
    private String type;
    private String size;
    private List<TagResponse> tags;
}
