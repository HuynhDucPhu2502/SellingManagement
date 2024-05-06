package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;


import model.shelf.SizeType;
import util.FetchDataStatus;
import model.shelf.Shelf;
import model.shelf.ShelfDAO;
import model.shelf.Shelfs;
import model.shelf.ShelfLocation;
import view.shelfs.ShelfManagerView;



public class ShelfManagerController implements ActionListener {
	private ShelfManagerView shelfManagerView;
	private Shelfs shelfs;

	public ShelfManagerController(ShelfManagerView shelfManagerView) {
		this.shelfManagerView = shelfManagerView;
		this.shelfs = shelfManagerView.getShelfs();
		
		
		this.addAction();
		setupTableSelection();
	}
	
	private void addAction() {
		this.shelfManagerView.getAddBtn().addActionListener(this);
		this.shelfManagerView.getRemoveBtn().addActionListener(this);
		this.shelfManagerView.getUpdateBtn().addActionListener(this);
		this.shelfManagerView.getDeleteBtn().addActionListener(this);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(shelfManagerView.getAddBtn())) {
			this.addShelf();
			return;
		}
		if(o.equals(shelfManagerView.getRemoveBtn())) {
			this.removeShelf();	
			return;
		}
		if(o.equals(shelfManagerView.getUpdateBtn())) {
			this.updateInforOfShelf();
			return;
		}
		if(o.equals(shelfManagerView.getDeleteBtn())) {
			this.resetTxtField();
			return;
		}
	}
	
	private void addShelf() {
		Shelf temp = createShelf();
		if(temp == null)
			return;
		if(ShelfDAO.insertData(temp)) {
			shelfs.setListShelfs(FetchDataStatus.getShelfData());
			
			this.resetTxtField();
			JOptionPane.showMessageDialog(null, "Thêm kệ hàng thành công");
			return;
		}else {
			JOptionPane.showMessageDialog(null, "Kệ hàng đã tồn tại");
			return;
		}
	}
	
	private void removeShelf() {
		int index = -1;
		index = shelfManagerView.getShelfTable().getSelectedRow();
		if(index < 0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng xóa");
			return;
		}
		String c_ID = shelfManagerView.getShelfs().getListShelfs().get(index).getShelfID();
		if(ShelfDAO.deleteData(c_ID)) {
			shelfs.setListShelfs(FetchDataStatus.getShelfData());
			
			this.resetTxtField();
			JOptionPane.showMessageDialog(null, "Xóa kệ hàng thành công");
			return;
		}else {
			JOptionPane.showMessageDialog(null, "Xóa kệ hàng thất bại");
			return;
		}
	}
	
	private void updateInforOfShelf() {
		int index = -1;
		index = shelfManagerView.getShelfTable().getSelectedRow();
		if(index < 0) {
			JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng sửa thông tin");
			return;
		}
		Shelf newInfor = this.createShelf();
		if(newInfor == null) {
			return;
		}
		if(ShelfDAO.updateData(newInfor)) {
			this.resetTxtField();
			shelfs.setListShelfs(FetchDataStatus.getShelfData());
			JOptionPane.showMessageDialog(null, "Sửa thông tin thành công");
			return;
		}else {
			JOptionPane.showMessageDialog(null, "Sửa thông tin thất bại");
			return;
		}
	}
	
	
	private Shelf createShelf() {
		String shelfID = shelfManagerView.getShelfIDTxtField().getText();
		if(shelfID.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Mã kệ hàng không được rỗng");
			shelfManagerView.getShelfIDTxtField().requestFocus();
			return null;
		}
		
		
		
		String shelfName = shelfManagerView.getShelfNameTxtField().getText();
		if(shelfName.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, " Tên kệ hàng không được rỗng");
			shelfManagerView.getShelfNameTxtField().requestFocus();
			return null;
		}
		
		String shelfLocationStr = (String)shelfManagerView.getShelfLocationCBox().getSelectedItem();
	    ShelfLocation shelfLocation = ShelfLocation.fromString(shelfLocationStr);
	    
	    String sizeTypeStr = (String)shelfManagerView.getSizeTypeCBox().getSelectedItem();
	    SizeType sizeType = SizeType.fromString(sizeTypeStr);
	    

		
		String status = shelfManagerView.getStatusTxtField().getText();
		if(status.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Trạng thái kệ hàng không được rỗng");
			shelfManagerView.getStatusTxtField().requestFocus();
			return null;
		}
		

		String note = shelfManagerView.getNoteTxtField().getText();
		if(note.trim().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Ghi chú không được rỗng");
			shelfManagerView.getNoteTxtField().requestFocus();
			return null;
		}
		
		return new Shelf(shelfID, shelfName, shelfLocation, sizeType, status, note);
	}
	
	private void resetTxtField() {
		shelfManagerView.getShelfIDTxtField().setText(null);
		shelfManagerView.getShelfIDTxtField().setEditable(true);
		shelfManagerView.getShelfNameTxtField().setText(null);
		shelfManagerView.getShelfLocationCBox().setSelectedIndex(0);
		shelfManagerView.getSizeTypeCBox().setSelectedIndex(0);
		shelfManagerView.getStatusTxtField().setText(null);
		shelfManagerView.getNoteTxtField().setText(null);
	}
	
	private void showTxtField(int selectedIndex) {
		Shelf shelf = shelfs.getListShelfs().get(selectedIndex);
		
		shelfManagerView.getShelfIDTxtField().setText(shelf.getShelfID());
		shelfManagerView.getShelfIDTxtField().setEditable(false);
		shelfManagerView.getShelfNameTxtField().setText(shelf.getShelfName());
		shelfManagerView.getShelfLocationCBox().setSelectedItem(shelf.getShelfLocation().toString());
		shelfManagerView.getSizeTypeCBox().setSelectedItem(shelf.getSizeTye().toString());
		shelfManagerView.getStatusTxtField().setText(shelf.getStatus());
		shelfManagerView.getNoteTxtField().setText(shelf.getNote());
	}
	
	public void resetAll() {
		resetTxtField();
		shelfs.setListShelfs(FetchDataStatus.getShelfData());
	}
	
	private void setupTableSelection() {
		shelfManagerView.getShelfTable().getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int index = shelfManagerView.getShelfTable().getSelectedRow();
				if (index != -1) showTxtField(index);
				else resetTxtField();
			}
		});
	}
}
