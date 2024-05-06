package model.employee;

import java.time.LocalDate;
import java.util.Objects;

public class Employee {
	private String employeeID;
	private String lastName;
	private String firstName;
	private String address;
	private String phoneNumber;
	private String email;
	private EmployeePosition position;
	private LocalDate birthDay;
	private Gender gender;
	private double coefficientsSalary;

	public Employee(String employeeID, String lastName, String firstName, String address, String phoneNumber,
			String email, EmployeePosition position, LocalDate birthDay, Gender gender, double coefficientsSalary) {
		super();
		this.employeeID = employeeID;
		this.lastName = lastName;
		this.firstName = firstName;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.position = position;
		this.birthDay = birthDay;
		this.gender = gender;
		this.coefficientsSalary = coefficientsSalary;
	}

	public Employee(String employeeID) {
		super();
		this.employeeID = employeeID;
	}

	public Employee() {
		super();
	}
	
	

	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", lastName=" + lastName + ", firstName=" + firstName
				+ ", address=" + address + ", phoneNumber=" + phoneNumber + ", email=" + email + ", position="
				+ position + ", birthDay=" + birthDay + ", gender=" + gender + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(employeeID.toUpperCase());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(employeeID.toUpperCase(), other.employeeID.toUpperCase());
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
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

	public EmployeePosition getPosition() {
		return position;
	}

	public void setPosition(EmployeePosition position) {
		this.position = position;
	}

	public LocalDate getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(LocalDate birthDay) {
		this.birthDay = birthDay;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public double getCoefficientsSalary() {
		return coefficientsSalary;
	}

	public void setCoefficientsSalary(double coefficientsSalary) {
		this.coefficientsSalary = coefficientsSalary;
	}
	
	

}
