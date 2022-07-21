package com.skynest;

import com.skynest.models.BucketResponse;
import io.restassured.response.Response;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class RestoreBucketTest extends BaseTest {
    private UUID firstDeletedBucketId;

    @BeforeClass
    void getBucketId() {
        loginAs(Roles.ADMIN);
        Response getDeletedBucketsResponse = skyNestBackendClient.getDeletedBuckets();
        getDeletedBucketsResponse.then().statusCode(SC_OK);
        BucketResponse[] bucketResponses = getDeletedBucketsResponse.body().as(BucketResponse[].class);
        firstDeletedBucketId = getFirstBucketId(List.of(bucketResponses));
    }

    @Test
    void user_should_be_able_to_restore_specific_deleted_bucket() {
        skyNestBackendClient.restoreBucket(firstDeletedBucketId).then().statusCode(SC_OK);
    }

    @AfterClass
    void user_should_be_able_to_delete_bucket_after_restore() {
        skyNestBackendClient.deleteBucket(firstDeletedBucketId).then().statusCode(SC_OK);
    }

}
