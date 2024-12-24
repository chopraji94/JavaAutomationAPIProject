package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payload.User;
import io.restassured.response.Response;

public class DeserializeJsonToPojo {
	
	Faker faker;
	User payload;
	
	@BeforeClass
	public void setupData() {
		faker = new Faker();
		payload = new User();
		
		payload.setId(faker.number().hashCode());
		payload.setUsername(faker.name().username());
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		payload.setPassword(faker.internet().password(5,10));
		payload.setPhone(faker.phoneNumber().cellPhone());
		
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		Response response = UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	@Test(priority = 2)
	public void testGetUserByName() throws JsonMappingException, JsonProcessingException {
		Response response = UserEndPoints.readUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		//Deserialize data Method 1
		User data;
		
		ObjectMapper mapper = new ObjectMapper();
		data = mapper.readValue(response.getBody().asString(), User.class);
		System.out.println(data.getEmail());
		 
		//Deserialize data Method 2
			/*
			 * User posts = response.getBody().as(User.class);
			 * System.out.println(posts.getFirstName());
			 */
	}
}
