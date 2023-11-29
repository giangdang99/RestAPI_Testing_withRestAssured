package test;

import io.restassured.response.Response;
import io.restassured.specification.RedirectSpecification;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class SimpleTest {
    public static void main(String[] args) {
//        Get API
//        https://jsonplaceholder.typicode.com/todos/1
        String baseUri = "https://jsonplaceholder.typicode.com/";

        //Request Scope
        RequestSpecification request = given();
        request.baseUri(baseUri);
        request.basePath("/todos");

        //Response scope
        final String FIRST_TODO = "/1";
        Response response = request.get(FIRST_TODO);
        response.prettyPrint();

//        Verify
        response.then().body("userId", equalTo(1));
        response.then().body("id", equalTo(1));
        response.then().body("title", equalTo("delectus aut autem"));
        response.then().body("completed", equalTo(false));
    }
}
