package com.mba.commons.dataMapping;

import com.mba.commons.dataDriven.Xls_Reader;
import com.mba.commons.identifiers.InitMethodiOS;

/**
@author Shenilton
@version 2.1
*/

public class DataImp {

	private static DataImp obj;

	private DataImp() {
	}

	public static DataImp getInstance() {
		if (obj == null)
			obj = new DataImp();
		return obj;
	}

	public String preReqsite(String key) {
		return Xls_Reader.getInstance().returninginStringValueForParticularKey(key,"PRE_REQ_FOR_TEST",InitMethodiOS.INPUT_DATA);
	}

	public String testData(String key) {
		return Xls_Reader.getInstance().returninginStringValueForParticularKey(key, "INPUT_DATA", InitMethodiOS.INPUT_DATA);
	}
	
	public static void main(String[] args) {
		String inputData = DataImp.getInstance().testData("INPUTFIELD_XPATH");	
		System.out.println(inputData);
	}

}
