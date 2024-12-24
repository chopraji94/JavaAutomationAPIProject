package api.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;

public class DataProviders {
	
	@DataProvider(name="Data")
	public String[][] getAllData() throws IOException{
		String path = System.getProperty("user.dir")+"//testData//TestData.xlsx";
		ExcelUtility utility = new ExcelUtility(path);
		
		int rowCount = utility.getRowCount("Sheet1");
		int columnCount = utility.getCellCount("Sheet1", 1);
		
		String[][] apiData = new String[rowCount][columnCount];
		
		for(int i=1;i<=rowCount;i++) {
			for(int j=0;j<columnCount;j++) {
				apiData[i-1][j] = utility.getCellData("Sheet1", i, j);
			}
		}
		
		return apiData;
	}
	
	@DataProvider(name="UserNames")
	public String[] getUserName() throws IOException {
		String path = System.getProperty("user.dir")+"//testData//TestData.xlsx";
		ExcelUtility utility = new ExcelUtility(path);
		
		int rowCount = utility.getRowCount("Sheet1");
		
		String[] userNameData = new String[rowCount];
		
		for(int i=1;i<=rowCount;i++) {
			userNameData[i-1] = utility.getCellData("Sheet1", i, 1);
		}
		return userNameData;
	}
}
