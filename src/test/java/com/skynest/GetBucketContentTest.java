package com.skynest;

import com.skynest.models.BucketResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;

public class GetBucketContentTest extends BaseTest {
    @Test
    void logged_admin_should_be_able_to_view_bucket_content() {
        loginAs(Roles.ADMIN);
        Response getAllBucketsResponse = skyNestBackendClient.getAllBuckets();
        getAllBucketsResponse.then().statusCode(SC_OK);
        BucketResponse[] bucketResponses = getAllBucketsResponse.body().as(BucketResponse[].class);

        UUID firstBucketId = getFirstBucketId(List.of(bucketResponses));

        Response getBucketContentResponse = skyNestBackendClient.getBucketContent(firstBucketId);
        getBucketContentResponse.then().statusCode(SC_OK);

    }
}
