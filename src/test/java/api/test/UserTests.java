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
import lombok.val;
import lombok.experimental.var;

public class UserTests {
	
	Faker faker;
	User payload;
	
	public Logger logger;
	
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
		
		logger = LogManager.getLogger(this.getClass());
	}
	
	@Test(priority = 1)
	public void testPostUser() {
		logger.info("**************** Creating User ****************");
		Response response = UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Created ****************");
	}
	
	@Test(priority = 2)
	public void testGetUserByName() throws JsonMappingException, JsonProcessingException {
		logger.info("**************** Get User ****************");
		Response response = UserEndPoints.readUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Info is displayed ****************");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() {
		logger.info("**************** Update User ****************");
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints.updateUser(this.payload.getUsername(), payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Updated ****************");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() {
		logger.info("**************** Delete User ****************");
		Response response = UserEndPoints.deleteUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Deleted ****************");
	}
}
