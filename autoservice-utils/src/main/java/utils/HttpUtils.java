package utils;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import beans.ServicesBean;
import dao.ServicesDAO;
import dao.factory.DAOFactory;

public class HttpUtils {
	
	

public static    void getPriceList(HttpServletResponse response) throws IOException {
		
		ServicesDAO servicesDAO = DAOFactory.getFactory().getServiceDAO();
		ArrayList<ServicesBean> allServicesBeans = servicesDAO.getServiceTable();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Перечень услуг");
		int arrayElement=0;	
		for (int row=0;row<=allServicesBeans.size();row++){
				Row oneRow = sheet.createRow(row);
				if(row==0){
					Cell serviceIdHead = oneRow.createCell(0);
					serviceIdHead.setCellValue("№");
					Cell serviceHead = oneRow.createCell(1);
					serviceHead.setCellValue("Название услуги");
					Cell priceHead = oneRow.createCell(2);
					priceHead.setCellValue("Цена за 1 услугу");
				}else{
					System.out.println(arrayElement);
					ServicesBean serviceBean = allServicesBeans.get(arrayElement);
					for(int column=0;column<3;column++){
						Cell cell = oneRow.createCell(column);
						switch(column){
							case 0:
							cell.setCellValue(serviceBean.getServiceId());	
							break;
							case 1:
							cell.setCellValue(serviceBean.getServiceType());
							break;
							case 2:
							cell.setCellValue(serviceBean.getPrice());
							break;
							
						}	
					}
				}
				if(row>0){
					arrayElement++;
				}
			 }  
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition","attachment; filename=ServicePriceList.xlsx");
			ServletOutputStream out = response.getOutputStream();
			workbook.write(out);
			workbook.close();
	 }
}
