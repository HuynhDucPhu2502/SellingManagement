package model.shelf;

public enum SizeType {
	SMALL("80x40x180"),
	MEDIUM("100x50x200"),
	LONG("120x60x220");
	
	

	private final String displayname;


	private SizeType(String displayname) {
		this.displayname = displayname;
	}
	
	public String toString() {
		return displayname;
	}
	
	public String getKichthuocType() {
		return displayname;
	}
	
	public static SizeType fromString(String text) {
        for (SizeType b : SizeType.values()) {
            if (b.displayname.equalsIgnoreCase(text)) {
                return b;
            }
        }
        return null;
    }
}
