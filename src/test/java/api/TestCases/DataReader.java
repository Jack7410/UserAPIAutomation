package api.TestCases;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.Utilities.ExcelRead;

public class DataReader {
	ExcelRead excel=new ExcelRead();
	
	@DataProvider(name = "ReadUserData")
	public String [] []  readUserData() throws Throwable {
		
		int rowcount=excel.countRow();
		int cellcount=excel.countCell();
		String []  [] data=new String [rowcount] [cellcount] ;
		
		for(int i=1;i<=rowcount;i++) {
			for(int j=0;j<cellcount;j++) {
				data[i-1][j]=excel.readData(i, j);
			}
		}
		
		return data;
	}
	
	
	@DataProvider (name="UserName")
	public String[] getUserName() throws Throwable {
		
		int rowcount=excel.countRow();
		
		
		String[] data=new String[rowcount];
		for(int i=0;i<rowcount;i++) {
			
			data[i]=excel.readData(i+1,1);
		}
		
		return data;
	}

	
}


