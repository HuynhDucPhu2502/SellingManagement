package model.statistical;

import java.util.Objects;

public class ProductDetail {
	private String productID;
	private String productName;
	private int totalSoldQuantity;
	public ProductDetail(String productID, String productName, int totalSoldQuantity) {
		super();
		this.productID = productID;
		this.productName = productName;
		this.totalSoldQuantity = totalSoldQuantity;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(productID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductDetail other = (ProductDetail) obj;
		return Objects.equals(productID.toLowerCase(), other.productID.toLowerCase());
	}

	@Override
	public String toString() {
		return "ProductDetail [productID=" + productID + ", productName=" + productName + ", totalSoldQuantity="
				+ totalSoldQuantity + "]";
	}

	public String getProductID() {
		return productID;
	}
	public void setProductID(String productID) {
		this.productID = productID;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getTotalSoldQuantity() {
		return totalSoldQuantity;
	}

	public void setTotalSoldQuantity(int totalSoldQuantity) {
		this.totalSoldQuantity = totalSoldQuantity;
	}
	
	
	
}
