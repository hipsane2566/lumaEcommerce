package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProvidersClass {
	
	static String file = ".\\testData\\luma_LoginTestData.xlsx";
	
	@DataProvider(name="LoginData")
	public String [][] getData() throws IOException{
		ExcelUtility utils = new ExcelUtility(file);
		
		int totalRows = utils.getRowCount("Sheet1");
		int totalCols = utils.getCellCount("Sheet1", 1);
		
		String loginData [][] = new String [totalRows][totalCols];
		for(int i=1;i<=totalRows;i++) {
			for(int j = 0;j<totalCols;j++) {
				loginData[i-1][j] = utils.getCellData("Sheet1", i, j);
			}
		}
		return loginData;
	}
}
