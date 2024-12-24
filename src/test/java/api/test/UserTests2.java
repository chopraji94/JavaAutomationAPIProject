package api.test;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints2;
import api.payload.User;
import io.restassured.response.Response;

public class UserTests2 {
	
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
	public void testPostUser() throws IOException {
		logger.info("**************** Creating User ****************");
		Response response = UserEndPoints2.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Created ****************");
	}
	
	@Test(priority = 2)
	public void testGetUserByName() throws IOException {
		logger.info("**************** Get User ****************");
		Response response = UserEndPoints2.readUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Info is displayed ****************");
	}
	
	@Test(priority = 3)
	public void testUpdateUserByName() throws IOException {
		logger.info("**************** Update User ****************");
		payload.setFirstName(faker.name().firstName());
		payload.setLastName(faker.name().lastName());
		payload.setEmail(faker.internet().safeEmailAddress());
		
		Response response = UserEndPoints2.updateUser(this.payload.getUsername(), payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Updated ****************");
	}
	
	@Test(priority = 4)
	public void testDeleteUser() throws IOException {
		logger.info("**************** Delete User ****************");
		Response response = UserEndPoints2.deleteUser(this.payload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		logger.info("**************** User Deleted ****************");
	}
}
