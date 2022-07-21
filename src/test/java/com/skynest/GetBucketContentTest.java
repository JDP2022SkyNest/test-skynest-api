package com.skynest;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class GetBucketContentTest extends BucketBaseTest {

    @Test
    void user_should_be_able_to_view_created_bucket_content() {
        Response response = skyNestBackendClient.getBucketContent(createdBucketId);
        response.then().statusCode(SC_OK);
    }
}
