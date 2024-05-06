package model.products;

import java.util.Date;
import java.util.Objects;

import model.categories.Category;
import model.shelf.Shelf;
import model.suppliers.Supplier;

public class Product implements Cloneable {
	private String productID;
	private String productName;
	private double purchasePrice;
	private double sellingPrice;
	private Category category;
	private int stock;
	private String unit;
	private Date recevieDate;
	private Supplier supplier;
	private Shelf shelf;
	
	public Product(String id, String name, double purchasePrice, double sellingPrice, 
			Category category, int stock, String unit, Date recevieDate, Supplier supplier,Shelf shelf) {
		super();
		this.productID = id;
		this.productName = name;
		this.purchasePrice = purchasePrice;
		this.sellingPrice = sellingPrice;
		this.category = category;
		this.stock = stock;
		this.unit = unit;
		this.recevieDate = recevieDate;
		this.supplier = supplier;
		this.shelf = shelf;
	}
	
	public Product() {
		this("", "", 0, 0, null, 0, "", null, null,null);
	}
	
	
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return super.clone();
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
		Product other = (Product) obj;
		return Objects.equals(productID.toLowerCase(), other.productID.toLowerCase());
	}

	
	
	
	

	public String getId() {
		return productID;
	}

	@Override
	public String toString() {
		return "Product [productID=" + productID + ", productName=" + productName + ", purchasePrice=" + purchasePrice
				+ ", sellingPrice=" + sellingPrice + ", category=" + category + ", stock=" + stock + ", unit=" + unit
				+ ", recevieDate=" + recevieDate + ", supplier=" + supplier + ", shelf=" + shelf + "]";
	}
	
	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public void setId(String id) {
		this.productID = id;
	}

	public String getName() {
		return productName;
	}
	public void setName(String name) {
		this.productName = name;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public double getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(double purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getRecevieDate() {
		return recevieDate;
	}

	public void setRecevieDate(Date recevieDate) {
		this.recevieDate = recevieDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}
	
	
	
	
	
	
}
