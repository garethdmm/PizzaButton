package android.pizzabutton;

import android.app.Activity;
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
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.*;

import android.pizzabutton.HttpHelper;

public class orderpizza extends Activity {
	public static final String PREFS_NAME = "PizzaPrefs";
	private User theUser;
	
	// TODO: Make this accessible to all classes in the app.
	private static final String TAG = "PizzaButton";

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

        loadPage(R.layout.main);

        bindButtonToListener(R.id.pizza, pizzaButtonListener);
    }

    private OnClickListener pizzaButtonListener = new OnClickListener() {
    	public void onClick(View v) {
    		loadPageInfoPersonal();
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
    		
    		/*String name = getTextById(R.id.user_name);
    		String email = getTextById(R.id.user_email);
    		String phone = getTextById(R.id.user_phone);
    		String streetname = getTextById(R.id.user_streetname);
    		String streetnum = getTextById(R.id.user_streetnumber);
    		String city = getTextById(R.id.user_city);
    		String province = getTextById(R.id.user_province);
    		String postalCode = getTextById(R.id.user_postalcode);
    		
    		String firstName = name.substring(0, name.indexOf(" "));
    		String lastName = name.substring(name.indexOf(" ") + 1);
    		
    		Address address = new Address(streetnum, streetname, city, postalCode, province);
    		theUser = new User(firstName, lastName, phone, address, email);*/
    		
    		try {
				loadPageInfoAddress();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    };
    
    private void loadPageInfoAddress() throws JSONException {
    	// do that http get to get the list of pizzas,
    	// display them in some sort of list, let the user pick one
    	// ask for the quantity
    	
    	loadPage(R.layout.info_address);
    	
    	bindButtonToListener(R.id.go_info_order, goInfoOrderListener);
    }
    
    private OnClickListener goInfoOrderListener = new OnClickListener() {
    	public void onClick(View v) {
    		loadPageInfoOrder();
    	}
    };
    
    private void loadPageInfoOrder() {
    	loadPage(R.layout.info_order);
    	
    	RadioGroup rg = (RadioGroup)findViewById(R.id.pizzaList);
    	
    	JSONArray json = getPizzaTypes();
    	
    	int i = 0;
    	while (i < json.length()) {
    		JSONObject jobj;
    		String id = "";
    		String pizzaType = "";
    		
			try {
				jobj = json.getJSONObject(i);
	    		id = jobj.getString("id");
	    		pizzaType = jobj.getString("pizzaType");
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
			RadioButton rb = new RadioButton(this);
			
    		rb.setText(pizzaType);
    		
    		rg.addView(rb);
    		i++;
    	}    	
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