package com.skynest;

import com.skynest.models.BucketResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.UUID;

import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class GetBucketByIdTest extends BucketBaseTest {

    @Test
    void user_should_be_able_to_view_details_of_specific_bucket_by_valid_id() {
        Response getBucketResponse = skyNestBackendClient.getBucketById(createdBucketResponse.getBucketId());
        getBucketResponse.then().statusCode(SC_OK);

        BucketResponse bucketResponse = getBucketResponse.as(BucketResponse.class);

        assertNotNull(bucketResponse.getBucketId());
        assertEquals(bucketResponse.getName(), createdBucketResponse.getName());
        assertEquals(bucketResponse.getDescription(), createdBucketResponse.getDescription());
        assertEquals(bucketResponse.getIsPublic(), createdBucketResponse.getIsPublic());
    }

    @Test
    void user_should_not_be_able_to_view_details_of_bucket_by_not_existing_id() {
        Response getBucketResponse = skyNestBackendClient.getBucketById(UUID.randomUUID());
        getBucketResponse.then().statusCode(SC_NOT_FOUND);
    }
}
