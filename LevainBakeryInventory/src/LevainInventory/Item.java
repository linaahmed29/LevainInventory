package LevainInventory;

public class Item {
	private String name;
	private int quantity;
	private double price;
	public Item(String name, int quantity, double price) {
		this.name=name;
		this.quantity=quantity;
		this.price=price;
	}
	public String getName(){
		return name;
	}
	public int getQuantity(){
		return quantity;
	}
	public double getprice(){
		return price;
	}
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	public void setPrice(int price){
		this.price=price;
	}
	public String toString(){
		return name + "| Quantity: "+quantity+" | Price: $"+price;
	}
}
