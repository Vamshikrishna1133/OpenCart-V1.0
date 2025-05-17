package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	//Data Provider 1
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		String path = ".\\testData\\Opencart_LoginData.xlsx";
		
		ExcelUtility xlutil = new ExcelUtility(path);
		int totalRows = xlutil.getRowCount("Sheet1");
		int totalCols = xlutil.getCellCount("Sheet1",1);
		
		String logindata [][] = new String[totalRows][totalCols];
		
		for(int i=1;i<=totalRows;i++) //i is row
		{
			for(int j=0;j<totalCols;j++) //j is column
			{
				logindata[i-1][j] = xlutil.getCellData("Sheet1", i, j); //storing data into array
			}
		}
		return logindata; //returning two dimensional array
	}

	
	//Data Provider 2
	
	//Data Provider 3
}
