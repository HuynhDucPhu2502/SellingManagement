package model.invoices;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Invoices extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3036452950257036834L;
	private ArrayList<Invoice> invoices;
	private final String[] columns = {"Mã hóa đơn", "Mã NV", "Tên NV", "Mã KH", "Tên KH", "Loại KH", "Tổng tiền", "Ngày lập"};
	
	
	
	public Invoices() {
		super();
		this.invoices = InvoiceDAO.getData();
	}
	
	public Invoices(ArrayList<Invoice> invoices) {
		super();
		this.invoices = invoices;
	}

	@Override
	public String getColumnName(int column) {
		return this.columns[column];
	}
	@Override
	public int getRowCount() {
		return invoices.size();
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Invoice invoice = invoices.get(rowIndex);
		
		switch (columnIndex) {
			case 0: return invoice.getInvoiceID();
			case 1: return invoice.getAccount().getEmployee().getEmployeeID();
			case 2: return invoice.getAccount().getEmployee().getFirstName() + " " + invoice.getAccount().getEmployee().getLastName();
			case 3: {
				if (invoice.getCustomer() == null) return "Vãng lai";
				else return invoice.getCustomer().getCustomerID();
			}
			case 4: {
				if (invoice.getCustomer() == null) return "Vãng lai";
				else return invoice.getCustomer().getFirstName() + invoice.getCustomer().getLastName();
			}
			case 5: {
				if (invoice.getCustomer() == null) return "Vãng lai";
				else return invoice.getCustomer().getCustomerType().toString();
			}
			case 6: return invoice.getTotalAmount();
			case 7: return invoice.getInvoiceDate();
			default: return null;
		}
	}
	public ArrayList<Invoice> getInvoices() {
		return invoices;
	}
	public void setInvoices(ArrayList<Invoice> invoices) {
		this.invoices = invoices;
		fireTableDataChanged();
	}
	
	
}
