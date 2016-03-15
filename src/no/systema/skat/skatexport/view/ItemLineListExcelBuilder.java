/**
 * 
 */
package no.systema.skat.skatexport.view;

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

import no.systema.skat.skatexport.model.jsonjackson.topic.items.JsonSkatExportSpecificTopicItemRecord;
import no.systema.skat.util.SkatConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Nov 25, 2014
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
        List<JsonSkatExportSpecificTopicItemRecord> itemList = (List<JsonSkatExportSpecificTopicItemRecord>) model.get(SkatConstants.ITEM_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("SKAT-Export Item list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_syav.avdelning", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_syop.angNr", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_syli.linjeNr", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_28b.purchaseSellerInvoice", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_32.varepostNr", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_34a.oprLand", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_331.varekod", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_37.procedure", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_35.bruttov", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_38.nettov", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_412.supplEnhVerdi", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_313.kolliAntal", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_315.vareDescription", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_46.statValue", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(style);
        
        header.createCell(14).setCellValue(this.context.getMessage("systema.skat.export.item.list.label.dkev_42.varansPris", new Object[0], request.getLocale()));
        header.getCell(14).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonSkatExportSpecificTopicItemRecord record : itemList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getDkev_syav());
            aRow.createCell(1).setCellValue(record.getDkev_syop());
            aRow.createCell(2).setCellValue(record.getDkev_syli());
            aRow.createCell(3).setCellValue(record.getDkev_28b());
            aRow.createCell(4).setCellValue(record.getDkev_32());
            aRow.createCell(5).setCellValue(record.getDkev_34a());
            aRow.createCell(6).setCellValue(record.getDkev_331());
            aRow.createCell(7).setCellValue(record.getDkev_37());
            aRow.createCell(8).setCellValue(record.getDkev_35());
            aRow.createCell(9).setCellValue(record.getDkev_38());
            aRow.createCell(10).setCellValue(record.getDkev_412());
            aRow.createCell(11).setCellValue(record.getDkev_313());
            aRow.createCell(12).setCellValue(record.getDkev_315());
            aRow.createCell(13).setCellValue(record.getDkev_46());
            aRow.createCell(14).setCellValue(record.getDkev_42());
        }
    }
	
}
