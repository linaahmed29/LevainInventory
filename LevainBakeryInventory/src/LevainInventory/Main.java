package LevainInventory;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		ArrayList<Item> inventory= new ArrayList<>();
		Scanner scanner= new Scanner(System.in);
		boolean ProgramRuns= true;
		
		while(ProgramRuns){
			System.out.println("\n--- Bakery Inventory Menu ---");
			System.out.println("1. Add an Item");
			System.out.println("\t2. View all items" );
			System.out.println("\t3. Update Quantity");
			System.out.println("\t4. Delete Item");
			System.out.println("\t5. Exit Program");
			System.out.println("Choose an option from the previous: ");
			int choice= scanner.nextInt();
			scanner.nextLine();
			
			switch(choice) {
			case 1:
				addItem(inventory, scanner);
				break;
			case 2:
				viewItem(inventory);
				break;
			case 3:
				updateQuantity(inventory, scanner);
				break;
			case 4:
				deleteItem(inventory, scanner);
				break;
			case 5:
				ProgramRuns=false;
				System.out.println("See you next time! :]");
				break;
			default:
				System.out.println("Invalid option, try again!");
			}
		
		}scanner.close();
		
	
		
		
		for (Item item: inventory) {
			System.out.println(item);
		}
	}
	
	
		static void addItem(ArrayList<Item> inventory, Scanner scanner) {
			System.out.println("Enter Item name: ");
			String name = scanner.nextLine();
			System.out.println("Enter quantity: ");
			int quantity = scanner.nextInt();
			System.out.println("Enter Price: ");
			double price = scanner.nextDouble();
			scanner.nextLine();
			inventory.add(new Item(name, quantity, price));
			System.out.println("Item added! yay!");
		}
		
		
		static void viewItem(ArrayList<Item> inventory) {
			if(inventory.isEmpty()) {
				System.out.println("No items to view at the moment, Inventory is empty.");
				return;
			}
			for(int i=0; i< inventory.size(); i++) {
				System.out.println(i+": "+ inventory.get(i));
			}
		}
		
		
		static void updateQuantity(ArrayList<Item> inventory, Scanner scanner) {
			viewItem(inventory);
			if(inventory.isEmpty()){
				return;
			}
			System.out.println("Enter the index of the item you would like to update: ");
			int index= scanner.nextInt();
			
			if (index<0 || index>=inventory.size()) {
				System.out.println("Invalid index number entered. please try again :((");
				return;
			}
			System.out.println("Enter new quantity: ");
			
		}
		static void deleteItem(ArrayList<Item> inventory, Scanner scanner) {
			viewItem(inventory);
			if(inventory.isEmpty()) return;
			
			System.out.println("Please enter the number of the item you would like to delete: ");
			int index = scanner.nextInt();
			
			if(index<0 || index>=inventory.size()) {
				System.out.println("Invalid index entered!");
				return;
			}
			inventory.remove(index);
			System.out.println("Item deleted!");
		}
		
	}


