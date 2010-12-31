package android.pizzabutton;

import org.json.JSONException;
import org.json.JSONObject;

public class User {
	private String name = "";
	private String phoneNumber = "";
	private String email = "";
	private String userId = "";
	private Address address = new Address();
	
	public User() {}
	
	public User(String name, String  phoneNumber, String email, Address address) {
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.address = address;
	}
	
	public String getJSON() {
		JSONObject jobj = new JSONObject();
		try {
			jobj.put("name", this.name);
			jobj.put("phoneNumber", this.phoneNumber);
			jobj.put("email", this.email);
			
			jobj.put("number", this.address.getNumber());
			jobj.put("street", this.address.getStreet());
			jobj.put("city", this.address.getCity());
			jobj.put("province", this.address.getProvince());
			jobj.put("postalCode", this.address.getPostalCode());
			
			jobj.put("quantity", "1");
			jobj.put("pizzaSize", "1");
			jobj.put("pizzaId", "1");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return jobj.toString();
	}
	
	public void setUserId(String userId) { this.userId = userId; }
	public void setName(String name) { this.name = name; }
	public void setEmail(String email) {this.email = email; }
	public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
	public void setAddress(Address address) { this.address = address; }
	
	public String getUserId() { return userId; }
	public String getName() { return name; };
	public String getPhoneNumber() { return phoneNumber; };
	public Address getAddress() { return address; };
	public String getEmail() { return email; };
}
