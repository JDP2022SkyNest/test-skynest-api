package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor

public class StorageContentResponse {
    private UUID bucketId;
    private List<FolderResponse> folders;
    private List<FileResponse> files;
    private List<ShortFolderResponse> path;
}
