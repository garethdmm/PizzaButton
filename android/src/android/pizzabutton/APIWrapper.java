package android.pizzabutton;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

public class APIWrapper {
	public static final String apiURL = "http://10.0.2.2:8080/"; // hack for the android simulator
	public static final String orderPizzaEndpoint = apiURL + "order_pizza";
	public static final String pizzaListEndpoint = apiURL + "pizza_list";
	public static final String addUserEndpoint = apiURL + "add_user";
	
	public static String pizza_list() {
	 	String response = HttpHelper.doHttpGet(pizzaListEndpoint, null);
		return response;
	}
	
	public static String order_pizza(String userId) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("userId", userId));
		
		String response = HttpHelper.doHttpPost(orderPizzaEndpoint, nameValuePairs);
		
		return response;
	}
	
	public static String add_user(String userJSON) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("user", userJSON));
		
		String response = HttpHelper.doHttpPost(addUserEndpoint, nameValuePairs);
	
		Log.v("TPB", "Posting " + addUserEndpoint);
		Log.v("TPB", "Response: " + response);
		return response; // response should be userId
	}
}
