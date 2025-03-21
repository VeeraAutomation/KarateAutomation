package com.cognizant.utilities;

import java.io.File;

public class FIleCompare {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String folderPath = "./src/com/cognizant/reports/actualPDFs";
		File folder = new File(folderPath);
		File[] files = folder.listFiles();
		for (File file : files) {
			System.out.println(file.getAbsolutePath());
			File[] pdfFiles = new File(file.getAbsolutePath()).listFiles();
			for (File pdfFile : pdfFiles) {
				compare(pdfFile.getName());
			}
			System.out.println("..............................");
		}
	}
	
	public static void compare(String fileName) {
		System.out.println(fileName);
	}

}
