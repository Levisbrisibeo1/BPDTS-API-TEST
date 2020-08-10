package common;

import com.sun.org.apache.regexp.internal.RE;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RestHelper {

    public static String END_POINT;
    public static RequestSpecBuilder REQUEST_BUILDER;
    public static RequestSpecification REQUEST_SPEC;
    public static ResponseSpecBuilder RESPONSE_BUILDER;
    public static ResponseSpecification RESPONSE_SPEC;

    public static void setEndPoint(String endPoint) {
        END_POINT = endPoint;
    }

    public static RequestSpecification getRequestSpec() {
        REQUEST_BUILDER = new RequestSpecBuilder();
        REQUEST_BUILDER.setBaseUri("http://bpdts-test-app-v2.herokuapp.com");
        REQUEST_SPEC = REQUEST_BUILDER.build();
        return REQUEST_SPEC;
    }

    public static ResponseSpecification getResponseSpec() {
        RESPONSE_BUILDER = new ResponseSpecBuilder();
        RESPONSE_BUILDER.expectStatusCode(200);
        RESPONSE_BUILDER.expectContentType(ContentType.JSON);
        RESPONSE_SPEC = RESPONSE_BUILDER.build();
        return RESPONSE_SPEC;
    }

    public static ResponseSpecification getResponseSpec404() {
        RESPONSE_BUILDER = new ResponseSpecBuilder();
        RESPONSE_BUILDER.expectStatusCode(404);
        RESPONSE_BUILDER.expectContentType(ContentType.JSON);
        RESPONSE_SPEC = RESPONSE_BUILDER.build();
        return RESPONSE_SPEC;
    }
}
