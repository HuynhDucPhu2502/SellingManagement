package model.shelf;

import java.util.Collection;
import java.util.Objects;

import model.suppliers.Supplier;

public class Shelf {
	private String shelfID;
	private String shelfName;
	private ShelfLocation shelfLocation;
	private SizeType sizeType;
	private String status;
	private String note;
	public Shelf() {
		super();
	}
	public Shelf(String idKe) {
		super();
		this.shelfID = idKe;
	}
	public Shelf(String shelfID, String shelfName, ShelfLocation shelfLocation, SizeType sizeType, String status, String note) {
		super();
		this.shelfID = shelfID;
		this.shelfName = shelfName;
		this.shelfLocation = shelfLocation;
		this.sizeType = sizeType;
		this.status = status;
		this.note = note;
	}
	public String getShelfID() {
		return shelfID;
	}
	public void setShelfID(String shelfID) {
		this.shelfID = shelfID;
	}
	public String getShelfName() {
		return shelfName;
	}
	public void setShelfName(String shelfName) {
		this.shelfName = shelfName;
	}
	public ShelfLocation getShelfLocation() {
		return shelfLocation;
	}
	public void setShelfLocation(ShelfLocation shelfLocation) {
		this.shelfLocation = shelfLocation;
	}
	public SizeType getSizeTye() {
		return sizeType;
	}
	public void setSizeType(SizeType sizeType) {
		this.sizeType = sizeType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	@Override
	public int hashCode() {
		return Objects.hash(shelfID);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Shelf other = (Shelf) obj;
		return Objects.equals(shelfID, other.shelfID);
	}
	public Collection<Supplier> getShelfs() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
}
