package api.endpoints;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import api.payload.User;

public class UserEndPoints2 {
	
	static Properties property;
	
	static Properties getURL() throws IOException  {
		
		  FileReader propertyFile = new FileReader("./src//test//resources//routes.properties"); 
		  property = new Properties(); 
		  property.load(propertyFile);
		  return property;
		 
	}

	public static Response createUser(User payload) throws IOException {
		
		String postUrl = getURL().getProperty("post_url");
	 	Response response = given()
			.contentType("application/json")
			.accept("application/json")
			.body(payload)
		.when()
			.post(postUrl);
	 	
	 	return response;
	}
	
	public static Response readUser(String userName) throws IOException {
		
		String getUrl = getURL().getProperty("get_url");
		
		Response response =  given()
		.pathParam("userName", userName)
		.when()
			.get(getUrl);
		
		return response;
	}
	
	public static Response updateUser(String userName, User payload) throws IOException {
		
		String updateUrl = getURL().getProperty("update_url");
		
		Response response =  given()
				.pathParam("userName", userName)
				.contentType("application/json")
				.accept("application/json")
				.body(payload)
				.when()
					.put(updateUrl);
				
				return response;
	}
	
	public static Response deleteUser(String userName) throws IOException {
		
		String deleteUrl = getURL().getProperty("delete_url");
		
		Response response =  given()
				.pathParam("userName", userName)
				.when()
					.delete(deleteUrl);
				
				return response;
	}
}
