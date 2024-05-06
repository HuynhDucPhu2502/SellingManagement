package model.employee;

public enum EmployeePosition {
	SALE_EMPLOYEE("Nhân viên bán hàng"),
	MANAGER("Nhân viên quản lí");
	
	private final String displayname;
	
	private EmployeePosition(String displayname) {
		this.displayname = displayname;
	}

	@Override
	public String toString() {
		return displayname;
	}
	
}
