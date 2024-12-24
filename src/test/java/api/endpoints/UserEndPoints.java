package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import api.payload.User;

public class UserEndPoints {

	public static Response createUser(User payload) {
		
	 	Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		.when()
			.post(Routes.post_url);
	 	
	 	return response;
	}
	
	public static Response readUser(String userName) {
		
		Response response =  given()
		.pathParam("userName", userName)
		.when()
			.get(Routes.get_url);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload) {
		
		Response response =  given()
				.pathParam("userName", userName)
				.contentType("application/json")
				.accept("application/json")
				.body(payload)
				.when()
					.put(Routes.update_url);
				
				return response;
	}
	
	public static Response deleteUser(String userName) {
		
		Response response =  given()
				.pathParam("userName", userName)
				.when()
					.delete(Routes.delete_url);
				
				return response;
	}
}
