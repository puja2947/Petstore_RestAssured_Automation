package api.endpoints;

/*
 Swagger URL: https://petstore.swagger.io/
 
 Create User(POST) : https://petstore.swagger.io/v2/user
 Get User(GET) : https://petstore.swagger.io/v2/user/{username}
 Update User(PUT) : https://petstore.swagger.io/v2/user/{username}
 Delete User (DELETE) : https://petstore.swagger.io/v2/user/{username}
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
	
	   //here create store model url
	
	//PET MODEL
	
        // here create pet module url
}
