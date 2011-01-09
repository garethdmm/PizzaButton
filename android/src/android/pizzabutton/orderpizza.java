package android.pizzabutton;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.ArrayList;
import java.util.TimerTask;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

import android.pizzabutton.HttpHelper;


public class orderpizza extends Activity {
	private boolean hasUser = false;
	private User theUser = new User();
	
	// TODO: Make this accessible to all classes in the app.
	private static final String TAG = "TPB";

	public void ToastMsg(String message) {
		    Toast msg = Toast.makeText(
		        getApplicationContext(),
		        message,
		        Toast.LENGTH_LONG);

		    msg.show();
	}

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 
        SharedPreferences settings = getPreferences(MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = settings.edit();
        
        // clear the preferences for testing purposes
        editor.putBoolean("hasUser", false);
        editor.putString("userId", "");
        editor.commit();
        
        loadPage(R.layout.main);

        bindButtonToListener(R.id.pizza, pizzaButtonListener);
    }

    private OnClickListener pizzaButtonListener = new OnClickListener() {
    	public void onClick(View v) {
            SharedPreferences settings = getPreferences(MODE_WORLD_WRITEABLE);
            hasUser = settings.getBoolean("hasUser", false);
            
    		if (hasUser) {
    			Log.v("TPB", "Have the user, going straight to orderPizza()");
    			String userId = settings.getString("userId", "");
    			theUser.setUserId(userId);
    			
    			orderPizza();
    		} else { // have to create a new user
    			Log.v("TPB", "No user, going to create User wizard");
    			loadPageInfoPersonal();
    		}
    	}
    };
    
    private void loadPageInfoPersonal() {  
        loadPage(R.layout.info_personal);
        
        bindButtonToListener(R.id.go_info_address, goInfoAddressListener);
        
        ToastMsg("Before we start, we need a little bit of info about you");
        
        EditText name = (EditText)findViewById(R.id.user_name);
        EditText email = (EditText)findViewById(R.id.user_email);
        EditText phone = (EditText)findViewById(R.id.user_phone);
        
        name.setOnClickListener(clearTextListener);
        email.setOnClickListener(clearTextListener);
        phone.setOnClickListener(clearTextListener);    
    }
    
    private OnClickListener goInfoAddressListener = new OnClickListener() {
    	public void onClick(View v) {
    		// test that the user has filled out all the forms
    		// test that the data is good
    		// create a user object
    		// advance
    		
    		String name = getTextById(R.id.user_name);
    		String email = getTextById(R.id.user_email);
    		String phoneNumber = getTextById(R.id.user_phone);
    		
    		theUser.setName(name);
    		theUser.setEmail(email);
    		theUser.setPhoneNumber(phoneNumber);
    	
    		loadPageInfoAddress();	
    	}
    };
    
    private void loadPageInfoAddress() {
    	// do that http get to get the list of pizzas,
    	// display them in some sort of list, let the user pick one
    	// ask for the quantity
    	
    	loadPage(R.layout.info_address);
    	
    	bindButtonToListener(R.id.go_info_order, goInfoOrderListener);
    }
    
    private OnClickListener goInfoOrderListener = new OnClickListener() {
    	public void onClick(View v) {
    		
    		String number = getTextById(R.id.user_streetnumber);
    		String street = getTextById(R.id.user_streetname);
    		String city = getTextById(R.id.user_city);
    		String postalCode = getTextById(R.id.user_postalcode);
    		String province = getTextById(R.id.user_province);
    		
    		Address address = new Address(number, street, city, 
    				postalCode, province);
    		 
    		theUser.setAddress(address);
    		
    		loadPageInfoOrder();
    	}
    };
    
    private void loadPageInfoOrder() {
    	loadPage(R.layout.info_order);
    
    	bindButtonToListener(R.id.orderSubmit, orderSubmitListener);
    }

    // we need to separate this into PizzaButton and "Jesus Christ" button
    // functionalities
    private OnClickListener orderSubmitListener = new OnClickListener() {
    	public void onClick(View v) {
    		createUser();
    		orderPizza();
    		
    		loadPage(R.layout.main);
            bindButtonToListener(R.id.pizza, pizzaButtonListener);
    	}
    };

    private void orderPizza() {
    	Log.v("TPB", "Ordering pizza!");
    	String response = APIWrapper.order_pizza(theUser.getUserId());
    	Log.v("TPB", response);
    	// TODO: actually check if it worked
    	ToastMsg("Fuck yeah, Pizza is on its way!");
    }
    
    private void createUser() {
    	Log.v("TPB", theUser.getName());
    	Log.v("TPB", theUser.getJSON());
    	String retUserId = APIWrapper.add_user(theUser.getJSON());
    	this.theUser.setUserId(retUserId);
    	Log.v("TPB", "New user id: " + this.theUser.getUserId());
    	
        SharedPreferences settings = getPreferences(MODE_WORLD_WRITEABLE);
    	SharedPreferences.Editor editor = settings.edit();
    	
    	editor.putString("userId", theUser.getUserId());
    	editor.putBoolean("hasUser", true);
    	editor.commit();
    	
    	this.hasUser = true;
    	
    	Log.v("TPB", "UserId from preferences: " + settings.getString("userId", ""));
    }
    
    private JSONArray getPizzaTypes() {
    	String result = HttpHelper.doHttpGet("http://thepizzabutton.appspot.com/pizza", null);
    	JSONArray json;
    	
        try {
        	// NOTE: JSONArray vs JSONObject for [] or {}, respectively.
        	json = new JSONArray(result);

	        Log.v(TAG, json.toString());
	        return json;
        } catch (JSONException e) {
        	Log.v(TAG, "Error parsing JSON: "+e);
        	return null;
        }
    }  
    
    ///////
    
    // I'm trying to get this to clear text fields when they're selected but its not working
    private OnClickListener clearTextListener = new OnClickListener() {
    	public void onClick(View v) {
    		EditText et = (EditText)v;
    		et.setText("");
    	}
    };
    
    private void loadPage(int id) {
    	setContentView(id);
    }
    
    private void setTextById(int id, String text) {
        TextView textObj = (TextView)findViewById(id);
        textObj.setText(text);
    }
      
    private String getTextById(int id) {
    	TextView textObj = (TextView)findViewById(id);
        return textObj.getText().toString();
    }
    
    private void bindButtonToListener(int id, OnClickListener listener) {
        Button button = (Button)findViewById(id);
        button.setOnClickListener(listener);
    }
}