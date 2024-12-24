package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DDTests {
	
	@Test(priority = 1,dataProvider = "Data", dataProviderClass = DataProviders.class)
	public void testPostUser(String userId,String userName,String firstName,String lastName,String email,String password,String phone) {
		User payload = new User();
		
		payload.setId(Integer.parseInt(userId));
		payload.setUsername(userName);
		payload.setFirstName(firstName);
		payload.setLastName(lastName);
		payload.setEmail(email);
		payload.setPassword(password);
		payload.setPhone(phone);
		
		Response response = UserEndPoints.createUser(payload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	
	
	  @Test(priority = 2,dataProvider = "UserNames", dataProviderClass = DataProviders.class) public void testGetUser(String userName) { Response
	  response = UserEndPoints.deleteUser(userName); response.then().log().all();
	  Assert.assertEquals(response.getStatusCode(), 200); }
	 
	
}
