package com.skynest;

import com.skynest.models.BucketResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class GetBucketByIdTest extends BucketBaseTest {

    @Test
    void logged_admin_should_be_able_to_view_details_of_specific_bucket() {
        Response getBucketResponse = skyNestBackendClient.getBucketById(createdBucketId);
        getBucketResponse.then().statusCode(SC_OK);

        BucketResponse bucketResponse = getBucketResponse.as(BucketResponse.class);

        assertNotNull(bucketResponse.getBucketId());
        assertEquals(bucketResponse.getName(), bucketName);
    }
}
