package android.pizzabutton;

public class Address {
	private String number;
	private String street;
	private String city;
	private String postalCode;
	private String province;
	
	public Address(String number, String street, String city,
		String postalCode, String province) {
		this.number = number;
		this.street = street;
		this.city = city;
		this.postalCode = postalCode;
		this.province = province;
	}
	
	public String getNumber() { return number; }
	public String getStreet() { return street; }
	public String getCity() { return city; }
	public String getPostalCode() { return postalCode; }
	public String getProvince() { return province; }
	
}
