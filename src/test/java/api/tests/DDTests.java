package api.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import api.utiities.DataProvidors;
import io.restassured.response.Response;

public class DDTests {
	
	
	@Test(priority=1,dataProvider="Data", dataProviderClass= DataProvidors.class)
	public void testPostUsers(String userid, String username, String fname, String lname, String email, String password, String phone)
	{
		
		//read the data from data provider--> create payload-->sending request
		//create pojo object
		
		User userPayload= new User();
		userPayload.setId(Integer.parseInt(userid));
		userPayload.setUsername(username);
		userPayload.setFirstName(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(email);
		userPayload.setPassword(password);
		userPayload.setPhone(phone);
		
		Response response=	UserEndPoints.createUser(userPayload);
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
		
	}

	
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProvidors.class)
	public void testDeleteUsers(String username)
	{
		
		 System.out.println("Testing user: " + username);
		 
		/* Response res = UserEndPoints.readUser(username);
		 if (res.getStatusCode() == 200) {
		     Response deleteRes = UserEndPoints.deleteUser(username);
		     Assert.assertEquals(deleteRes.getStatusCode(), 200);
		 } else {
		     System.out.println("User " + username + " does not exist — skipping delete.");
		 }*/
		 
		Response response=	UserEndPoints.deleteUser(username);
		AssertJUnit.assertEquals(response.getStatusCode(),200); 
		
		
	}
	
}

