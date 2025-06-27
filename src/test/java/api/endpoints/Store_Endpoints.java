package api.endpoints;

import static io.restassured.RestAssured.given;

import api.payloads.Store;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class Store_Endpoints {
	
	public static Response getStoreInventory()
	{
		
	Response response=	given()
		.when()
		   .get(Routes.get_store_inventory_url);
		
		return response;
	}

	
	public static Response Orderplace(Store storepayload)
	{
		Response response =
						given()
					  .contentType(ContentType.JSON)
					   .accept(ContentType.JSON)
					   .body(storepayload)
					   .log().body()
					.when()
					   .post(Routes.post_order_url);
		
		return response;
	}
	
	public static Response getOrderDetails(int id)
	{
		Response response=
		given()
		   .pathParam("orderid", id)
		.when()
		   .get(Routes.get_order_url);
		return response;
	}
	
	
	public static Response deleteOrder(int id)
	{
		Response response=
		given()
		   .pathParam("orderid", id)
	.when()
	     .delete(Routes.delete_order_url);
		
		return response;
	}
}
