package model.invoices;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class InvoiceDetailsList extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5021186238363731590L;
	private ArrayList<InvoiceDetails> invoiceDetailsList;
	private final String[] columns = {"Mã chi tiết hóa đơn", "Mã hóa đơn", "Tên sản phẩm", "Mã sản phẩm", "Giá bán", "Số lượng", "Thành tiền"};
	
	
	
	public InvoiceDetailsList() {
		invoiceDetailsList = InvoiceDetailsDAO.getData();
	}
	
	
	
	public InvoiceDetailsList(ArrayList<InvoiceDetails> invoiceDetailsList) {
		super();
		this.invoiceDetailsList = invoiceDetailsList;
	}



	@Override
	public String getColumnName(int column) {
		return this.columns[column];
	}


	@Override
	public int getRowCount() {
		return invoiceDetailsList.size();
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		InvoiceDetails invoiceDetails = invoiceDetailsList.get(rowIndex);
		switch (columnIndex) {
			case 0: return invoiceDetails.getInvoiceDetailID();
			case 1: return invoiceDetails.getInvoice().getInvoiceID();
			case 2: return invoiceDetails.getProduct().getName();
			case 3: return invoiceDetails.getProduct().getProductID();
			case 4: return invoiceDetails.getSellingPrice();
			case 5: return invoiceDetails.getQuantity();
			case 6: return invoiceDetails.getTotalPrice();
			default: return null;
		}
	}
	
	public ArrayList<InvoiceDetails> getInvoiceDetailsList() {
		return invoiceDetailsList;
	}
	public void setInvoiceDetailsList(ArrayList<InvoiceDetails> invoiceDetailsList) {
		this.invoiceDetailsList = invoiceDetailsList;
		fireTableDataChanged();
	}
	
	
}
