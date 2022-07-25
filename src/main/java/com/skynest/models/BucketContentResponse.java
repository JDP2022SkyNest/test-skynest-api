package com.skynest.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BucketContentResponse {
    private UUID bucketId;
    private List<String> folders;
    private List<String> files;
    private List<String> path;
}
