package myUtils;

import java.io.File;
import java.util.NoSuchElementException;

import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;

public class ConfigurationManager {
	
	private static String cfgfile = "config.properties";
	
	public static Object getConfig(Class c, String varName) {
		Configurations configs = new Configurations();
		try{
		    Configuration config = configs.properties(new File(cfgfile));
		    return (Object)config.get(c, varName);
		}catch (Exception e){
		    throw new NoSuchElementException(".config loading error");
		}
	}
}
