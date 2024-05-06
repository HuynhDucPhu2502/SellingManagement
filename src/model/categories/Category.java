package model.categories;

import java.util.Objects;

public class Category {
	private String categoryID;
	private String categoryName;
	private int itemCount;
	
	
	
	public Category(String categoryID, String categoryName, int itemCount) {
		super();
		this.categoryID = categoryID;
		this.categoryName = categoryName;
		this.itemCount = itemCount;
	}

	public Category(String categoryName) {
		this.categoryID = "";
		this.categoryName = categoryName;
		this.itemCount = 0;
	}
	
	public Category() {
		this("", "", 0);
	}

	
	

	@Override
	public int hashCode() {
		return Objects.hash(categoryID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Category other = (Category) obj;
		return Objects.equals(categoryID.toLowerCase(), other.categoryID.toLowerCase());
	}

	@Override
	public String toString() {
		return "Category [maLoai=" + categoryID + ", tenLoai=" + categoryName + ", soLuong=" + itemCount + "]";
	}

	public String getCategoryID() {
		return categoryID;
	}

	public void setCategoryID(String categoryID) {
		this.categoryID = categoryID;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}

	
	

	
	
}
