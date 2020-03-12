package com.parkit.parkingsystem.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.parkit.parkingsystem.App;
import com.parkit.parkingsystem.config.DatabaseCredentials;
import com.parkit.parkingsystem.constants.PropertyType;

public class PropertiesReaderUtil {
	
    private static final Logger logger = LogManager.getLogger("PropertiesReaderUtil");
    private Properties properties = new Properties();

    public PropertiesReaderUtil()
    {
    	loadProperties("resources\\parkingsystem.properties");
    }

    public PropertiesReaderUtil(String parkingSystemProperties)
    {
    	loadProperties(parkingSystemProperties);
    }

    private void loadProperties(String parkingSystemProperties)
    {
        logger.info("Loading properties");
    	FileInputStream fileInputStream = null;
    	try {
    		fileInputStream = new FileInputStream(new File(parkingSystemProperties));
			properties.load(fileInputStream);
		} catch (FileNotFoundException e) {
			logger.error("{} not found", parkingSystemProperties, e);
		} catch (IOException e) {
			logger.error("{} could not be read", parkingSystemProperties, e);
		}
    	
   		if (fileInputStream != null) try {
			fileInputStream.close();
		} catch (IOException e) {
			logger.error("File could not be closed", e);
		}
    }
    
    public DatabaseCredentials readTestCredentials()
    {
    	DatabaseCredentials credentials = new DatabaseCredentials();
    	credentials.driver = properties.getProperty(PropertyType.DB_DRIVER);
    	credentials.url = properties.getProperty(PropertyType.DB_TEST_URL);
    	credentials.username = properties.getProperty(PropertyType.DB_TEST_USERNAME);
    	credentials.password = properties.getProperty(PropertyType.DB_TEST_PASSWORD);
    	logger.info("Properties read : {}, {}, {}, {}", credentials.driver, credentials.url, credentials.username, credentials.password);
    	return credentials;
    }
    
    public DatabaseCredentials readProductionCredentials()
    {
    	DatabaseCredentials credentials = new DatabaseCredentials();
    	credentials.driver = properties.getProperty(PropertyType.DB_DRIVER);
    	credentials.url = properties.getProperty(PropertyType.DB_PROD_URL);
    	credentials.username = properties.getProperty(PropertyType.DB_PROD_USERNAME);
    	credentials.password = properties.getProperty(PropertyType.DB_PROD_PASSWORD);
    	logger.info("Properties read : {}, {}, {}, {}", credentials.driver, credentials.url, credentials.username, credentials.password);
    	return credentials;
    }
    
    public DatabaseCredentials readCredentials()
    {
    	StackTraceElement[] trace = Thread.currentThread().getStackTrace();
    	String caller = trace[trace.length-1].getClassName();
    	if (caller == "com.parkit.parkingsystem.App") return readProductionCredentials();
    	else return readTestCredentials();
    }
}
