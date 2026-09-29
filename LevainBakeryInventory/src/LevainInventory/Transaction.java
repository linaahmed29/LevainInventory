package LevainInventory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
	public enum Type { SALE, RESTOCK, WASTE }

	private final Item item;
	private final Type type;
	private final int quantity;
	private final LocalDateTime timestamp;

	public Transaction(Item item, Type type, int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than 0.");
		}
		this.item = item;
		this.type = type;
		this.quantity = quantity;
		this.timestamp = LocalDateTime.now();
	}

	public Item getItem() { return item; }
	public Type getType() { return type; }
	public int getQuantity() { return quantity; }
	public LocalDateTime getTimestamp() { return timestamp; }

	@Override
	public String toString() {
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		return timestamp.format(fmt) + " | " + type + " | " + item.getName() + " x" + quantity;
	}
}
