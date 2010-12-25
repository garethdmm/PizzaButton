package android.pizzabutton;


public class PizzaOrder {
	private int quantity;
	private String pizzaTypeId;
	
	public PizzaOrder(int quantity, String pizzaTypeId) {
		this.quantity = quantity;
		this.pizzaTypeId = pizzaTypeId;
	}
	
	public int getQuantity() { return quantity; }
	public String getPizzaTypeId() { return pizzaTypeId; }
}
