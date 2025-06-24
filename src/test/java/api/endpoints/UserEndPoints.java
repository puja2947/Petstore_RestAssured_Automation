package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//This is UserEndPoints.java file created for perform CRUD operation through methods

public class UserEndPoints {
	
	
	//getting the payload , sending the request, storing response in variable and return response 
	
	public static Response createUser(User payload)
	{
		Response res=
		given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)  //passing the payload
		.when()
	        .post(Routes.post_url);  //url is referred from routes class
		return res; 
	}

	
	public static Response readUser(String userName)
	{
		Response res=
		given()
		   .pathParam("username", userName)
	 
		.when()
	        .get(Routes.get_url);  //url is referred from root class
		return res; 
	}

	
	public static Response updateUser(User payload, String userName)
	{
		Response res=
		given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)  //passing the payload
		   .pathParam("username", userName)
		.when()
	        .put(Routes.update_url) ; //url is referred from routes class
		return res; 
	}
	
	
	public static Response deleteUser(String userName)
	{
		Response res=
		given()
		   .pathParam("username", userName)
	 
		.when()
	        .delete(Routes.delete_url);  //url is referred from root class
		return res; 
	}
}
