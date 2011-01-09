package android.pizzabutton;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import android.util.Log;

/**
 * Wraps the platform calls. All these functions handle formatting the HTTP calls, and then
 * pass them off to HTTPHelper to make the actual call
 * 
 * @author gareth
 *
 */
public class APIWrapper {
	public static final String apiURL = "http://thepizzabutton.appspot.com/"; // hack for the android simulator
	public static final String orderPizzaEndpoint = apiURL + "order_pizza";
	public static final String pizzaListEndpoint = apiURL + "pizza_list";
	public static final String addUserEndpoint = apiURL + "add_user";
	
	public static String pizza_list() {
	 	String response = HttpHelper.doHttpGet(pizzaListEndpoint, null);
		return response;
	}
	
	/**
	 * Orders a pizza using the user's settings
	 * 
	 * @param userId	the user id of the user ordering pizza
	 * @return 			the response from the HTTP call
	 */
	public static String order_pizza(String userId) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("userId", userId));
		
		String response = HttpHelper.doHttpPost(orderPizzaEndpoint, nameValuePairs);
		
		return response;
	}
	
	/**
	 * 
	 * @param userJSON	a properly formatted JSON object containing all the fields
	 * necessary to create a new user object in our database. See the API spec for 
	 * more information on these fields
	 * 
	 * @return	the response from the HTTP call
	 */
	public static String add_user(String userJSON) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("user", userJSON));
		
		String response = HttpHelper.doHttpPost(addUserEndpoint, nameValuePairs);
	
		Log.v("TPB", "Posting " + addUserEndpoint);
		Log.v("TPB", "Response: " + response);
		return response; // response should be userId
	}
}
