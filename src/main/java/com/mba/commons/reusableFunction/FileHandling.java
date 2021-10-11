package com.mba.commons.reusableFunction;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileHandling {

	public void writtingToFile(String pathFile, String content) throws Exception {
		// Get the file reference
		Path path = Paths.get(pathFile);
		// Use try-with-resource to get auto-closeable writer instance
		try (BufferedWriter writer = Files.newBufferedWriter(path)) {
			writer.write(content);
		}
	}
	
	public String readingFile(String pathFile) throws Exception {
		String data = ""; 
	    data = new String(Files.readAllBytes(Paths.get(pathFile))); 
	    return data; 
	}

}
