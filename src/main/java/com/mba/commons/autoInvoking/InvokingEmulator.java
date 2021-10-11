package com.mba.commons.autoInvoking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.log4j.Logger;

import com.mba.commons.controlers.CommonCallFunction;
import com.mba.commons.dataMapping.DataImp;

public class InvokingEmulator{

	/**
	@author Shenilton
	@version 2.1
	*/

	private static Logger logger = Logger.getLogger(InvokingEmulator.class);

	public static void startAvd() throws IOException, InterruptedException {
		try {
			ProcessBuilder pbEmulator = new ProcessBuilder(
					DataImp.getInstance().preReqsite("AVD_Emulator_PATH"), "-avd",
					DataImp.getInstance().preReqsite("AVD_NAME"));
			ProcessBuilder pbADB = new ProcessBuilder(DataImp.getInstance().preReqsite("AVD_PATH"),
					"devices");
			Process pcADB = pbADB.start();
			InputStream is = pcADB.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			String adb = new String();
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					line += br.readLine();
					adb = line;
				}
			}
			if (adb.toLowerCase().contains("emulator-5554")) {
				System.out.println("AVD Device Already exists");
				System.out.println("Device is connected");
				Thread.sleep(60000);
				System.out.println("Waiting for the system to load");
			} else {
				System.out.println("Device Not connected");
				System.out.println("AVD starting");
				@SuppressWarnings("unused")
				Process pcEmulator = pbEmulator.start();
				System.out.println("AVD started");
				Thread.sleep(60000);
				System.out.println("Waiting for the system Not to load");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Error in Starting AVD" + e.getMessage());
		}
	}

	public static void stopAvd() throws IOException, InterruptedException {
		try {
			ProcessBuilder pbEmulator = new ProcessBuilder(DataImp.getInstance().preReqsite("AVD_PATH"),
					"-s", "emulator-5554", "emu", "kill");
			ProcessBuilder pbADB = new ProcessBuilder(DataImp.getInstance().preReqsite("AVD_PATH"),
					"devices");
			Process pcADB = pbADB.start();
			InputStream is = pcADB.getInputStream();
			InputStreamReader isr = new InputStreamReader(is);
			BufferedReader br = new BufferedReader(isr);
			String line;
			String adb = new String();
			while ((line = br.readLine()) != null) {
				if (line.length() > 0) {
					line += br.readLine();
					adb = line;
				}
			}
			if (adb.toLowerCase().contains("emulator-5554")) {
				System.out.println("Device is connected");
				@SuppressWarnings("unused")
				Process pcEmulator = pbEmulator.start();
				System.out.println("Emulator closed successfully");
			} else {
				System.out.println("Device Not connected");
			}
		} catch (Exception e) {
			logger.error("Error in Stoping AVD" + e.getMessage());
		}
	}
}