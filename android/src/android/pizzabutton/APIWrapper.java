package android.pizzabutton;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class APIWrapper {
	public static final String apiURL = "http://127.0.0.1/";
	public static final String orderPizzaEndpoint = apiURL + "order";
	public static final String pizzaListEndpoint = apiURL + "pizzaList";
	
	public static String pizza_list() {
	 	String response = HttpHelper.doHttpGet(pizzaListEndpoint, null);
		return response;
	}
	
	public static String order_pizza(String userId) {
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
		nameValuePairs.add(new BasicNameValuePair("userId", "0"));
		
		String response = HttpHelper.doHttpPost(orderPizzaEndpoint, nameValuePairs);
		
		return response;
	}
}
