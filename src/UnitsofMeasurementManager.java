import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import jxl.Cell;
import jxl.CellType;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class UnitsofMeasurementManager {

  private String inputFile;
  private ArrayList<UnitofMeasurement> unitsOfMeasurement;
  
  public UnitsofMeasurementManager() {
	  unitsOfMeasurement=new ArrayList<UnitofMeasurement>();
	  setInputFile("C://Users//user//Desktop//annex-1.xls");
	  try {
		read();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  setInputFile("C://Users//user//Desktop//annex-2.xls");
	  try {
		read();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	  
  }

  public ArrayList<UnitofMeasurement> getUnitsofMeasurement(){
	  return unitsOfMeasurement;
  }
  public void setInputFile(String inputFile) {
    this.inputFile = inputFile;
  }

  public void read() throws IOException  {
    File inputWorkbook = new File(inputFile);
    Workbook w;
    try {
      w = Workbook.getWorkbook(inputWorkbook);
      // Get the first sheet
      Sheet sheet = w.getSheet(0);
      // Loop over first 10 column and lines
      String cell0,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10;
      for (int j = 0; j < sheet.getRows(); j++) {
    	   
    	  if (inputFile.startsWith("C://Users//user//Desktop//annex-1.xls"))
    	  {
    		  cell0 = sheet.getCell(0, j).getContents().toString();
    		  cell1 = sheet.getCell(1, j).getContents().toString();
    		  cell2 = sheet.getCell(2, j).getContents().toString();
    		  cell3 = sheet.getCell(3, j).getContents().toString();
    		  cell4 = sheet.getCell(4, j).getContents().toString();
    		  cell5 = sheet.getCell(5, j).getContents().toString();
    		  cell6 = sheet.getCell(6, j).getContents().toString();
    		  cell7 = sheet.getCell(7, j).getContents().toString();
    		  cell8 = sheet.getCell(8, j).getContents().toString();
    		  cell9 = sheet.getCell(9, j).getContents().toString();
    		  cell10 = sheet.getCell(10, j).getContents().toString();
    		  
    		  UnitofMeasurement um=new UnitofMeasurement(cell0,cell1,cell2,cell3,cell4,cell5,cell6,cell7,cell8,cell9,cell10);
    		  unitsOfMeasurement.add(um);
    	  }
    	  if (inputFile.startsWith("C://Users//user//Desktop//annex-2"))
    	  {
    		 cell0 = sheet.getCell(0, j).getContents().toString();
    		 cell1 = sheet.getCell(1, j).getContents().toString();
    		 cell2 = sheet.getCell(2, j).getContents().toString();
    		 cell3 = sheet.getCell(3, j).getContents().toString();
    		 cell4 = sheet.getCell(4, j).getContents().toString();
    		 cell5 = sheet.getCell(5, j).getContents().toString();
    		 cell6 = sheet.getCell(6, j).getContents().toString();
    		  
    		  UnitofMeasurement um=new UnitofMeasurement("","","","",cell4,cell0,cell1,cell2,cell6,cell5,cell3);
    		  unitsOfMeasurement.add(um);
    	  }
    	  
      }
    } catch (BiffException e) {
      e.printStackTrace();
    }
  }
} 

class UnitofMeasurement
{
	String groupNumber="";
	String sector="";
	String groupId="";
	String quantity="";
	String level_Category="";
	String status="";
	String commonCode="";
	String name="";
	String conversionFactor="";
	String symbol="";
	String description="";
	
	public UnitofMeasurement(String c0, String c1, String c2, String c3, String c4, String c5, String c6, String c7, String c8, String c9, String c10) 
	{
		this.groupNumber=c0;
		this.sector=c1;
		this.groupId=c2;
		this.quantity=c3;
		this.level_Category=c4;
		this.status=c5;
		this.commonCode=c6;
		this.name=c7;
		this.conversionFactor=c8;
		this.symbol=c9;
		this.description=c10;
	}
}

