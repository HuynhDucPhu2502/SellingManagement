package model.statistical;

import java.util.Objects;

public class CustomerDetail {
	private String customerID;
	private String fullName;
	private double totalAmount;
	public CustomerDetail(String customerID, String fullName, double totalAmount) {
		super();
		this.customerID = customerID;
		this.fullName = fullName;
		this.totalAmount = totalAmount;
	}
	
	@Override
	public String toString() {
		return "CustomerDetail [customerID=" + customerID + ", fullName=" + fullName + ", totalAmount=" + totalAmount
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(customerID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CustomerDetail other = (CustomerDetail) obj;
		return Objects.equals(customerID, other.customerID);
	}

	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
