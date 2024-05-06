package model.suppliers;

import java.util.Objects;

public class Supplier {
	private String supplierID;
	private String supplierName;
	private String address;
	private String phoneNumber;
	private String email;
	
	public Supplier(String supplierID, String supplierName, String address, String phoneNumber, String email) {
		super();
		this.supplierID = supplierID;
		this.supplierName = supplierName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	public Supplier() {
		this ("", "", "", "", "");
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(supplierID);
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		return Objects.equals(supplierID.toLowerCase(), other.supplierID.toLowerCase());
	}



	@Override
	public String toString() {
		return "Supplier [supplierID=" + supplierID + ", supplierName=" + supplierName + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}


	public String getSupplierID() {
		return supplierID;
	}
	public void setSupplierID(String supplierID) {
		this.supplierID = supplierID;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
