package com.skynest;

import com.skynest.models.BucketResponse;
import com.skynest.models.CreateBucketRequest;
import com.skynest.models.FileResponse;
import com.skynest.models.FolderResponse;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertNotNull;

public class BucketBaseTest extends BaseTest {
    protected BucketResponse createdBucketResponse;
    protected FolderResponse createdFolderResponse;
    protected FileResponse uploadedFileResponse;

    @BeforeClass
    void create_a_bucket_for_test_purpose() {
        loginAs(Roles.ADMIN);
        Response response = skyNestBackendClient.createBucket(CreateBucketRequest.generateValidBucketCreationRequest());
        response.then().statusCode(SC_OK);

        BucketResponse createBucketResponse = response.as(BucketResponse.class);

        assertNotNull(createBucketResponse.getBucketId());

        createdBucketResponse = createBucketResponse;
    }


    @AfterClass
    void delete_bucket_after_all_bucket_related_tests() {
        skyNestBackendClient.deleteBucket(createdBucketResponse.getBucketId()).then().statusCode(SC_OK);
    }

}
