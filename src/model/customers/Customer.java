package model.customers;

import java.util.Objects;

public class Customer {
	private String customerID;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNumber;
	private CustomerType customerType;
		

	public Customer() {
		this("", "", "", "", "", CustomerType.MEMBER);
	}
	
	public Customer(String customerID, String firstName, String lastName, String address, String phoneNumber,
			CustomerType customerType) {
		super();
		this.customerID = customerID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.customerType = customerType;
	}
	
	public Customer(String customerID) {
		super();
		this.customerID = customerID;
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(customerID, phoneNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(customerID, other.customerID) && Objects.equals(phoneNumber, other.phoneNumber);
	}

	@Override
	public String toString() {
		return "Customer [customerID=" + customerID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", customerType=" + customerType + "]";
	}


	public String getCustomerID() {
		return customerID;
	}


	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
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


	public CustomerType getCustomerType() {
		return customerType;
	}


	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}
	
	
	
	
	
	
	
}
