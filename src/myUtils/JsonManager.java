package myUtils;

import java.io.*;
import javax.json.*;

public class JsonManager {
	
	File file;
	
	public JsonManager(String file) {
		this.file = new File(file);
	}
	
	public JsonArray loadArray(String name){
		JsonArray arr = null;
		try {
			InputStream in = new FileInputStream(file);
			JsonReader reader = Json.createReader(in);
			JsonObject obj = reader.readObject();
			arr = obj.getJsonArray(name);			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("JSON exception: unable to find array "+name);
		}
		return arr;
	}

}
