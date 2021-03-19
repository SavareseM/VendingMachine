package main;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.common.base.Joiner;
public class Main extends ReadDataFromJSONFile {

	public static void main(String[] args) throws IOException, ParseException {
		Scanner myObj = new Scanner(System.in);
		System.out.println("Welcome to the Vending Machine Would you like to upload a JSON file to update the selections? Y/N");
		
		String yorn = myObj.nextLine();
		//if else statement to see if user wants to update vending machine item selection
		if (yorn.equals("Y")){ 
			
			System.out.println("Then please enter the name below ex: example.json");
			Map<String, JSONObject> vendingItems = getHashmapFromJSONFile();
			System.out.println("Please make a selection using the key. ");

			Scanner order = new Scanner(System.in);
			String input = order.nextLine();
			System.out.println(input);
			JSONObject newkey = vendingItems.get(input);
			String newname = (String) newkey.get("name"); 
			String newprice = (String) newkey.get("price");
			System.out.println(newname +" Will be " + newprice + " How much will you be paying for that? 0.00");
			Scanner order1 = new Scanner(System.in);
			String moneygiven = order1.nextLine();
			newprice=newprice.replace("$", "");
			double newprice1 = Double.parseDouble(newprice);
			double moneygiven1 = Double.parseDouble(moneygiven);
			Double change = moneygiven1-newprice1;
			System.out.println("Change given is $" + change);
			
			
			try {
				File audit = new File("audit.txt");
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("audit.txt", true)));
				writer.println("Product Sold "+ newname+ " Money received " + change + " Money Earned " + newprice1 );
				writer.close();
				
			 
			}catch(IOException e) {
				System.out.println("An error occured.");
				e.printStackTrace();
			}
		} else {
			System.out.println("Then please enter input.json");
			Map<String, JSONObject> vendingItems = getHashmapFromJSONFile();
			System.out.println("Please make a selection using the key. ");

			Scanner order = new Scanner(System.in);
			String input = order.nextLine();
			
			JSONObject newkey = vendingItems.get(input);
			String newname = (String) newkey.get("name"); 
			String newprice = (String) newkey.get("price");
			System.out.println(newname +" Will be " + newprice + " How much will you be paying for that? 0.00");
			Scanner order1 = new Scanner(System.in);
			String moneygiven = order1.nextLine();
			newprice=newprice.replace("$", "");
			double newprice1 = Double.parseDouble(newprice);
			double moneygiven1 = Double.parseDouble(moneygiven);
			Double change = moneygiven1-newprice1;
			System.out.println("Change given is $" + change);
			
			
			try {
				File audit = new File("audit.txt");
				PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("audit.txt", true)));
				writer.println("Product Sold "+ newname+ " Money received " + change + " Money Earned " + newprice1 );
				writer.close();
				
			 
			}catch(IOException e) {
				System.out.println("An error occured.");
				e.printStackTrace();
			}
			
			
			
		return;
		}
	}
	
}
