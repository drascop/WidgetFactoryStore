package edu.ilstu;

import java.util.Random;

public class Widget {

		private int price;
		private int id = 0;
		private static int idTrack = 1;
		
		public Widget() {
			Random numGen = new Random();
			this.price = numGen.nextInt(10, 21);
			this.id = idTrack;
			++idTrack;
		}
		
		public int getPrice() {
			return price;
		}
		
		public String toString() {
			if (id < 10) { // formatting for different id values
				return "                     Widget ID: " + id + "        Price: $" + price;
			}
			else if (id < 100) {
				return "                     Widget ID: " + id + "       Price: $" + price;
			}
			else if (id < 1000) {
				return "                     Widget ID: " + id + "      Price: $" + price;
			}
			else {
				return "                     Widget ID: " + id + "     Price: $" + price;
			}
 		}
}
