package com.skynest;

import com.skynest.models.BucketResponse;
import com.skynest.models.CreateBucketRequest;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertNotNull;

public class BucketBaseTest extends BaseTest {
    protected UUID createdBucketId;
    protected String bucketName;

    @BeforeClass
    void create_a_bucket_for_test_purpose() {
        loginAs(Roles.ADMIN);
        Response response = skyNestBackendClient.createBucket(CreateBucketRequest.generateValidBucketCreationRequest());
        response.then().statusCode(SC_OK);

        BucketResponse createBucketResponse = response.as(BucketResponse.class);

        assertNotNull(createBucketResponse.getBucketId());

        createdBucketId = createBucketResponse.getBucketId();
        bucketName = createBucketResponse.getName();
    }

    @AfterClass
    void delete_bucket_after_all_bucket_related_tests() {
        skyNestBackendClient.deleteBucket(createdBucketId).then().statusCode(SC_OK);
    }

}
