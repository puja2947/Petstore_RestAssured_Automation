package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.Pet;
import io.restassured.response.Response;

public class PetEndPoints {

	
	
	public static Response createPet(Pet data)
	{

		Response response=	given()
			   .contentType("application/json")
			   .body(data)
			   
			.when()
			   .post(Routes.create_pet_url);
		return response;
		
	}
	
	public static Response getPet(int id)
	{
		Response response=
				given()
				  .pathParam("id",id)
				.when()
				   .get(Routes.get_pet_url);
		return response;
	}
	

	public static Response updatePet(Pet data)
	{

		Response response=	given()
			   .contentType("application/json")
			   .body(data)
			   
			.when()
			   .post(Routes.update_pet_url);
		return response;
		
	}
	
	public static Response deletePet(int id)
	{
Response response=
		given()
		  .pathParam("id", id)
		.when()
		   .delete(Routes.delete_pet_url);
		return response;
	}
	
}
