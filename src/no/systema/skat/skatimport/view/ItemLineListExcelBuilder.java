/**
 * 
 */
package no.systema.skat.skatimport.view;

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

import no.systema.skat.skatimport.model.jsonjackson.topic.items.JsonSkatImportSpecificTopicItemRecord;
import no.systema.skat.util.SkatConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Nov 24, 2014
 * 
 */
public class ItemLineListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public ItemLineListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonSkatImportSpecificTopicItemRecord> itemList = (List<JsonSkatImportSpecificTopicItemRecord>) model.get(SkatConstants.ITEM_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("SKAT-Import Item list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_syav.avdelning", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_syop.angNr", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_syli.linjeNr", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_28b.purchaseSellerInvoice", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_32.varepostNr", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_34.oprLand", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_331.varekod", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_36.preference", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_37.procedure", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_35.bruttov", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_38.nettov", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_412.supplEnhVerdi", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_313a.kolliAntal", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_315a.vareDescription", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(style);
        
        header.createCell(14).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_46.statValue", new Object[0], request.getLocale()));
        header.getCell(14).setCellStyle(style);
        
        header.createCell(15).setCellValue(this.context.getMessage("systema.skat.import.item.list.label.dkiv_42.varansPris", new Object[0], request.getLocale()));
        header.getCell(15).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonSkatImportSpecificTopicItemRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getDkiv_syav());
            aRow.createCell(1).setCellValue(record.getDkiv_syop());
            aRow.createCell(2).setCellValue(record.getDkiv_syli());
            aRow.createCell(3).setCellValue(record.getDkiv_28b());
            aRow.createCell(4).setCellValue(record.getDkiv_32());
            aRow.createCell(5).setCellValue(record.getDkiv_34());
            aRow.createCell(6).setCellValue(record.getDkiv_331());
            aRow.createCell(7).setCellValue(record.getDkiv_36());
            aRow.createCell(8).setCellValue(record.getDkiv_37());
            aRow.createCell(9).setCellValue(record.getDkiv_35());
            aRow.createCell(10).setCellValue(record.getDkiv_38());
            aRow.createCell(11).setCellValue(record.getDkiv_412());
            aRow.createCell(12).setCellValue(record.getDkiv_313a());
            aRow.createCell(13).setCellValue(record.getDkiv_315a());
            aRow.createCell(14).setCellValue(record.getDkiv_46());
            aRow.createCell(15).setCellValue(record.getDkiv_42());
            
        }
    }
	
}
