package util;

import java.awt.Desktop;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.invoices.Invoice;
import view.file.FileManagerView;
import view.invoices.PrintInvoiceView;


public class ExportFileHelper {
	public static void exportToExcel(FileManagerView view, String sheetName, JTable table) {
		JFileChooser chooser = new JFileChooser();
		chooser.setDialogType(JFileChooser.SAVE_DIALOG);
			
		try{
			chooser.showSaveDialog(view);
			
			File file = chooser.getSelectedFile();
			
			if (file == null || file.getName().trim().isEmpty()) {
				chooser.cancelSelection();
				throw new IllegalArgumentException("Tập tin không hợp lệ");
			}
			
			file = new File(file.getAbsolutePath() + ".xlsx"); // lấy đường dẫn tuyệt đối của file
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet(sheetName);
			
			Row rowCol = sheet.createRow(0);
			for(int i = 0; i < table.getColumnCount(); i++){
				Cell cell = rowCol.createCell(i);
				cell.setCellValue(table.getColumnName(i));
			}
            
			for(int i = 0 ;i < table.getRowCount(); i++){
				Row row = sheet.createRow(i + 1);
				for(int j = 0;j < table.getColumnCount(); j++){
					Cell cell = row.createCell(j);
					if(table.getValueAt(i, j) != null){
						cell.setCellValue(table.getValueAt(i, j).toString());
					}
				}
			}
			FileOutputStream out = new FileOutputStream(new File(file.toString()));
			workbook.write(out);
			workbook.close();
			out.close();
			openFile(file.toString());
		} catch (Exception exception) {
			chooser.cancelSelection();
			JOptionPane.showMessageDialog(null, exception.getMessage());
		}
	}
	
	 private static void openFile(String file){
		 try{
			 File path = new File(file);
	         Desktop.getDesktop().open(path);
		 }catch(IOException ioe){
	         System.out.println(ioe + "0");
	     }
	 }
	 
	 public static void printInvoice(Invoice i) {
		 if(i == null) throw new IllegalStateException("Hóa đơn rỗng");
		 try {
			PrintInvoiceView invoiceView = new PrintInvoiceView(i);
			invoiceView.gettPnlView().print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			throw new IllegalStateException("In hóa đơn thất bại");
		}
	 }
}
