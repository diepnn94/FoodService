import java.io.*;
import java.util.Scanner;
public class Interface {
	private String orderOption = "\nWelcome to Our Online Food Service! Please Choose One of the Mode to Start the Service:"+
			 "\n\t1. complete\n\t2. order\n\t3. update\n\t4. display\n\t5. menu";
	public String getOrderOption(){
		return orderOption;
	}
	
	public void orderFood(Order orderList){
		int orderNum = 1;
		orderList.printMenu();
		System.out.println("\nLet's order some foods! (type 'done' to complete the order)");
		while (true){
			System.out.print("\tDish "+ orderNum++ +":   ");
			Scanner entry = new Scanner (System.in);
			String item = entry.nextLine().trim();
			if (item.matches("^[0-9]+$")){
				orderList.orderEntry(Integer.valueOf(item));	
				orderList.printOrder();
			}
			else if (item.equals("done")){
				break;
			}
			else{
				System.out.println("Please enter the corresponding number location on the list:");
				orderNum--;
			}
			
		}
	}
	
	
	
	public void removeFood(Order orderList){
		System.out.println("\nWhich entry do you want to remove?");
		System.out.println("Please enter the corresponding number location on the list.");
		System.out.println("(type 'done' to complete the order update)");
		
		while (true){
			
			orderList.printOrder();
			System.out.print("You want to remove order number: ");
			Scanner entry = new Scanner (System.in);
			String item = entry.nextLine();
			if (item.matches("^[0-9]+$")){
				orderList.updateEntry(Integer.valueOf(item));	
			}
			else if (item.equals("done")){
				break;
			}
			else{
				System.out.println("Please enter the corresponding number location on the list.");
			}
			
		}
	}
	
	public Guest orderCheckOut(){
		System.out.print("\nPlease enter your name: ");
		Scanner name = new Scanner(System.in);
		String Name = name.nextLine();
		System.out.println();
		String Phone = null;
		while (true){
			System.out.print("Please enter your phone: ");
			Scanner phone = new Scanner (System.in);
			Phone = phone.nextLine();
			if (Phone.matches("^[0-9]{10}$") ){
				break;
			}
		}
		System.out.println();
		System.out.print("Please enter your email: ");
		Scanner email = new Scanner (System.in);
		String Email = email.nextLine();
		System.out.println();
		
	
		Guest guest = new Guest(Name, Phone, Email);
		return guest;
		
		
	}
	
	public void orderRecord(Order orderList){
		Guest guestInfo = orderCheckOut();
		System.out.println("\nHi, " + guestInfo.getGName()+ ", Your Order is Comming Right Up!");
		System.out.println("Please Wait For Our Phone Confirmation or Email Confirmation To Verify The Orders.");
		orderList.printOrder();
		if (orderList.size() != 0){
			Receipt receipt = new Receipt(orderList.getOrderConfirmation(), guestInfo);
			receipt.writeReceipt();
		}
		
	}
	
	public void OrderApplication(){
		String option = "none";
		Order orderList = new Order();
		orderList.loadMenu();
		while (!option.equals( "complete")){
			System.out.println(getOrderOption());
			System.out.print("\nPlease select a mode: ");
			Scanner input = new Scanner(System.in);
			option = input.nextLine();
			if (option.equals("order")){
				orderFood(orderList);
			}
			else if (option.equals("update")){
				removeFood(orderList);
			}
			else if (option.equals("display")){
				orderList.printOrder();
			}
			else if (option.contentEquals("menu")){
				orderList.printMenu();
			}
			else{
				if (!option.equals("complete")){
				System.out.println("\nPlease choose one of the specified mode.");
				}
			}
		}
		
		orderRecord(orderList);
		
	}
	
	public static void main(String args[]){
		Interface inf = new Interface();
		inf.OrderApplication();
	
	}
}
