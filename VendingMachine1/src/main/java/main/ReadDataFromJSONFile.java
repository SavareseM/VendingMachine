package main;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.common.base.Joiner;

public class ReadDataFromJSONFile  {

		
	public static void main(String[] args) throws IOException, ParseException {

		
		Map<String, JSONObject> vendingItems = getHashmapFromJSONFile();
		System.out.println(vendingItems);
		
	}
	public static String Interface() {
			Scanner scan = new Scanner(System.in);
			String newfile = scan.nextLine();
			return newfile;
		
		
	}
		public static Object Parse() throws IOException, ParseException {
			JSONParser jsonparser =new JSONParser();
			FileReader reader=new FileReader(".\\jsonfiles\\"+ Interface()); //newfile scanned in to update JSON file to be parsed
			Object obj=jsonparser.parse(reader); //obj is java object
			return obj;
		}
		@SuppressWarnings("unchecked")
		public static Map<String, JSONObject> getHashmapFromJSONFile() throws IOException, ParseException{
			
			JSONObject empjsonobj=(JSONObject) Parse(); 
			//gets first object "config" and its values
			JSONObject config = (JSONObject) empjsonobj.get("config");
			long rows=(long) config.get("rows");
			String columns=(String) config.get("columns");
			//gets next array of objects "items"
			JSONArray array = (JSONArray)empjsonobj.get("items");
			//declaring variables
			JSONObject items = null;
			String name = "";
			long amount = 0;
			String price = "";
			//loop to parse array "items"
			for(int i=0;i<array.size();i++)
			{
				items=(JSONObject) array.get(i);
					
				name=(String) items.get("name");
				amount=(long) items.get("amount");
				price=(String) items.get("price");
			}
			//initialize variables and counters for next loop	
			int row= (int)rows;
			int column= Integer.parseInt(columns);
			int second = 0;
			String key= "";
			char first= 'A';
			int REDIX=10;
			int counter=0;
			int newcount =0;
			Map<String, JSONObject> vendingItems = new HashMap<>();
			//loop to take 
				for(int i=0;i<row;i++)
				{
						
					for(int j=0;j<column;j++) {
							second++;
							//builds key by combining two characters and iterating them 
							char b=Character.forDigit(second, REDIX);
							StringBuilder builder = new StringBuilder().append(first).append(b);
							key = builder.toString();
							
							 items=(JSONObject) array.get(newcount);
							vendingItems.put(key, items);
							JSONObject newItems = items;
							newItems =(JSONObject) items.put("key",key);
							
							counter++;
							newcount++;
							//prints out vending machine 
							JSONObject newkey = vendingItems.get(key);
							String newname = (String) newkey.get("name");
							String newprice = (String) newkey.get("price");
							System.out.print(key+" ");
							System.out.print(newname+" ");
							System.out.println(newprice);
							//loop to check if loop is out of bounds
							if(counter==array.size()) {
								return vendingItems;
							}else if(j== column-1) {
								second=0;
								first++;
							}else {
								continue;
							}
				
						}
					
					}
				
					return vendingItems;
			}
		

	

	
}