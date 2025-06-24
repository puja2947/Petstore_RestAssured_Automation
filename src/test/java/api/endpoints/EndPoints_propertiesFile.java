package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//This is UserEndPoints.java file created for perform CRUD operation through methods

public class EndPoints_propertiesFile {
	
	//additional method to load properties files, to get urls
	
static 	ResourceBundle getURL()
	{
		ResourceBundle routes= ResourceBundle.getBundle("Routes");
		return routes;
	}
	
	
	//getting the payload , sending the request, storing response in variable and return response 
	
	public static Response createUser(User payload)
	{
		//get the post url using object of getURL method
		
	String post_url=getURL().getString("post_url");  //getString is used becoz getURL will return object type of data
		
		Response res=
		given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)  //passing the payload
		.when()
	        .post(post_url);  //url is referred from routes class
		return res; 
	}

	
	public static Response readUser(String userName)
	{
		String get_url=getURL().getString("get_url"); 
		Response res=
		given()
		   .pathParam("username", userName)
	 
		.when()
	        .get(get_url);  //url is referred from root class
		return res; 
	}

	
	public static Response updateUser(User payload, String userName)
	{
		String put_url=getURL().getString("get_url"); 
		Response res=
		given()
		   .contentType(ContentType.JSON)
		   .accept(ContentType.JSON)
		   .body(payload)  //passing the payload
		   .pathParam("username", userName)
		.when()
	        .put(put_url) ; //url is referred from routes class
		return res; 
	}
	
	
	public static Response deleteUser(String userName)
	{
		String delete_url=getURL().getString("delete_url"); 
		
		Response res=
		given()
		   .pathParam("username", userName)
	 
		.when()
	        .delete(delete_url);  //url is referred from root class
		return res; 
	}
}
