//package com.cognizant.utilities;
//
//import java.awt.Color;
//import java.io.IOException;
//import java.util.Collection;
//
//import org.apache.pdfbox.multipdf.PDFMergerUtility;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.common.PDRectangle;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.apache.pdfbox.pdmodel.font.Standard14Fonts;
//
//import de.redsix.pdfcompare.CompareResultImpl;
//import de.redsix.pdfcompare.PageArea;
//import de.redsix.pdfcompare.PdfComparator;
//import de.redsix.pdfcompare.env.SimpleEnvironment;
//
//public class PDFTest {
//
//	public static void main(String[] args) throws Exception {
//		//String source = "C:\\Users\\821988\\OneDrive - Cognizant\\Documents\\Sumitomo\\Tools\\Source\\PAT0.pdf";
//		//String target = "C:\\Users\\821988\\OneDrive - Cognizant\\Documents\\Sumitomo\\Tools\\Target\\PAT0.pdf";
//		String source = "C:\\Users\\821988\\OneDrive - Cognizant\\Documents\\Sumitomo\\Tools\\Source\\PAT0.pdf";
//		String target = "C:\\Users\\821988\\OneDrive - Cognizant\\Documents\\Sumitomo\\Tools\\Target\\PAT0.pdf";
//		String res = "C:\\Users\\821988\\OneDrive - Cognizant\\Documents\\result";
//
//		// CompareResultImpl result = new PdfComparator(source, target).withIgnore(new
//		// PageArea(1, 1316, 105, 1720, 250)).compare();
//		CompareResultImpl result = new PdfComparator(source, target)
//				.withEnvironment(new SimpleEnvironment()
//						.setActualColor(Color.red)
//						.setExpectedColor(Color.white))
//				.withIgnore(new PageArea(1, 1316, 105, 1720, 250))
//				.withIgnore(new PageArea(2, 1316, 105, 1720, 250))
//				.compare();
//		//System.out.println(result.getDifferencesJson().split("exclusions: ")[1]);
//		//System.out.println(result.getNumberOfPages());
//		//System.out.println(result.getDifferences().size());
//		//System.out.println(result.getDifferences().toArray());
//		result.writeTo(res);
//		Collection<Integer> differencePages = result.getPagesWithDifferences();
//        //System.out.println(differencePages);
//		createSummary(differencePages, result.getNumberOfPages());
//		//merge
//	}
//	
//	public static void createSummary(Collection<Integer> differencePages, int totalPages) throws IOException {
//	    PDDocument document = new PDDocument();
//	    PDPage page = new PDPage();
//	    document.addPage(page);
//
//	    PDRectangle trimBox = new PDRectangle(612, 792); 
//	    page.setTrimBox(trimBox);
//	    
//	    int pageHeight = (int)page.getTrimBox().getHeight();
//	    PDPageContentStream contentStream = new PDPageContentStream(document,page);
//	    contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN),14);
//        contentStream.beginText();
//        contentStream.newLineAtOffset(50, 750);
//        contentStream.showText("Comparison Result");
//        contentStream.endText();
//	    contentStream.setStrokingColor(Color.DARK_GRAY);
//	    contentStream.setLineWidth(1);
//
//	    int initX = 50;
//	    int initY = pageHeight-50;
//	    int cellHeight = 20;
//	    int cellWidth = 100;
//
//	    int colCount = 2;
//	    int rowCount = totalPages;
//
//	    for(int i = 1; i<=rowCount;i++){
//	        for(int j = 1; j<=colCount;j++){
//	            if(j == 1){
//	                contentStream.addRect(initX,initY,cellWidth,-cellHeight);
//	                contentStream.beginText();
//	                contentStream.newLineAtOffset(initX+30,initY-cellHeight+10);
//	                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN),10);
//	                contentStream.showText("Page "+i);
//	                contentStream.endText();
//	                initX+=cellWidth;
//	            }else{
//	                contentStream.addRect(initX,initY,cellWidth,-cellHeight);
//	                contentStream.beginText();
//	                contentStream.newLineAtOffset(initX+30,initY-cellHeight+10);
//	                contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_ROMAN),10);
//	                if (differencePages.contains(i))
//	                	contentStream.showText("Yes");
//	                else 
//	                	contentStream.showText("No");
//	                contentStream.endText();
//	                initX+=cellWidth;
//	            }
//	        }
//	        initX = 50;
//	        initY -=cellHeight;
//	    }
//
//	    contentStream.stroke();
//	    contentStream.close();
//
//
//	    document.save("C:\\Users\\821988\\OneDrive - Cognizant\\Documents\\temp.pdf");
//	    document.close();
//	    System.out.println("pdf created");
//	}
//	
//	public static void mergePDFs() {
//		PDFMergerUtility ut = new PDFMergerUtility();
////		ut.addSource("");
////		ut.addSource("");
////		ut.setDestinationFileName("");
//		//ut.appendDocument("", "");
//	}
//}