package model.shelf;

public enum ShelfLocation {
	Left("Góc trái"),
	Right("Góc phải");
	
	private String displayname;

	private ShelfLocation(String displayname) {
		this.displayname = displayname;
	}
	
	public String toString() {
		return displayname;
	}
	
	public String getVitriType() {
		return displayname;
	}
	
	public static ShelfLocation fromString(String text) {
        for (ShelfLocation b : ShelfLocation.values()) {
            if (b.displayname.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
