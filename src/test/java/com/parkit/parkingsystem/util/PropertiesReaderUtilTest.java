package com.parkit.parkingsystem.util;

import java.util.Properties;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import com.parkit.parkingsystem.config.DatabaseCredentials;
import static com.parkit.parkingsystem.constants.PropertyType.*;

@ExtendWith(MockitoExtension.class)
public class PropertiesReaderUtilTest {
	
	// Mockito ne fonctionne pas sur la classe Properties, car elle utilise hashCode.
	// Pour mocker les inputs, il faut donc utiliser un fichier properties dédié aux tests unitaires de la classe.
	private static PropertiesReaderUtil propertiesReaderUtil = new PropertiesReaderUtil("src\\test\\resources\\parkingsystem.properties.test");
		
	@Test
	public void givenFile_TestProperties_areReadCorrectly()
	{
		// GIVEN
		// WHEN
		DatabaseCredentials databaseCredentials = propertiesReaderUtil.readTestCredentials();
		// THEN
		assertEquals("dbdriver", databaseCredentials.driver);
		assertEquals("dbtesturl", databaseCredentials.url);
		assertEquals("dbtestusername", databaseCredentials.username);
		assertEquals("dbtestpassword", databaseCredentials.password);		
	}

	@Test
	public void givenFile_ProductionProperties_areReadCorrectly()
	{
		// GIVEN
		// WHEN
		DatabaseCredentials databaseCredentials = propertiesReaderUtil.readProductionCredentials();
		// THEN
		assertEquals("dbdriver", databaseCredentials.driver);
		assertEquals("dbprodurl", databaseCredentials.url);
		assertEquals("dbprodusername", databaseCredentials.username);
		assertEquals("dbprodpassword", databaseCredentials.password);		
	}

}
