package model.customers;

public enum CustomerType {
	MEMBER("Thành viên"),
	VIP("VIP");
	
	private final String displayname;

	private CustomerType(String displayname) {
		this.displayname = displayname;
	}
	
	@Override
	public String toString() {
		return displayname;
	}
	

}
