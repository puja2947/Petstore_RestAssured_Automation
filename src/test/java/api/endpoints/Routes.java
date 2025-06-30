package api.endpoints;

/*
 Swagger URL: https://petstore.swagger.io/
 
 Create User(POST) : https://petstore.swagger.io/v2/user
 Get User(GET) : https://petstore.swagger.io/v2/user/{username}
 Update User(PUT) : https://petstore.swagger.io/v2/user/{username}
 Delete User (DELETE) : https://petstore.swagger.io/v2/user/{username}
 
 
 Get Inventory(get) : https://petstore.swagger.io/v2/store/inventory
 create order(post) : https://petstore.swagger.io/v2/store/order
 get order details (get) : https://petstore.swagger.io/v2/store/order/{orderid}
 Delete order(delete)  : https://petstore.swagger.io/v2/store/order/{orderid}
 */



public class Routes {
	
	public static String base_url= "https://petstore.swagger.io/v2"; //public static so that it can be accessed
	//anywhere just by class name, no need to create object
	
	//USER MODEL
	
	public static String post_url= base_url+"/user";
	public static String get_url= base_url+"/user/{username}";
	public static String update_url= base_url+"/user/{username}";
	public static String delete_url= base_url+"/user/{username}";
	
	//STORE MODEL
	
	   public static String get_store_inventory_url= base_url+"/store/inventory";
	   public static String post_order_url= base_url+"/store/order";
	   public static String get_order_url=base_url+"/store/order/{orderid}";
	   public static String delete_order_url= base_url+"/store/order/{orderid}";
	   
	
	//PET MODEL
	
	   public static String create_pet_url= base_url+"/pet";
		public static String get_pet_url= base_url+"/pet/{id}";
		public static String update_pet_url= base_url+"/pet";
		public static String delete_pet_url= base_url+"/pet/{id}";
}
