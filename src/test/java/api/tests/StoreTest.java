package api.tests;

 import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.Store_Endpoints;
import api.payloads.Store;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class StoreTest {
	
	Faker faker;   // class variable to randomly generate the request body
	Store storePayload; // created object of store POJO class for payload
	int orderid;
	public Logger logger;
	
	@BeforeClass
	public void setUpdata()
	{

		faker= new Faker();   //Initialized faker and Store variable
		storePayload = new Store();
		
		
		String shipDate = ZonedDateTime.now().format(DateTimeFormatter.ISO_INSTANT);
		
		storePayload.setId(faker.number().numberBetween(1, 5));
		storePayload.setPetId(faker.number().numberBetween(1, 5));
		storePayload.setQuantity(faker.number().numberBetween(1, 5));
		storePayload.setShipDate(shipDate);
		storePayload.setStatus("placed");
		storePayload.setComplete(true);
		
		logger=LogManager.getLogger(this.getClass());
	
	}

	@Test(priority=1)
	void testGetStoreInventory()
	{
	
		logger.info("************Getting inventory details***********");
		Response response= Store_Endpoints.getStoreInventory();
		response.then()
		  .statusCode(200)
		  .header("Content-Type","application/json")
		  .log().all();
		logger.info("**************above is the inventory details****************");
	}
	
	@Test(priority=2)
	void testPlaceOrder()
	{
		
		logger.info("************Placing new order***********");
	Response response= Store_Endpoints.Orderplace(storePayload);
	
	orderid= response.path("id");
	System.out.println("The order id is  :"+orderid);
	 response.then()
	    .statusCode(200)
	    .log().body();
	 logger.info("************New Order placed***********");
	}
	
	
	@Test(priority=3)
	void testGetOrderInfo()
	{
		logger.info("************Getting order details***********");
		Response response= Store_Endpoints.getOrderDetails(orderid);
		response.then()
		  .statusCode(200)
		  .log().all();
		logger.info("************Above is the order details***********");
	}
	
	
	@Test(priority=4)
	void testDeleteOrder()
	{
		logger.info("************Deleting the order***********");
		Response response= Store_Endpoints.deleteOrder(orderid);
		
	response.then()
	   .statusCode(200)
	  .log().all();
	logger.info("************Deleted the order***********");	
		
	}
}
