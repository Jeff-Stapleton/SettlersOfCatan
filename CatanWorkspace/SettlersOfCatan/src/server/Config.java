package server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Logger;

/**
 * Contains all configuration methods for a server
 * @author Cory
 */
public class Config {
    private final static Logger LOG = Logger.getLogger(Config.class.getName());
    public static final String DATA_FOLDER = "server.data";
    public static final String TMP_FOLDER = "server.tmp";
    
    private static Properties configProperties = null;
    
    /**
     * Gets the configuration properties object for this server
     * @return the configuration properties object for this server
     * @throws IOException when the config file is missing
     */
    public static Properties getConfig() {
    	if (null == configProperties) {
        	try {
    			configProperties = getConfig("config.xml");
    		} catch (IOException e) {
    			LOG.warning("Default config file could not be loaded");
    			e.printStackTrace();
    		}
    	}
    	return configProperties;
    }
    
    /**
     * Gets the configuration properties object for this server
     * @return the configuration properties object for this server
     * @throws IOException when the config file is missing
     */
    public static Properties getConfig(String configFilename) throws IOException {
    	LOG.entering(Config.class.getName(), "getConfig");
    	
    	Properties config = new Properties();
    	File configFile = new File(configFilename);
    	try {
    		LOG.finer("Getting config properties from " + configFile.getAbsolutePath());
    		config.loadFromXML(new FileInputStream(configFile));
    		LOG.finest("Successfully got config properties");
    	} catch (IOException e) {
    		if (e instanceof InvalidPropertiesFormatException) {
    			LOG.severe("Config file is not formatted correctly.");
    		} else {
        		LOG.severe("Failed to get config properties");
    		}
    		throw e;
    	}
    	
    	LOG.exiting(Config.class.getName(), "getConfig", config);
		return config;
    }
    
    /**
     * Save a Properties object as a new config file
     * @param config the properties to save to the config file
     */
    public static void saveConfig(Properties config) {
    	LOG.entering(Config.class.getName(), "saveConfig");
    	try {
			config.storeToXML(new FileOutputStream("config.xml"), null);
			configProperties = config;
		} catch (IOException e) {
			LOG.severe("Failed to save the config file");
			e.printStackTrace();
		}
    	LOG.exiting(Config.class.getName(), "saveConfig");
    }
    
    /**
     * Save a Properties object as a config file at the specified location
     * @param config the Properties to save as a config file
     * @param destination the place to save the config file to
     */
    public static void saveConfig(Properties config, File destination) {
    	LOG.entering(Config.class.getName(), "saveConfig");
    	try {
    		config.storeToXML(new FileOutputStream(destination), null);
    	} catch (IOException e ) {
    		LOG.severe("Failed to save the config file");
    		e.printStackTrace();
    	}
    }
}