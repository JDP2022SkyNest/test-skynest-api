package com.skynest;

import org.testng.annotations.Test;

import static org.apache.http.HttpStatus.SC_OK;

public class GetDeletedBucketsTest extends BaseTest {

    @Test
    void logged_user_should_be_able_to_view_list_of_deleted_buckets() {
        loginAs(Roles.WORKER);
        skyNestBackendClient.getDeletedBuckets().then().statusCode(SC_OK);
    }
}
