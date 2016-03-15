/**
 * 
 */
package no.systema.overview.ufortolledeoppdrag.view;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.view.document.AbstractExcelView;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;

import no.systema.overview.ufortolledeoppdrag.model.jsonjackson.JsonTopicRecord;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Sep 22, 2013
 * 
 */
public class UoppdragListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public UoppdragListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonTopicRecord> listUoppdrag = (List<JsonTopicRecord>) model.get("listUoppdrag");
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Ufortollede Oppdrag");
        sheet.setDefaultColumnWidth(30);
         
        // create style for header cells
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
         
        // create header row
        HSSFRow header = sheet.createRow(0);
         
        header.createCell(0).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.date", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.dager", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.tar", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.avd", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.topicNr", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.sign", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.extref", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.handelsFakt", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.receiver", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.kll", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.weight", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.godsNr", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.status", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.overview.uoppdrag.main.list.label.mark", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(style);
        
        // create data rows
        int rowCount = 1;
         
        for (JsonTopicRecord record : listUoppdrag) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getDato());
            aRow.createCell(1).setCellValue(record.getDager());
            aRow.createCell(2).setCellValue(record.getTariffor());
            aRow.createCell(3).setCellValue(record.getAvd());
            aRow.createCell(4).setCellValue(record.getOpd());
            aRow.createCell(5).setCellValue(record.getSign());
            aRow.createCell(6).setCellValue(record.getExtRef());
            aRow.createCell(7).setCellValue(record.getAntHfakt());
            aRow.createCell(8).setCellValue(record.getMottaker());
            aRow.createCell(9).setCellValue(record.getKll());
            aRow.createCell(10).setCellValue(record.getVekt());
            aRow.createCell(11).setCellValue(record.getGodsnr());
            aRow.createCell(12).setCellValue(record.getStatus());
            aRow.createCell(13).setCellValue(record.getMerknad());
        }
    }
  
	
	
}
