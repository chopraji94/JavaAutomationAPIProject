package api.endpoints;

public class Routes {
	public static String base_url = "https://petstore3.swagger.io/api/v3";
	
	//UseModule
	public static String post_url = base_url+"/user";
	public static String get_url = base_url+"/user/{userName}";
	public static String update_url = base_url+"/user/{userName}";
	public static String delete_url = base_url+"/user/{userName}";
}
