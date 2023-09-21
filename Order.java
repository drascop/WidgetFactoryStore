package edu.ilstu;

import java.util.List;
import java.util.Random;

public class Order {

	private int widgets;
	private int orderNumber;
	private List<Widget> theOrder;
	private static int orderNumTrack = 1;
	
	public Order() {
		Random numGen = new Random();
		this.widgets = numGen.nextInt(1, 31);
		this.orderNumber = orderNumTrack;
		orderNumTrack++;
	}
	
	public void fulfillOrder(List<Widget> theOrder) { 
		System.out.println("Delivering the following widgets:");
		this.theOrder = theOrder;
		for(int i = 0; i < theOrder.size(); i++) {
			System.out.println(theOrder.get(i).toString());
		}
		System.out.println(toString() + "\n");
	}
	
	public int getWidgets() {
		return widgets;
	}
	
	public String getOrder() {
		return "Order Number: " + orderNumber + "     Widgets Ordered: " + widgets;
	}
	
	public String toString() {
		double orderTotal = 0;
		int beforeTaxTotal = 0;
		for(int i = 0; i < theOrder.size(); i++) {
			beforeTaxTotal += theOrder.get(i).getPrice();
		}
		orderTotal = beforeTaxTotal * 1.08;
		if (orderNumber < 10) { // Formatting for different order number values
			return "                     Order Number: " + orderNumber + "     Order Total: " + String.format("$%.2f", orderTotal);
		}
		else if (orderNumber < 100) {
			return "                     Order Number: " + orderNumber + "    Order Total: " + String.format("$%.2f", orderTotal);
		}
		else {
			return "                     Order Number: " + orderNumber + "   Order Total: " + String.format("$%.2f", orderTotal);
		}
		
	}
	
}
