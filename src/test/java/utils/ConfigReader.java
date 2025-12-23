package utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
	private static Properties prop;
	public static Properties getProperties() {
		try {		
			FileInputStream fis = new FileInputStream("src/main/java/demoresource/config.properties");
			prop = new Properties();
			prop.load(fis);
//			System.out.println(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
	public static String getProperty(String key) {
		return prop.getProperty(key);
	}

}
