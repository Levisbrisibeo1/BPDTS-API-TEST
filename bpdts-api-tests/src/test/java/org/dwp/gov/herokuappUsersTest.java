package org.dwp.gov;

import common.RestHelper;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.BeforeClass;
import org.junit.Test;
import com.tngtech.java.junit.dataprovider.*;
import org.junit.runner.RunWith;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@RunWith(DataProviderRunner.class)
public class herokuappUsersTest {

    private static RequestSpecification requestSpec;
    private static ResponseSpecification responseSpec;
    private static ResponseSpecification responseSpec404;

    @BeforeClass
    public static void setUp() {
        requestSpec = RestHelper.getRequestSpec();
        responseSpec = RestHelper.getResponseSpec();
        responseSpec404 = RestHelper.getResponseSpec404();
    }

    @DataProvider
    public static Object[][] cityAndUsers() {
        return new Object[][]{
                {"London", "Mechelle"},
                {"Newcastle", null}
        };
    }

    @DataProvider
    public static Object[][] userIds() {
        return new Object[][]{
                {"1", "Maurise"},
                {"2", "Bendix"}
        };

    }

    @DataProvider
    public static Object[] invalidUserIds() {
        return new Object[]{"10000", "One"};
    }

    @DataProvider
    public static Object[] users() {
        return new Object[]{
                "Bendix"
        };

    }


    @Test
    @UseDataProvider("cityAndUsers")
    public void users_in_a_city_test(String city, String firstName) {
        given().
                spec(requestSpec).
                pathParam("city", city).
                when().
                get("/city/{city}/users").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("[0].first_name", equalTo(firstName));

    }

    @Test
    public void get_instructions_test() {
        given().
                spec(requestSpec).
                when().
                get("/instructions").
                then().
                spec(responseSpec);

    }

    @Test
    @UseDataProvider("userIds")
    public void get_user_ids_test(String userId, String fName) {
        given().
                spec(requestSpec).
                pathParam("id", userId).
                when().
                get("/user/{id}").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("first_name", equalTo(fName));
    }

    @Test
    @UseDataProvider("invalidUserIds")
    public void get_user_ids_not_found_test(String userId) {
        given().
                spec(requestSpec).
                pathParam("id", userId).
                when().
                get("/user/{id}").
                then().
                spec(responseSpec404);
    }

    @Test
    @UseDataProvider("users")
    public void get_users_test(String fName) {
        given().
                spec(requestSpec).
                when().
                get("/users").
                then().
                spec(responseSpec).
                and().
                assertThat().
                body("[1].first_name", equalTo(fName));
    }
}
