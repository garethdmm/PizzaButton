package android.pizzabutton;

public class User {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private Address address;
	private String email;
	
	public User(String firstName, String lastName, String  phoneNumber,
			Address address, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.email = email;
	}
	
	public String getFirstName() { return firstName; };
	public String getLastName() { return lastName; };
	public String getPhoneNumber() { return phoneNumber; };
	public Address getAddress() { return address; };
	public String getEmail() { return email; };
}
