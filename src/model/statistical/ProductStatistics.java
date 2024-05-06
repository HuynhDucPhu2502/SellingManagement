package model.statistical;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

public class ProductStatistics extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3465772530544144953L;
	private ArrayList<ProductDetail> productDetails;
	private String[] columns = {"Top", "Mã Hàng hóa", "Tên hàng hóa", "Số lượng đã bán"};
	
	public ProductStatistics() {
		productDetails = StatisticalDAO.getTotalSoldProductsQuantityAll();
	}
	
	public ArrayList<ProductDetail> getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(ArrayList<ProductDetail> productDetails) {
		this.productDetails = productDetails;
		fireTableDataChanged();
	}
	
	@Override
	public String getColumnName(int column) {
		return this.columns[column];
	}
	@Override
	public int getRowCount() {
		return productDetails.size();
	}
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		ProductDetail productDetail = productDetails.get(rowIndex);
		switch (columnIndex) {
			case 0: return productDetails.indexOf(productDetail) + 1;
			case 1: return productDetail.getProductID();
			case 2: return productDetail.getProductName();
			case 3: return productDetail.getTotalSoldQuantity();
			default: return null;
		}
	}
	
	
}
