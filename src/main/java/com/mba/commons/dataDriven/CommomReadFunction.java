package com.mba.commons.dataDriven;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import com.mba.commons.dataMapping.DataImp;
import com.mba.commons.identifiers.InitMethodWindows;

public class CommomReadFunction {

	private static CommomReadFunction obj;

	private CommomReadFunction() {
	}

	public static CommomReadFunction getInstance() {
		if (obj == null)
			obj = new CommomReadFunction();
		return obj;
	}
	public void logGeneration(String value) {
		BufferedWriter writer = null;
		StringBuffer buffer = null;
		try {
			buffer = new StringBuffer();
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
			String destFile = InitMethodWindows.ProjectWorkingDirectory + "\\FailedLog\\" + dateFormat.format(new Date()) + ".txt";
			File logFile = new File(destFile);
			System.out.println(logFile.getCanonicalPath());
			writer = new BufferedWriter(new FileWriter(logFile));
			writer.newLine();
			writer.write("*************************************RESULT**********************************");
			writer.newLine();
			writer.newLine();
			writer.write(value);
			writer.newLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (Exception e) {
			}
		}
	}
	
	@SuppressWarnings("finally")
	public String getPropertyVlaue(String redturnValue, String path) {
		Properties prop = new Properties();
		InputStream input = null;
		String finalPropertyVlaue = null;
		try {
			input = new FileInputStream(path);
			prop.load(input);
			finalPropertyVlaue = prop.getProperty(redturnValue);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return finalPropertyVlaue;
		}
	}
	
	public String propertyValueCall(String redturnValue, String filePath) {
		String value = getPropertyVlaue(redturnValue, returnPathValue(filePath));
		return value;
	}
	
	public String returnPathValue(String fileLocalPath) {
		String finalVlaue = InitMethodWindows.ProjectWorkingDirectory + fileLocalPath.toString().trim();
		System.out.println(finalVlaue);
		return finalVlaue;
	}
	
	public InputStream loadResource(final String resourcePath) throws IOException {
		final URL url = CommomReadFunction.class.getResource(resourcePath);
		if (url == null)
			throw new IOException(resourcePath + ": resource not found");
		return url.openStream();
	}

	public String returnPreviousFolderOfProjectDirectory(String fileName) {
		Path path = null;
		try {
			path = Paths.get(CommomReadFunction.class.getResource("..").toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String value1 = path.getParent().getParent().getParent().getParent().getParent().getParent().toString();
		return value1+fileName;

	}
	
	public void main(String[] args) {
		String value = returnPreviousFolderOfProjectDirectory("/Data_table_SG.XLSX");
		System.out.println(value);
	}

}