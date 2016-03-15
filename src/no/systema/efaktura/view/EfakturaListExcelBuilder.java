/**
 * 
 */
package no.systema.efaktura.view;

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

import no.systema.efaktura.model.jsonjackson.JsonEfakturaMainListRecord;
import no.systema.efaktura.util.EfakturaConstants;
import no.systema.main.context.TdsAppContext;
/**
 * 
 * @author oscardelatorre
 * @date Nov 2, 2015
 * 
 */
public class EfakturaListExcelBuilder extends AbstractExcelView {
	private ApplicationContext context;
	
	public EfakturaListExcelBuilder(){
		this.context = TdsAppContext.getApplicationContext();
	}
	
	protected void buildExcelDocument(Map<String, Object> model,
        HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // get data model which is passed by the Spring Container via our own Controller implementation
        List<JsonEfakturaMainListRecord> list = (List<JsonEfakturaMainListRecord>) model.get(EfakturaConstants.DOMAIN_LIST);
         
        // create a new Excel sheet
        HSSFSheet sheet = workbook.createSheet("Efaktura list");
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

        header.createCell(0).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.avd", new Object[0], request.getLocale()));
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.xffn.faktnr", new Object[0], request.getLocale()));
        header.getCell(1).setCellStyle(style);
        
        header.createCell(2).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.xfkn.kundenr", new Object[0], request.getLocale()));
        header.getCell(2).setCellStyle(style);
        
        header.createCell(3).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.herfa.agentref", new Object[0], request.getLocale()));
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.hedtop.date", new Object[0], request.getLocale()));
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.hesdf.from", new Object[0], request.getLocale()));
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.hesdt.to", new Object[0], request.getLocale()));
        header.getCell(6).setCellStyle(style);
       
        header.createCell(7).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.henas.avs", new Object[0], request.getLocale()));
        header.getCell(7).setCellStyle(style);
        
        header.createCell(8).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.henak.mott", new Object[0], request.getLocale()));
        header.getCell(8).setCellStyle(style);
        
        header.createCell(9).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.hekdpl.prkd", new Object[0], request.getLocale()));
        header.getCell(9).setCellStyle(style);
        
        header.createCell(10).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.hent.ant", new Object[0], request.getLocale()));
        header.getCell(10).setCellStyle(style);
        
        header.createCell(11).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.hevkt.vkt", new Object[0], request.getLocale()));
        header.getCell(11).setCellStyle(style);
        
        header.createCell(12).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.hem3.m3", new Object[0], request.getLocale()));
        header.getCell(12).setCellStyle(style);
        
        header.createCell(13).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.helm.lm", new Object[0], request.getLocale()));
        header.getCell(13).setCellStyle(style);
        
        header.createCell(14).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.xpdf.pdf", new Object[0], request.getLocale()));
        header.getCell(14).setCellStyle(style);
        
        header.createCell(15).setCellValue(this.context.getMessage("systema.efaktura.mainlist.label.xfst.status", new Object[0], request.getLocale()));
        header.getCell(15).setCellStyle(style);
        
        
        // create data rows
        int rowCount = 1;
         
        for (JsonEfakturaMainListRecord record : list) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(record.getAvd() + "/" + record.getOpd());
            
            aRow.createCell(1).setCellValue(record.getXffn());
            aRow.createCell(2).setCellValue(record.getXfkn());
            aRow.createCell(3).setCellValue(record.getHerfa());
            aRow.createCell(4).setCellValue(record.getHedtop());
            aRow.createCell(5).setCellValue(record.getHesdf());
            aRow.createCell(6).setCellValue(record.getHesdt());
            aRow.createCell(7).setCellValue(record.getHenas());
            aRow.createCell(8).setCellValue(record.getHenak());
            aRow.createCell(9).setCellValue(record.getHekdpl());
            aRow.createCell(10).setCellValue(record.getHent());
            aRow.createCell(11).setCellValue(record.getHevkt());
            aRow.createCell(12).setCellValue(record.getHem3());
            aRow.createCell(13).setCellValue(record.getHelm());
            aRow.createCell(14).setCellValue(record.getXpdf());
            
            if("E".equals(record.getXfst())){
            	aRow.createCell(15).setCellValue("(E)Error");
            }else if("O".equals(record.getXfst())){
            	aRow.createCell(15).setCellValue("(O)OK");
            }else if("C".equals(record.getXfst())){
            	aRow.createCell(15).setCellValue("(C)Sendt");
            }else if("A".equals(record.getXfst())){
            	aRow.createCell(15).setCellValue("(A)Ftp-øverf.");
            }else{
            	aRow.createCell(15).setCellValue(record.getXfst());
            }
            
            
        }
    }
	
}
