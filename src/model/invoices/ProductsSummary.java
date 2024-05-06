package model.invoices;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import model.products.Product;
import model.products.ProductDAO;

public class ProductsSummary extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3699418567872906136L;
	private ArrayList<Product> products;
	private String[] column = {"Tên sản phẩm", "Mã sản phẩm", "Giá Bán", "Tồn kho", "Đơn vị"};
	
	public ProductsSummary() {
		products = ProductDAO.getData();
	}
	
	
	
	public ProductsSummary(ArrayList<Product> products) {
		super();
		this.products = products;
	}



	public Product getProductByID(String productID) {
		return products.stream().filter(x -> x.getProductID().equalsIgnoreCase(productID))
				.findFirst().orElse(null);
	}
	
	
	@Override
	public String getColumnName(int column) {
		return this.column[column];
	}

	@Override
	public int getRowCount() {
		return products.size();
	}
	
	@Override
	public int getColumnCount() {
		return column.length;
	}
	
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Product product = products.get(rowIndex);
		switch (columnIndex) {
			case 0: return product.getName();
			case 1: return product.getId();
			case 2: return product.getSellingPrice();
			case 3: return product.getStock();
			case 4: return product.getUnit();
			default: return null;
		}
	}

	public ArrayList<Product> getProducts() {
		return products;
	}

	public void setProducts(ArrayList<Product> products) {
		this.products = products;
		fireTableDataChanged();
	}
	
	
	
	
	
}
