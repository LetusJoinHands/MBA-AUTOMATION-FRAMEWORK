package com.mba.commons.identifiers;

import java.io.File;
import org.testng.ISuite;
import org.testng.ISuiteResult;

/**
@author Shenilton
@version 2.1
*/

public class InitMethodWindows {
	
	public static String FS = File.separator;
	public static String OSName = System.getProperty("os.name");
	public static String OSArchitecture = System.getProperty("os.arch");
	public static String OSVersion = System.getProperty("os.version");
	public static String OSBit = System.getProperty("sun.arch.data.model");
	public static String ProjectWorkingDirectory = System.getProperty("user.dir");
	public static String TestData = "";
	public static String LoginDetails = "";
	public static String PropertiesFiles = "";
	public static String Reports = "";
	public static String Images = "";
	public static String Videos = "";
	public static String OUTPUT_FOLDER = "";
	public static String FILE_NAME = "Extent Report.html";
	public static ISuite suite;
	public static ISuiteResult res;
	public static String extendXML = "";
	public static String KEYWORDS = "/src/main/resources/Keywords_Source/DataTableComcast.xlsx";
	public static String TEST_DATA = "/src/main/resources/data_bed/TEST_BED.xlsx";
	public static String INPUT_DATA = "/src/main/resources/data_bed/TEST_BED.xlsx";
	public static String OBJECT_GROUP = "/src/main/resources/apiAutomation/dataset/ObjectGroup.properties";
	public static String OBJECT_REPROSITORY = "/src/main/resources/apiAutomation/dataset/ObjectRepro.properties";
	
}