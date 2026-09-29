package LevainInventory;

public class Item {
	private String name;
	private int quantity;
	private double price;
	public Item(String name, int quantity, double price) {
		if (quantity < 0 || price < 0) {
			throw new IllegalArgumentException("Quantity and price can't be negative.");
		}
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
		if (quantity < 0) {
			throw new IllegalArgumentException("Quantity can't be negative.");
		}
		this.quantity=quantity;
	}
	public void setPrice(double price){
			if (price < 0) {
			throw new IllegalArgumentException("Price can't be negative.");
		}
		this.price=price;
	}
	public void apply(Transaction t) {
		switch (t.getType()) {
			case RESTOCK:
				quantity += t.getQuantity();
				break;
			case SALE:
			case WASTE:
				if (t.getQuantity() > quantity) {
					throw new IllegalArgumentException(
						"Not enough stock. Only " + quantity + " of " + name + " left.");
				}
				quantity -= t.getQuantity();
				break;
		}
	}
	@Override
	public String toString(){
		return  name + " | Quantity: " + quantity + " | Price: $" + String.format("%.2f", price);
	}
}
