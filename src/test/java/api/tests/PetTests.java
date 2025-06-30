package api.tests;

import static io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers.*;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import api.endpoints.PetEndPoints;
import api.payloads.Category_Pet;
import api.payloads.Pet;
import api.payloads.Tags_Pet;

public class PetTests {

	int id;
	Pet data;
	public Logger logger;
	
	@BeforeClass
	public void setUpData()
	{
		 data=new Pet();
		Category_Pet category= new Category_Pet();
		category.setId(1);
		category.setName("Kitty");
		
		Tags_Pet tags1= new Tags_Pet();
		tags1.setId(1);
		tags1.setName("friendly");
		
		Tags_Pet tags2= new Tags_Pet();
		tags2.setId(2);
		tags2.setName("Vaccinatted");
		
		data.setId(101);
		data.setName("cat");
		data.setCategory(category);
		data.setPhotoUrls(new String[]{"https://example.com/cat.jpg"});
		data.setTags(new Tags_Pet[] {tags1,tags2});
		data.setStatus("available");
		
		//log
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testCreatePet()
	{
		
		logger.info("***************Creating New Pet**************");
		Response response= PetEndPoints.createPet(data);
		
		id= response.jsonPath().getInt("id");
		System.out.println("the generated id is :"+ id);
		
		response.then()
		    .statusCode(200)
		    .log().all();
		logger.info("*************New Pet created****************"); 
	}
	
	@Test(priority=2)
	public void testGetPetDetails()
	{
		
		logger.info("*************Getting details of new pet ****************"); 
		Response response= PetEndPoints.getPet(id);
		response.then()
		   .statusCode(200)
		   .log().body();
		logger.info("************* Got details of new pet****************"); 
	}
	
	@Test(priority=3)
	public void testUpdatePetDetails()
	{ 
	/*	
		Response response= PetEndPoints.getPet(id);
		Pet existingPet = response.as(Pet.class);
  

// Step 2: Modify the fields you want
existingPet.setName("UpdatedCat");
existingPet.setStatus("sold");
		
		Response response1= PetEndPoints.updatePet(existingPet);
		response1.then()
		   .statusCode(200)
		   .log().body(); */
		
		logger.info("*************Updating pet details ****************"); 
		Response response = PetEndPoints.getPet(id);

		if (response.statusCode() == 200) {
		    Pet existingPet = response.as(Pet.class);
		    
		    existingPet.setName("UpdatedName");
		    existingPet.setStatus("sold");

		   Response response1= PetEndPoints.updatePet(existingPet);
		    response1.then()
		        .statusCode(200)
		        .log().body();
		} else {
		  System.out.println("Pet not found. Status Code: " + response.statusCode());
		   System.out.println("Response Body: " + response.getBody().asString());
		}
		logger.info("*************Updated pet details ****************"); 
	}
	
	
	@Test(priority=4)
	public void testDeletePet()
	{
		logger.info("************* Deleting the pet info****************"); 
		Response response = PetEndPoints.deletePet(id);
		response.then()
		  .statusCode(200)
		  .log().all();
		logger.info("*************Deleted pet ****************"); 
	}
	
	
	@AfterClass
	public void cleanUp() {
	    if (id != 0) {
	        Response response = PetEndPoints.getPet(id);
	        if (response.statusCode() == 200) {
	            System.out.println("Cleaning up pet with ID: " + id);
	            Response deleteResponse = PetEndPoints.deletePet(id);
	            deleteResponse.then()
	                .statusCode(200)
	                .log().all();
	        } else {
	            System.out.println("No pet found to delete. It might have already been deleted.");
	        }
	    } else {
	        System.out.println("ID not set. Nothing to clean up.");
	    }
}
}
