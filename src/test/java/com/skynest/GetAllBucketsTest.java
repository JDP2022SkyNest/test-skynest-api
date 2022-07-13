package com.skynest;

import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_FORBIDDEN;
import static org.apache.http.HttpStatus.SC_OK;

public class GetAllBucketsTest extends BaseTest {

    @Test
    void logged_admin_should_be_able_to_view_details_of_all_buckets() {
        loginAs(Roles.ADMIN);
        skyNestBackendClient.getAllBuckets().then().statusCode(SC_OK);
    }

    //Bucket permissions are not implemented yet!
    @Test
    void logged_worker_should_not_be_able_to_view_details_of_all_buckets() {
        loginAs(Roles.WORKER);
        skyNestBackendClient.getAllBuckets().then().statusCode(SC_FORBIDDEN);
    }
}
