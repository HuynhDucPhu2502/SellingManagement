package model.accounts;

import java.util.Objects;

import model.employee.Employee;

public class Account {
	private String username;
	private String password;
	private	Employee employee;
	
	
	public Account(String username, String password, Employee employee) {
		super();
		this.username = username;
		this.password = password;
		this.employee = employee;
	}
	
	public Account() {
		this("", "", null);
	}
	
	

	@Override
	public int hashCode() {
		return Objects.hash(username);
	}

	
	
	@Override
	public String toString() {
		return "user [username=" + username + ", password=" + password + ", employee=" + employee + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(username, other.username);
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
}
