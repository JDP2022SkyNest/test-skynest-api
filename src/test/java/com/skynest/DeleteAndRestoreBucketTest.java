package com.skynest;

import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class DeleteAndRestoreBucketTest extends BucketBaseTest {

    @Test
    void user_should_be_able_to_delete_created_bucket() {
        skyNestBackendClient.deleteBucket(createdBucketResponse.getBucketId()).then().statusCode(SC_OK);
    }

    @Test(dependsOnMethods = "user_should_be_able_to_delete_created_bucket")
    void user_should_be_able_to_restore_bucket_after_deleting() {
        skyNestBackendClient.restoreBucket(createdBucketResponse.getBucketId()).then().statusCode(SC_OK);
    }

}
