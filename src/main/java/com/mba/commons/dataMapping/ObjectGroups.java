package com.mba.commons.dataMapping;

import java.util.Arrays;
import java.util.List;

import com.mba.apiAutomation.webServiceMethods.MutableJson;
import com.mba.commons.controlers.CommonCallFunction;
import com.mba.commons.dataDriven.Xls_Reader;
import com.mba.commons.reusableFunction.FileHandling;
import com.mba.models.APImapping;
import com.mba.models.JsonUpdateIdentification;
import com.mba.models.ObjectModel;

/**
@author Shenilton
@version 29-07-2020
*/

public class ObjectGroups {

	private static ObjectGroups obj;

	private ObjectGroups() {
	}

	public static ObjectGroups getInstance() {
		if (obj == null)
			obj = new ObjectGroups();
		return obj;
	}

	public void actionOnElementsWeb(String OBJECT_GROUP, String OBJECT_KEY, String ACTION_KEY, String ASSERTION_TYPE, String ASERTION_VALUE, String groupPath, String objectReprositaryPath, String inputToUI, String CONTROL_FLAG, String inputDataToUI) throws Exception {
		String inputData = "";
		int index = 0;		
		if (OBJECT_GROUP.equalsIgnoreCase("multi") && CONTROL_FLAG.equalsIgnoreCase("Y")) {	
			String groupValue = CommonCallFunction.callPropertyValueInputDataWithPathObject(OBJECT_KEY, groupPath);
			List<ObjectModel> value = KeywordsEngine.returnValue(groupValue);	
			for (ObjectModel actionMap : value) {				
				String element = actionMap.getValue();
				String action = actionMap.getKey();
				String objectCall = CommonCallFunction.callPropertyValueInputDataWithPathObject(element, objectReprositaryPath);
				inputData = CommonCallFunction.callPropertyValueInputDataWithPathObject(element, inputToUI);		
				ActionMapping.getInstance().actionMappingWeb(action, element, objectCall, inputData,ASSERTION_TYPE, ASERTION_VALUE, index);
			}
		} else if (OBJECT_GROUP.equalsIgnoreCase("single") && CONTROL_FLAG.equalsIgnoreCase("Y")) {
			inputData = CommonCallFunction.callPropertyValueInputDataWithPathObject(OBJECT_KEY, inputToUI);
			System.out.println(inputData);		
			String objectCall = CommonCallFunction.callPropertyValueInputDataWithPathObject(OBJECT_KEY,objectReprositaryPath);		
			ActionMapping.getInstance().actionMappingWeb(ACTION_KEY, OBJECT_KEY, objectCall, inputData,ASSERTION_TYPE, ASERTION_VALUE, index);
		}
	}
	
	public void actionOnElementsMobileWeb(String OBJECT_GROUP, String OBJECT_KEY, String ACTION_KEY, String ASSERTION_TYPE, String ASERTION_VALUE, String groupPath, String objectReprositaryPath, String inputToUI, String CONTROL_FLAG, String inputDataToUI) throws Exception {
		String inputData = "";
		int index = 0;		
		if (OBJECT_GROUP.equalsIgnoreCase("multi") && CONTROL_FLAG.equalsIgnoreCase("Y")) {					
			List<ObjectModel> value = KeywordsEngine.returnValue(CommonCallFunction.callPropertyValueInputDataWithPathObject(OBJECT_KEY, groupPath));	
			for (ObjectModel actionMap : value) {				
				String element = actionMap.getValue();
				String action = actionMap.getKey();
				String objectCall = CommonCallFunction.callPropertyValueInputDataWithPathObject(element, objectReprositaryPath);
				inputData = Xls_Reader.getInstance().returninginStringValueForParticularKey(element, "TEST_DATA", inputToUI);			
				ActionMapping.getInstance().actionMappingMobile(action, element, objectCall, inputData,ASSERTION_TYPE, ASERTION_VALUE, index);
			}
		} else if (OBJECT_GROUP.equalsIgnoreCase("single") && CONTROL_FLAG.equalsIgnoreCase("Y")) {
			inputData = Xls_Reader.getInstance().returninginStringValueForParticularKey(OBJECT_KEY, "TEST_DATA", inputToUI);	
			System.out.println(inputData);		
			String objectCall = CommonCallFunction.callPropertyValueInputDataWithPathObject(OBJECT_KEY,objectReprositaryPath);		
			ActionMapping.getInstance().actionMappingMobile(ACTION_KEY, OBJECT_KEY, objectCall, inputData,ASSERTION_TYPE, ASERTION_VALUE, index);
		}
	}
	
	public void actionOnElementsAPI(String OBJECT_GROUP, String OBJECT_KEY, String ACTION_KEY, String ASSERTION_TYPE, String ASERTION_VALUE, String groupPath, String objectReprositaryPath, String inputToUI, String CONTROL_FLAG,String inputDataToAPI) throws Exception {
		String inputData = "";
		int index = 0;		
		 if (OBJECT_GROUP.equalsIgnoreCase("single") && CONTROL_FLAG.equalsIgnoreCase("Y")) {
			inputData = CommonCallFunction.callPropertyValueInputDataWithPathObject(OBJECT_KEY, inputToUI);
			System.out.println(inputData);		
			String objectCall = CommonCallFunction.callPropertyValueInputDataWithPathObject(OBJECT_KEY,objectReprositaryPath);	
			System.out.println(objectCall);
			APImapping mapping = new KeywordsEngine().apiMapping(objectCall);
			if (mapping.getMethod().contains("GET") || mapping.getMethod().contains("DELETE")) {
				System.out.println(mapping.getResource());
				ActionMapping.getInstance().actionMappingAPI(ACTION_KEY, OBJECT_KEY, mapping.getResource(), inputData,ASSERTION_TYPE, ASERTION_VALUE, index);
			}
			if (mapping.getMethod().contains("POST") || mapping.getMethod().contains("PUT")) {
				itratingTheStringForJsonUpdate(mapping.getDataUpdat(), mapping.getRequestLocation());
				String fileHandling = new FileHandling().readingFile(System.getProperty("user.dir")+ "/src/main/resources/apiAutomation/templates/" + mapping.getRequestLocation());
				ActionMapping.getInstance().actionMappingAPI(ACTION_KEY, OBJECT_KEY, mapping.getResource(),fileHandling, ASSERTION_TYPE, ASERTION_VALUE, index);
			}
		}
	}
	
	public void itratingTheStringForJsonUpdate(List<JsonUpdateIdentification> value, String location) {
		value.stream().parallel().forEach(itrating ->  {		
			String filePath = System.getProperty("user.dir")+"/src/main/resources/apiAutomation/templates/"+location;						
			try {							
				if (itrating.getDataType().contains("string")) {						
					filePath = System.getProperty("user.dir")+ "/src/main/resources/apiAutomation/templates/"+ location;	
					//new MutableJson(null).jsonUpdate(new FileHandling(), filePath, itrating.getKey(),itrating.getValue());
				} else if (itrating.getDataType().contains("double")) {			
					filePath = System.getProperty("user.dir")+ "/src/main/resources/apiAutomation/templates/"+ location;	
					//new MutableJson(null).jsonUpdate(new FileHandling(), filePath, itrating.getKey(),itrating.getValue());
				} else if (itrating.getDataType().contains("int")) {				
					filePath = System.getProperty("user.dir")+ "/src/main/resources/apiAutomation/templates/"+ location;
					//new MutableJson(null).jsonUpdate(new FileHandling(), filePath, itrating.getKey(),itrating.getValue());
				} else if (itrating.getDataType().contains("float")) {
					filePath = System.getProperty("user.dir")+ "/src/main/resources/apiAutomation/templates/"+ location;
					//new MutableJson(null).jsonUpdate(new FileHandling(), filePath, itrating.getKey(),itrating.getValue());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
}