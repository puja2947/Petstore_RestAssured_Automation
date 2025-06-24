package api.tests;

import org.testng.annotations.Test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.AssertJUnit;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hamcrest.Matchers.*;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;


public class UserTest {
	
	Faker faker;  //class variable
	User userPayload;
	public Logger logger;
	
	@BeforeClass
	public void setUpData ()  //generate the data using faker library and pass to pojo class
	{
		faker= new Faker();  //created object
		
		userPayload= new User();
		
		
		
		//generate date randomly
		
		userPayload.setId(faker.idNumber().hashCode());   
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstName(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
		userPayload.setUserStatus(1);
		
		//log
		logger=LogManager.getLogger(this.getClass());
		 
	}
	
	
	@Test(priority=1)
	public void testPostUser()
	{
		
		logger.info("******************Creating User******************");
	Response response=	UserEndPoints.createUser(userPayload);
	//response.then().log().all();
	response.then().log().body();
	AssertJUnit.assertEquals(response.getStatusCode(),200);
	
	logger.info("****************** User created ******************");
	}
	
	
@Test(priority=2)
	void testGetUserByName()
	{
	
	logger.info("******************Reading User info ******************");
	
	System.out.println("Trying to get user with username: " + userPayload.getUsername());
		Response response=UserEndPoints.readUser(userPayload.getUsername());
		response.then().log().all();
		AssertJUnit.assertEquals(response.getStatusCode(),200);
		
		logger.info("****************** User info is displayed ******************");
	}

@Test(priority=3)
void testUpdateUserByName()
{
	logger.info("******************Updating User******************");
	
	//update data using payload object
	
	userPayload.setFirstName(faker.name().firstName());
	userPayload.setLastName(faker.name().lastName());
	userPayload.setEmail(faker.internet().safeEmailAddress());
	
	Response response=UserEndPoints.updateUser(userPayload, this.userPayload.getUsername());
	response.then().log().all();
	//response.then().log().body().statusCode(200);  // this restassured validation
	AssertJUnit.assertEquals(response.getStatusCode(),200); //this is testng validated
	
	//checking data after update
	
	Response response_after_update = UserEndPoints.readUser(this.userPayload.getUsername());
	System.out.println(userPayload.getFirstName());
	System.out.println(userPayload.getLastName());
	System.out.println(userPayload.getEmail());
	AssertJUnit.assertEquals(response_after_update.getStatusCode(),200);
	
	logger.info("****************** User is updated ******************");
}

@Test(priority=4)
void testDeleteUserByName()
{
	logger.info("******************Deleting User******************");
	
Response response=	UserEndPoints.deleteUser(this.userPayload.getUsername());  //this keyword is used to refer to the
//current user name
AssertJUnit.assertEquals(response.getStatusCode(),200); 

logger.info("****************** User is deleted ******************");
}

}
