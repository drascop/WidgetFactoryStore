package edu.ilstu;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class WidgetFactoryStore {
	
	private static int day = 6;
	private static int month = 04;
	private static int year = 2023;
	
	private static int stock = 0; // Tracks number of widgets currently in stock
	
	private static Deque<List<Widget>> widgetStack = new ArrayDeque<List<Widget>>();
	private static ArrayDeque<Order> orderQ = new ArrayDeque<Order>();
	
	public static void main(String[] args) {
		int daysOfSim = 100;
		System.out.println("      Welcome to the Widget Factory Store Simulation System");
		
		for(int i = 0; i <=daysOfSim ; i++) {
			printDate();
			createBatch();
			createBatch();
			System.out.println("Widgets in stock: " + stock + "\n");
			Order newOrder = new Order();
			System.out.println("New Order:");
			System.out.println("           " + newOrder.getOrder() + "\n");
			
			if(newOrder.getWidgets() <= stock) { // if we have enough widgets then fulfill new order; no need to remove b/c it was never added to Q 
				List<Widget> currentOrder = new LinkedList<Widget>();
				System.out.println("Processing new order...");
				
				for(int x = 0; x < newOrder.getWidgets(); x++) {
					currentOrder.add(widgetStack.peek().get(0));
					widgetStack.peek().remove(0);
					--stock;
					
					if(widgetStack.peek().isEmpty()) { // Removes List<Widget> from the widget stack if the list is empty
						widgetStack.pop();
					}
				}
				newOrder.fulfillOrder(currentOrder);
			}	
			else if (orderQ.size() < 2) {
				System.out.println("Not enough widgets for the new order. Saving it for future deliveries.\n");
				orderQ.offer(newOrder);
				
				while(orderQ.peek().getWidgets() <= stock && ! orderQ.isEmpty()) { // Fulfills orders in queue while we have enough
					List<Widget> currentOrder = new LinkedList<Widget>();
					
					for(int x = 0; x < orderQ.peek().getWidgets(); x++) {	// Fills List of widgets that will be passed into fulfillOrder				
						currentOrder.add(widgetStack.peek().get(0));
						widgetStack.peek().remove(0);
						--stock;
						
						if(widgetStack.peek().isEmpty()) { // Removes List<Widget> from the widget stack if the list is empty
							widgetStack.pop();
						}
					}
					System.out.println("Processing an old order:");
					System.out.println("        " + orderQ.peek().getOrder() + "\n");
					orderQ.peek().fulfillOrder(currentOrder);
					orderQ.poll();
				}
					
			}
			else if(orderQ.size() == 2) { // If the queue is currently at max capacity
				System.out.println("Cannot process any new orders right now. Waiting for enough widgets.\n");
				
				while(orderQ.peek().getWidgets() <= stock && ! orderQ.isEmpty()) { // Fulfills orders in queue while we have enough
					List<Widget> currentOrder = new LinkedList<Widget>();
					
					for(int x = 0; x < orderQ.peek().getWidgets(); x++) {	// Fills List of widgets that will be passed into fulfillOrder				
						currentOrder.add(widgetStack.peek().get(0));
						widgetStack.peek().remove(0);
						--stock;
						
						if(widgetStack.peek().isEmpty()) { // Removes List<Widget> from the widget stack if the list is empty
							widgetStack.pop();
						}
					}
					orderQ.peek().fulfillOrder(currentOrder);
					orderQ.pop();
				}
			}		
			System.out.println("Widgets in stock: " + stock + "\n");
		}
	}		
	
	private static void createBatch() { // Fills list of widgets and pushes the list onto the widget stack
		LinkedList<Widget> batch = new LinkedList<Widget>();
		for(int i = 0; i < 5; i++) {
			batch.add(new Widget());
		}
		stock += 5;
		widgetStack.push(batch);
	}
	
	private static void printDate() { // Prints date and advances days/months/years
		System.out.println("Date: " + year + "-" + month + "-" + day);
		if(day == 31) {
			day = 1;
			if(month == 12) {
				month =1;
				year++;
			}
			else {
				month++;
			}
		}
		else {
			day++;
		}
	}

}
