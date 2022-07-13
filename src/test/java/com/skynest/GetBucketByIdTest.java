package com.skynest;

import com.skynest.models.BucketResponse;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;
import java.util.UUID;

import static org.apache.http.HttpStatus.SC_OK;
import static org.testng.Assert.assertNotNull;

public class GetBucketByIdTest extends BaseTest{

    @Test
    void logged_admin_should_be_able_to_view_details_of_random_bucket() {
        loginAs(Roles.ADMIN);
        Response getAllBucketsResponse = skyNestBackendClient.getAllBuckets();
        getAllBucketsResponse.then().statusCode(SC_OK);

        BucketResponse[] bucketResponses = getAllBucketsResponse.body().as(BucketResponse[].class);
//        BucketResponse[] bucketResponses = new BucketResponse[0];
//
//        if(bucketResponses.length==0){
//            return;
//        }

        UUID firstBucketId = getFirstBucketId(List.of(bucketResponses));

        Response getBucketResponse = skyNestBackendClient.getBucketById(firstBucketId);
        getBucketResponse.then().statusCode(SC_OK);

        BucketResponse bucketResponse = getBucketResponse.as(BucketResponse.class);

        assertNotNull(bucketResponse.getBucketId());

    }
}
