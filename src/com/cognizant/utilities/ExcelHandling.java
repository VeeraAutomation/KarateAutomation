package com.cognizant.utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;

public class ExcelHandling {

	public static JSONObject jsonObject = new JSONObject();
	public static Properties props = new Properties();
	public static Set<String> scenarios = new HashSet<String>();

	public static ArrayList<String> getExecutableScenarios() {

		String filePath = null;
		XSSFWorkbook workbook = null;
		FileInputStream inputStream = null;
		ArrayList<String> executableTags;
		try {
			// Read Excel file path
			props.load(new FileInputStream("./configs.properties"));
			filePath = props.getProperty("TestRunnerFilePath");
			inputStream = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(inputStream);
			XSSFSheet scenarioSheet = workbook.getSheetAt(0);
			XSSFSheet tagsSheet = workbook.getSheetAt(1);
			executableTags = ExcelHandling.getExecutableTags(scenarioSheet);
			//executableTags += ExcelHandling.getExecutableTags(tagsSheet);
			//executableTags = executableTags.substring(1);
			System.out.println(executableTags);
			return executableTags;
		} catch (Exception e) {
			System.out.println("Error occured while accessing RunManager execl data : " + e.getMessage());
			return null;
		} finally {
			try {
				workbook.close();
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<String> getExecutableTags(XSSFSheet sheet) {
		String execute = "";
		ArrayList<String> executableTags = new ArrayList<String>();
		for (int rowNum = 0; rowNum <= sheet.getLastRowNum(); rowNum++) {
			XSSFRow row = sheet.getRow(rowNum);
			if (row != null) {
				execute = row.getCell(1).getStringCellValue();
				if (execute.equalsIgnoreCase("YES")) {
					executableTags.add(row.getCell(0).getStringCellValue());
				}
			}
		}
		return executableTags;
	}

	public static void storeDataDetails() {
		String filePath = null;
		try {
			props.load(new FileInputStream("./configs.properties"));
			filePath = props.getProperty("DataFilePath");
			convertExcelToJson(filePath);
			for(String scenarioName : scenarios) {
				 createJson(scenarioName);
			 }
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error occured while accessing Data execl data : " + e.getMessage());
		}
	}

	private static void convertExcelToJson(String filePath) throws Exception {
		// Initialize an InputStream for the Excel file
		FileInputStream inputStream = new FileInputStream(filePath);

		// Load the workbook using XSSFWorkbook for .xlsx files
		Workbook workbook = new XSSFWorkbook(inputStream);

		// Get the first sheet (assuming you want the first sheet)
		Sheet sheet = workbook.getSheet("TestData");

		// Process the Excel data
		convertSheetToJson(sheet);
//		sheet = workbook.getSheet("SYMD");
//		convertSheetToJson(sheet);
		// Close the resources
		workbook.close();
		inputStream.close();
		System.out.println("JSON data: " + jsonObject);
	}

	private static void convertSheetToJson(Sheet sheet) throws Exception {
		// Get the header row
		Row headerRow = sheet.getRow(0);

		// Create a List to store header names
		List<String> headers = new ArrayList<>();
		// Extract header names
		for (Cell cell : headerRow) {
			headers.add(cell.getStringCellValue());
		}
		System.out.println(headers);
		// Iterate through rows (skip header row)
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			// Create a JSONObject to store row data
			JSONObject rowObject = new JSONObject();

			// Iterate through cells in the current row
			for (int j = 1; j < headers.size(); j++) {
				Cell cell = row.getCell(j);
				String value = getCellValue(cell);
				rowObject.put(headers.get(j), value);
			}
			// Add the row data to the JSON Object
			jsonObject.put(getCellValue(row.getCell(0))+getCellValue(row.getCell(1)), rowObject);
			scenarios.add(getCellValue(row.getCell(0)));
			
		}
		writeStringToFile(jsonObject.toString(), sheet.getSheetName());

	}

	 /* Write string data to a file.*/
    private static void writeStringToFile(String data, String sheetName)
    {
        try
        {
            // Get the output file absolute path.
            String filePath = "./src/com/cognizant/resources/"+sheetName+".json";

            // Create File, FileWriter and BufferedWriter object.
            File file = new File(filePath);

            FileWriter fw = new FileWriter(file);

            BufferedWriter buffWriter = new BufferedWriter(fw);

            // Write string data to the output file, flush and close the buffered writer object.
            buffWriter.write(data);

            buffWriter.flush();

            buffWriter.close();

            System.out.println(filePath + " has been created.");

        }catch(IOException ex)
        {
            System.err.println(ex.getMessage());
        }
    }
	private static String getCellValue(Cell cell) {
		DataFormatter formatter = new DataFormatter();
		formatter.setUseCachedValuesForFormulaCells(true);
		return formatter.formatCellValue(cell);
	}
	
	public static void getExecutableScenariosWithIteratios(){
		ArrayList<String> tags = ExcelHandling.getExecutableScenarios();
		String executableTags = "";
		for(String scenarioName:tags) {
			System.out.println("scenarioName : "+scenarioName);
			int iteration = getCount(scenarioName);
			for(int itr=0;itr<iteration;itr++) {
				
			}
			
		}
	}

	 public static int getCount(String scenarioName) {
		 int count = 0;
	        try {
	           
	            for (Object key : jsonObject.keySet()) {
	                if (key.toString().startsWith("VitalityPlan")) {
	                    count++;
	                }
	            }
	            System.out.println("Iteration of "+scenarioName+" is " + count);
	            return count;
	        } catch (Exception e) {
	            e.printStackTrace();
	            return 0;
	        }
	    }
	 public static void main(String[] args) {
		 storeDataDetails();
	}
	 
	 public static void createJson(String scenarioName) {
	        JSONArray jsonArray = new JSONArray();
	        for (String value : jsonObject.keySet()) {
	            if (value.contains(scenarioName)) {
	            	JSONObject rowObject = new JSONObject();
	            	rowObject.put("sName", value);
	            	jsonArray.put(rowObject);
	            } 
	        }
	        writeStringToFile(jsonArray.toString(),scenarioName);
	    }
}
