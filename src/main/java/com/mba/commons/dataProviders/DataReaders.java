package com.mba.commons.dataProviders;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.testng.annotations.DataProvider;

import com.mba.commons.controlers.CommonCallFunction;
import com.mba.commons.controlers.DriverCreation;
import com.mba.commons.identifiers.InitMethodiOS;

/**
@author Shenilton
@version 2.1
*/

public class DataReaders extends DriverCreation {

	@DataProvider
	public Object[][] dataRequiredForTest_Account_Details_100_PREMIERDEPOSIT() throws IOException {
		return CommonCallFunction.dataRequiredToTest("", "");
	}

	public Object[][] returnDatasFromSheet(String key, String workBookName) throws Exception {
		OPCPackage pkg = OPCPackage.open(new FileInputStream(workBookName));
		XSSFReader r = new XSSFReader(pkg);
		Iterator<InputStream> sheets = r.getSheetsData();
		if (sheets instanceof XSSFReader.SheetIterator) {
			XSSFReader.SheetIterator sheetiterator = (XSSFReader.SheetIterator) sheets;
			while (sheetiterator.hasNext()) {
				InputStream dummy = sheetiterator.next();
				System.out.println(sheetiterator.getSheetName());
				dummy.close();
			}
		}
		pkg.close();
		Map<String, Object[][]> mappingValues = new HashMap<String, Object[][]>();
		return mappingValues.get(key);
	}
}
