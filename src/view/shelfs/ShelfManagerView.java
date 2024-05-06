package view.shelfs;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import model.shelf.Shelfs;
import util.ColorHelper;
import util.LayoutHelper;

public class ShelfManagerView {
	
	private JPanel panel;
	
	private JTextField shelfIDTxtField;
	private JTextField shelfNameTxtField;
	private JTextField statusTxtField;
	private JTextField noteTxtField;
	
	private JTable shelfTable;
	private Shelfs shelfs;
	
	private JButton addBtn;
	private JButton removeBtn;
	private JButton updateBtn;
	private JButton deleteBtn;
	
	
	private JComboBox<String> shelfLocationCBox;
	private JComboBox<String> sizeTypeCBox;

	public ShelfManagerView(){
		
		this.panel=new JPanel(new BorderLayout());
		
		shelfs = new Shelfs();
		
		panel.add(setTablePanel(),BorderLayout.NORTH);
		panel.add(setupFormPanel(),BorderLayout.CENTER);
		panel.add(setupBtnPanel(),BorderLayout.SOUTH);
			
	}

	public JPanel getPanel() {
		return panel;
	}
	

	private JPanel setupFormPanel() {
		JPanel formPanel = LayoutHelper.getGridBagLayout(); 
		formPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin kệ hàng"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, LayoutHelper.setLabel("Mã kệ:"), formPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, shelfIDTxtField = LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(0, 1, 0, 0, LayoutHelper.setLabel("Tên kệ:"), formPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, shelfNameTxtField =  LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(0, 2, 0, 0, LayoutHelper.setLabel("Vị trí:"), formPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, shelfLocationCBox = new JComboBox<>(), formPanel, gbc);
		LayoutHelper.addItem(0, 3, 0, 0, LayoutHelper.setLabel("Kích thước:"), formPanel, gbc);
		LayoutHelper.addItem(1, 3, 1, 0, sizeTypeCBox = new JComboBox<>(), formPanel, gbc);
		LayoutHelper.addItem(0, 4, 0, 0, LayoutHelper.setLabel("Trạng thái:"), formPanel, gbc);
		LayoutHelper.addItem(1, 4, 1, 0, statusTxtField =  LayoutHelper.getTextField(10), formPanel, gbc);
		LayoutHelper.addItem(0, 5, 0, 0, LayoutHelper.setLabel("Ghi chú:"), formPanel, gbc);
		LayoutHelper.addItem(1, 5, 1, 0, noteTxtField =  LayoutHelper.getTextField(10), formPanel, gbc);
		
		
		shelfLocationCBox.addItem("Góc trái");
		shelfLocationCBox.addItem("Góc phải");
		
		sizeTypeCBox.addItem("80x40x180");
		sizeTypeCBox.addItem("100x50x200");
		sizeTypeCBox.addItem("120x60x220");
		
		return formPanel;
		
	}
	
	private JPanel setTablePanel() {
		JPanel tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách Kệ hàng"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		tablePanel.setPreferredSize(new Dimension(0, 300));
		
		shelfTable = new JTable(shelfs);
		tablePanel.add(new JScrollPane(shelfTable));
		
		return tablePanel;
	}
	
	private JPanel setupBtnPanel() {
		JPanel btnPanel = new JPanel();
		
		btnPanel.add(addBtn = LayoutHelper.setupBtn("Thêm", "src/Images/btn_icon/add_icon.png"));
		btnPanel.add(removeBtn = LayoutHelper.setupBtn("Xóa", "src/Images/btn_icon/remove_icon.png"));
		btnPanel.add(updateBtn =  LayoutHelper.setupBtn("Cập nhật", "src/Images/btn_icon/update_icon.png"));
		btnPanel.add(deleteBtn=LayoutHelper.setupBtn("Xóa rỗng", "src/Images/btn_icon/reset_icon.png"));
		
		return btnPanel;
	}




	public JTextField getShelfIDTxtField() {
		return shelfIDTxtField;
	}




	public void setShelfIDTxtField(JTextField shelfIDTxtField) {
		this.shelfIDTxtField = shelfIDTxtField;
	}




	public JTextField getShelfNameTxtField() {
		return shelfNameTxtField;
	}




	public void setShelfNameTxtField(JTextField shelfNameTxtField) {
		this.shelfNameTxtField = shelfNameTxtField;
	}




	public JTextField getStatusTxtField() {
		return statusTxtField;
	}




	public void setStatusTxtField(JTextField statusTxtField) {
		this.statusTxtField = statusTxtField;
	}




	public JTextField getNoteTxtField() {
		return noteTxtField;
	}




	public void setNoteTxtField(JTextField noteTxtField) {
		this.noteTxtField = noteTxtField;
	}




	public JTable getShelfTable() {
		return shelfTable;
	}




	public void setShelfTable(JTable shelfTable) {
		this.shelfTable = shelfTable;
	}




	public JButton getAddBtn() {
		return addBtn;
	}




	public void setAddBtn(JButton addBtn) {
		this.addBtn = addBtn;
	}




	public JButton getRemoveBtn() {
		return removeBtn;
	}




	public void setRemoveBtn(JButton removeBtn) {
		this.removeBtn = removeBtn;
	}




	public JButton getUpdateBtn() {
		return updateBtn;
	}




	public void setUpdateBtn(JButton updateBtn) {
		this.updateBtn = updateBtn;
	}




	public JButton getDeleteBtn() {
		return deleteBtn;
	}




	public void setDeleteBtn(JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
	}




	public JComboBox<String> getShelfLocationCBox() {
		return shelfLocationCBox;
	}




	public void setShelfLocationCBox(JComboBox<String> shelfLocationCBox) {
		this.shelfLocationCBox = shelfLocationCBox;
	}




	public JComboBox<String> getSizeTypeCBox() {
		return sizeTypeCBox;
	}




	public void setSizeTypeCBox(JComboBox<String> sizeTypeCBox) {
		this.sizeTypeCBox = sizeTypeCBox;
	}

	public Shelfs getShelfs() {
		return shelfs;
	}

	public void setShelfs(Shelfs shelfs) {
		this.shelfs = shelfs;
	}


	
	
}
