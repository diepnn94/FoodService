import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class Order {
	
	private ArrayList<Dish> orderMenu = new ArrayList<Dish>();
	private ArrayList<Dish> menu = new ArrayList<Dish>();
	private String filePath = "C:\\Users\\Amy\\Desktop\\menu.xml";
	
	
	public int size(){
		return orderMenu.size();
	}
	public void orderEntry(int index){
		if (index > menu.size() || index < 1){
			System.out.println("Please enter a number corresponding to the Menu list.");
		}
		else{
			orderMenu.add(menu.get(index-1));
			
		}
		
	}
	
	public void updateEntry(int index){
		if (index > orderMenu.size() || index < 1){
			System.out.println("Please enter a number corresponding to the ordered list.");
		}
		else{
			orderMenu.remove(index-1);
			
		}
		
	}
	
	public double priceTotal(){
		double total = 0;
		for (int i = 0; i < orderMenu.size(); i++){
			total+= orderMenu.get(i).getPrice();
		}
		return total;
	}
	
	public void loadMenu(){

		File xmlFile = new File(filePath);
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(xmlFile);
            doc.getDocumentElement().normalize();
            NodeList nodeList = doc.getElementsByTagName("DISH");
           
            for (int i = 0; i < nodeList.getLength(); i++) {
            	Element element = (Element) nodeList.item(i);
            	String tag = "NAME";
            	String tag1 = "PRICE";
            	NodeList NodeList = element.getElementsByTagName(tag).item(0).getChildNodes();
            	Node node = (Node) NodeList.item(0);
            	NodeList NodeList1 = element.getElementsByTagName(tag1).item(0).getChildNodes();
            	Node node1 = (Node) NodeList1.item(0);
            	Dish dish = new Dish(node.getNodeValue(), Double.valueOf(node1.getNodeValue().substring(1)));
            	menu.add(dish);

            }
           
        } catch (SAXException | ParserConfigurationException | IOException e1) {
            e1.printStackTrace();
        }
	}
	
	
	public void printMenu(){
		for (int i = 0; i < menu.size(); i++ ){
			
			System.out.println("\n\t" + (i+1) + ". " + menu.get(i).getName() + " : " + menu.get(i).getPrice());
		}
		
	}
	public String getOrderConfirmation(){
		String orderInfo = "\nYou have ordered (" + orderMenu.size() + " dishes):";
		for (int i = 0; i < orderMenu.size(); i++){
			orderInfo += "\r\n\t" + (i+1)  + ". "+  orderMenu.get(i).getName() + " : " + orderMenu.get(i).getPrice();
		}
		orderInfo += "\r\n\r\n*******************************************************************";
		orderInfo += "\r\n\r\n\t\t\t\t Your Total: $" + this.priceTotal();
		orderInfo += "\r\n\r\n********************************************************************";
		return orderInfo;
	}
	
	public void printOrder(){
		System.out.println("\n**********************************************************************");
		System.out.println("\nYou have ordered (" + orderMenu.size() + " dishes):");
		for (int i = 0; i < orderMenu.size(); i++){
			System.out.println("\t" + (i+1)  + ". "+  orderMenu.get(i).getName() + " : " + orderMenu.get(i).getPrice());
		}
		System.out.println("\t\t\t\t\tYour Total : $" + this.priceTotal());
		System.out.println("\n***********************************************************************");

	}
}
