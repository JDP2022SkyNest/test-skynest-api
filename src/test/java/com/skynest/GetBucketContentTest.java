package com.skynest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class GetBucketContentTest extends BucketBaseTest {

    //TODO Upgrade this test when you have tests for creating folders and files inside the bucket
    @Test
    void user_should_be_able_to_view_created_bucket_content() {
        Response response = skyNestBackendClient.getBucketContent(createdBucketResponse.getBucketId());
        response.then().statusCode(SC_OK);
    }
}
