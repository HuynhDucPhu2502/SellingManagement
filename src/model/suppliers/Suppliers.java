package model.suppliers;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class Suppliers extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2517754755567900836L;
	private ArrayList<Supplier> suppliers;
	private String[] column = {"Mã NCC", "Tên NCC", "Địa chỉ", "Số điện thoại", "Địa chỉ"};
	
	
	
	public Suppliers() {
		suppliers = SupplierDAO.getData();
	}
	
	
	public Suppliers(ArrayList<Supplier> suppliers) {
		super();
		this.suppliers = suppliers;
	}

	@Override
	public String getColumnName(int column) {
		return this.column[column];
	}

	@Override
	public int getRowCount() {
		return suppliers.size();
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Supplier supplier = suppliers.get(rowIndex);
		switch (columnIndex) {
			case 0: return supplier.getSupplierID();
			case 1: return supplier.getSupplierName();
			case 2: return supplier.getAddress();
			case 3: return supplier.getPhoneNumber();
			case 4: return supplier.getEmail();
			default: return null;
		}
	}
	
	
	public ArrayList<Supplier> getSuppliers() {
		return suppliers;
	}
	
	public void setSuppliers(ArrayList<Supplier> suppliers) {
		this.suppliers = suppliers;
		fireTableDataChanged();
	}
	
	
}
