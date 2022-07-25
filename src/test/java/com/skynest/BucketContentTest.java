package com.skynest;

import com.skynest.models.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class BucketContentTest extends BucketBaseTest {

    //TODO Upgrade this test when you have tests for creating folders and files inside the bucket
    @Test
    void bucket_content_should_be_empty_when_folders_and_files_are_not_added() {
        Response response = skyNestBackendClient.getBucketContent(createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);

        BucketContentResponse getBucketContentResponse = response.as(BucketContentResponse.class);

        assertEquals(getBucketContentResponse.getFolders().size(),0);
        assertEquals(getBucketContentResponse.getFiles().size(),0);
    }

    @Test(dependsOnMethods = "bucket_content_should_be_empty_when_folders_and_files_are_not_added")
    void logged_owner_should_be_able_to_create_folder_in_its_own_private_bucket() {
        CreateFolderRequest createFolderRequest = CreateFolderRequest.generateValidFolderCreationRequest(createdBucketResponse.getBucketId());
        Response response = skyNestBackendClient.createFolder(createFolderRequest);
        response.then().statusCode(SC_OK);

        FolderResponse createFolderResponse = response.as(FolderResponse.class);

        createdFolderResponse = createFolderResponse;

        assertNotNull(createFolderResponse.getId());
        assertEquals(createFolderResponse.getName(), createFolderRequest.getName());
        assertEquals(createFolderResponse.getParentFolderId(), createFolderRequest.getParentFolderId());
        assertEquals(createFolderResponse.getBucketId(), createFolderRequest.getBucketId());

    }

    @Test(dependsOnMethods = "logged_owner_should_be_able_to_create_folder_in_its_own_private_bucket")
    void bucket_content_should_contains_all_added_folders() {
        Response response = skyNestBackendClient.getBucketContent(createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);

        BucketContentResponse getBucketContentResponse = response.as(BucketContentResponse.class);

        assertEquals(getBucketContentResponse.getFolders().size(),1);
        assertEquals(getBucketContentResponse.getFiles().size(),0);
    }

}
