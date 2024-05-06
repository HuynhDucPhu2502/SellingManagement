package model.statistical;

import java.util.Objects;

public class EmployeeDetail {
	private String employeeID;
	private String fullName;
	private double totalAmount;
	public EmployeeDetail(String employeeID, String fullName, double totalAmount) {
		super();
		this.employeeID = employeeID;
		this.fullName = fullName;
		this.totalAmount = totalAmount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(employeeID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EmployeeDetail other = (EmployeeDetail) obj;
		return Objects.equals(employeeID, other.employeeID);
	}

	@Override
	public String toString() {
		return "EmployeeDetail [employeeID=" + employeeID + ", fullName=" + fullName + ", totalAmount=" + totalAmount
				+ "]";
	}

	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
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
