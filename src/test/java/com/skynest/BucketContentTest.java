package com.skynest;

import com.skynest.models.CreateFolderRequest;
import com.skynest.models.FileResponse;
import com.skynest.models.FolderResponse;
import com.skynest.models.StorageContentResponse;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.apache.http.HttpStatus.*;
import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class BucketContentTest extends BucketBaseTest {

    //TODO Upgrade this test when you have tests for creating folders and files inside the bucket
    @Test
    void bucket_content_should_be_empty_when_folders_and_files_are_not_added() {
        Response response = skyNestBackendClient.getBucketContent(createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);

        StorageContentResponse getStorageContentResponse = response.as(StorageContentResponse.class);

        assertEquals(getStorageContentResponse.getFolders().size(), 0);
        assertEquals(getStorageContentResponse.getFiles().size(), 0);
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
    void logged_owner_should_be_able_to_upload_file_in_its_own_private_bucket() {
        Response response = skyNestBackendClient.uploadFile(createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);

        FileResponse uploadFileResponse = response.as(FileResponse.class);

        uploadedFileResponse = uploadFileResponse;

        assertEquals(uploadFileResponse.getName(), "FileForUpload.txt");
        assertEquals(uploadFileResponse.getBucketId(), createdBucketResponse.getBucketId());
    }

    @Test(dependsOnMethods = "logged_owner_should_be_able_to_upload_file_in_its_own_private_bucket")
    void bucket_content_should_contains_all_added_folders_and_files() {
        Response response = skyNestBackendClient.getBucketContent(createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);

        StorageContentResponse getBucketContentResponse = response.as(StorageContentResponse.class);

        assertEquals(getBucketContentResponse.getFolders().size(), 1);
        assertEquals(getBucketContentResponse.getFiles().size(), 1);
    }

    //Enable this test when permissions are implemented
    @Test(enabled = false)
    void user_without_permissions_should_not_be_able_to_create_folder_in_private_bucket() {
        loginAs(Roles.WORKER);
        CreateFolderRequest createFolderRequest = CreateFolderRequest.generateValidFolderCreationRequest(createdBucketResponse.getBucketId());
        Response response = skyNestBackendClient.createFolder(createFolderRequest);
        response.then().statusCode(SC_FORBIDDEN);
    }

    //Enable this test when permissions are implemented
    @Test(enabled = false)
    void user_without_permissions_should_not_be_able_to_upload_file_in_private_bucket() {
        loginAs(Roles.WORKER);
        Response response = skyNestBackendClient.uploadFile(createdBucketResponse.getBucketId());
        response.then().statusCode(SC_FORBIDDEN);
    }

    @Test
    void user_should_not_be_able_to_view_bucket_content_by_not_existing_id() {
        Response getBucketContentResponse = skyNestBackendClient.getBucketContent(UUID.randomUUID());
        getBucketContentResponse.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void logged_owner_should_not_be_able_to_create_folder_in_not_existing_bucket() {
        CreateFolderRequest createFolderRequest = CreateFolderRequest.generateValidFolderCreationRequest(UUID.randomUUID());
        Response response = skyNestBackendClient.createFolder(createFolderRequest);
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test
    void logged_owner_should_not_be_able_to_upload_file_in_not_existing_bucket() {
        Response response = skyNestBackendClient.uploadFile(UUID.randomUUID());
        response.then().statusCode(SC_NOT_FOUND);
    }

    @Test(dataProvider = "invalidFolderData")
    void logged_owner_should_not_be_able_to_create_folder_with_invalid_data(CreateFolderRequest createFolderRequest) {
        Response response = skyNestBackendClient.createFolder(createFolderRequest);
        response.then().statusCode(SC_BAD_REQUEST);
    }

    @DataProvider(name = "invalidFolderData")
    public Object[][] getInvalidFolderData() {
        return new Object[][]{
                new Object[]{CreateFolderRequest.generateValidFolderCreationRequest(createdBucketResponse.getBucketId()).withName("")},
                {CreateFolderRequest.generateValidFolderCreationRequest(createdBucketResponse.getBucketId()).withName(null)},
                {CreateFolderRequest.generateValidFolderCreationRequest(null)},
        };
    }

}
