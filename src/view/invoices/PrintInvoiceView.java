package view.invoices;

import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JTextPane;

import model.invoices.Invoice;
import model.invoices.InvoiceDetails;
import model.invoices.InvoiceDetailsDAO;

public class PrintInvoiceView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1878044585695965767L;
	
	private Date date = null;
	
	private String invoiceID = "";
	
	private String employeeID = "";
	
	private String cusID = "";

	private String moneyGiveback = "";

	private String total = "";

	private JTextPane tPnlView;

	public PrintInvoiceView(Invoice invoice){
		// TODO Auto-generated constructor 
		
		if(invoice.getCustomer() == null) {
			cusID = "Vãng lai";
		}else {
			this.cusID = invoice.getCustomer().getCustomerID();
		}
	    
	    this.invoiceID = invoice.getInvoiceID();
	    this.employeeID = invoice.getAccount().getEmployee().getEmployeeID();
	    this.date = invoice.getInvoiceDate();
	    this.total = String.valueOf(invoice.getTotalAmount());
	    this.moneyGiveback = "";
		
		tPnlView = new JTextPane();
		
		tPnlView = setPnlView();
	}
	
	private JTextPane setPnlView() {
		JTextPane tPnlView = new JTextPane();
	    tPnlView.setText("                                                    CUA HANG TIEN LOI XNXX\n");
	    tPnlView.setText(tPnlView.getText() + "Dia chi : ap 2, xa Nghia Trung, huyen Bu Dang, tinh Binh Phuoc\n");
	    tPnlView.setText(tPnlView.getText() + "Hotline : 0 111 222 333\n");
	    tPnlView.setText(tPnlView.getText() + "Ma hoa don : " + this.invoiceID + "                                                       Ma nhan vien : " + this.employeeID +"\n");
	    tPnlView.setText(tPnlView.getText() + "Ma khach hang : " + this.cusID + "\n");
	    tPnlView.setText(tPnlView.getText() + "Ngay lap : " + this.date.toString() + "\n");  
	    tPnlView.setText(tPnlView.getText() + "                                                         HOA DON BAN HANG\n");
	    tPnlView.setText(tPnlView.getText() + "         ----------------------------------------------------------------------------------------------------------\n");
	    tPnlView.setText(tPnlView.getText() + "\tTên hàng hóa\tSố luong\tDon gia\tThanh tien\n");
		
		for(InvoiceDetails d : InvoiceDetailsDAO.getDataByInvoiceID(invoiceID)) {
			tPnlView.setText(tPnlView.getText() + "\t" + d.getProduct().getName() + "\t\t" + d.getQuantity() + "\t" + d.getSellingPrice() + "\t" + d.getTotalPrice() + "\n");
		}
		
		tPnlView.setText(tPnlView.getText() + "         ----------------------------------------------------------------------------------------------------------\n");
		tPnlView.setText(tPnlView.getText() + "Tong tien :\t\t\t\t\t" + this.total + "\n");
		tPnlView.setText(tPnlView.getText() + "Tien thoi lai :\t\t\t\t\t" + this.moneyGiveback + "\n");
		tPnlView.setText(tPnlView.getText() + "         ----------------------------------------------------------------------------------------------------------\n");
		tPnlView.setText(tPnlView.getText() + "                                                         Cam on da mua hang!!!\n");

	    return tPnlView;
	}

	public JTextPane gettPnlView() {
		return tPnlView;
	}
}
