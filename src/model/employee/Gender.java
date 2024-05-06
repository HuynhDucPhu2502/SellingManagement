package model.employee;

public enum Gender {
	MALE("Nam"),
	FEMALE("Nữ");
	
	private final String displayname;
	
	private Gender(String displayname) {
		this.displayname = displayname;
	}

	@Override
	public String toString() {
		return displayname;
	}
}
