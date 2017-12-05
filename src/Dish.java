
public class Dish {
	String dishName = null;
	double dishPrice = 0;
	public Dish(String name, double price){
		dishName = name;
		dishPrice = price;
	}
	
	public String getName(){
		return this.dishName;
	}
	
	public double getPrice(){
		return this.dishPrice;
	}
}
