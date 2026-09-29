package LevainInventory;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<Item> inventory= new ArrayList<>();
		ArrayList<Transaction> history = new ArrayList<>();
		Scanner scanner= new Scanner(System.in);
		boolean ProgramRuns= true;
		
		while(ProgramRuns){
			System.out.println("\n--- Bakery Inventory Menu ---");
			System.out.println("1. Add an item");
			System.out.println("2. View all items");
			System.out.println("3. Record a sale / restock / waste");
			System.out.println("4. View transaction history");
			System.out.println("5. Correct a quantity manually");
			System.out.println("6. Delete an item");
			System.out.println("7. Exit");
			int choice = readInt(scanner, "Choose an option: ");
			
				switch (choice) {
				case 1: addItem(inventory, scanner); break;
				case 2: viewItems(inventory); break;
				case 3: recordTransaction(inventory, history, scanner); break;
				case 4: viewHistory(history); break;
				case 5: correctQuantity(inventory, scanner); break;
				case 6: deleteItem(inventory, scanner); break;
				case 7:
					programRuns = false;
					System.out.println("See you next time! :]");
					break;
				default:
					System.out.println("Invalid option, try again!");
			}
		
		}scanner.close();
		
	}
	
	
	static int readInt(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Integer.parseInt(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a whole number.");
			}
		}
	}
 
	static double readDouble(Scanner scanner, String prompt) {
		while (true) {
			System.out.print(prompt);
			try {
				return Double.parseDouble(scanner.nextLine().trim());
			} catch (NumberFormatException e) {
				System.out.println("Please enter a number, like 3.50.");
			}
		}
	}
 
	static Item pickItem(ArrayList<Item> inventory, Scanner scanner) {
		if (inventory.isEmpty()) {
			System.out.println("Inventory is empty.");
			return null;
		}
		viewItems(inventory);
		int index = readInt(scanner, "Enter the number of the item: ");
		if (index < 0 || index >= inventory.size()) {
			System.out.println("Invalid number entered.");
			return null;
		}
		return inventory.get(index);
	}
 
	static void addItem(ArrayList<Item> inventory, Scanner scanner) {
		System.out.print("Enter item name: ");
		String name = scanner.nextLine().trim();
		if (name.isEmpty()) {
			System.out.println("Name can't be empty.");
			return;
		}
		int quantity = readInt(scanner, "Enter quantity: ");
		double price = readDouble(scanner, "Enter price: ");
		try {
			inventory.add(new Item(name, quantity, price));
			System.out.println("Item added!");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
 
	static void viewItems(ArrayList<Item> inventory) {
		if (inventory.isEmpty()) {
			System.out.println("No items to view at the moment. Inventory is empty.");
			return;
		}
		for (int i = 0; i < inventory.size(); i++) {
			System.out.println(i + ": " + inventory.get(i));
		}
	}
 
	static void recordTransaction(ArrayList<Item> inventory, ArrayList<Transaction> history, Scanner scanner) {
		Item item = pickItem(inventory, scanner);
		if (item == null) return;
 
		System.out.println("1. Sale   2. Restock   3. Waste");
		int typeChoice = readInt(scanner, "Type: ");
		Transaction.Type type;
		switch (typeChoice) {
			case 1: type = Transaction.Type.SALE; break;
			case 2: type = Transaction.Type.RESTOCK; break;
			case 3: type = Transaction.Type.WASTE; break;
			default:
				System.out.println("Invalid type.");
				return;
		}
		int quantity = readInt(scanner, "Quantity: ");
 
		try {
			Transaction t = new Transaction(item, type, quantity);
			item.apply(t);
			history.add(t);
			System.out.println("Recorded. " + item.getName() + " now has " + item.getQuantity() + " in stock.");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
 
	static void viewHistory(ArrayList<Transaction> history) {
		if (history.isEmpty()) {
			System.out.println("No transactions yet.");
			return;
		}
		for (Transaction t : history) {
			System.out.println(t);
		}
	}
 
	static void correctQuantity(ArrayList<Item> inventory, Scanner scanner) {
		Item item = pickItem(inventory, scanner);
		if (item == null) return;
		int newQuantity = readInt(scanner, "Enter new quantity: ");
		try {
			item.setQuantity(newQuantity);
			System.out.println("Quantity updated!");
		} catch (IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
 
	static void deleteItem(ArrayList<Item> inventory, Scanner scanner) {
		Item item = pickItem(inventory, scanner);
		if (item == null) return;
		inventory.remove(item);
		System.out.println("Item deleted!");
	}
}
 
