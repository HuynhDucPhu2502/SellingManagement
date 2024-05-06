package view.customers;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.customers.CustomerType;
import util.ColorHelper;
import util.LayoutHelper;

public class CustomerSearchView {
private JPanel searchPanel;
private JTextField searchByName;
private JTextField searchById;
private JComboBox<String> searchByType;
private JButton btn_searchByName;
private JButton btn_searchById;
private JButton btn_searchByType;
private JPanel southPanel;
private JButton resetSearchBtn;
private JPanel tablePanel;
private DefaultTableModel dfTbCus;
private JTable tBCus;
private Container panel;

public CustomerSearchView() {
		
		panel = new JPanel(new BorderLayout());
		
		panel.add(setupSearchPanel(), BorderLayout.NORTH);
		panel.add(setupTablePanel(), BorderLayout.CENTER);
		panel.add(setupSouthPanel(), BorderLayout.SOUTH);
	}
	
	
	private JPanel setupSearchPanel() {
		searchPanel = LayoutHelper.getGridBagLayout();
		searchPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Tìm kiếm"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Tên:"), searchPanel, gbc);
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Mã:"), searchPanel, gbc);
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("Loại khách hàng:"), searchPanel, gbc);
		
		LayoutHelper.addItem(1, 0, 1, 0, searchByName = new JTextField(), searchPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, searchById = new JTextField(), searchPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, searchByType = new JComboBox<String>(), searchPanel, gbc);
		searchByType.addItem(CustomerType.MEMBER.toString());
		searchByType.addItem(CustomerType.VIP.toString());
		searchByType.setSelectedIndex(0);
		
		LayoutHelper.addItem(2, 0, 0, 0, btn_searchByName = new JButton("Tìm kiếm theo tên"), searchPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, btn_searchById = new JButton("Tìm kiếm theo mã"), searchPanel, gbc);
		LayoutHelper.addItem(2, 2, 0, 0, btn_searchByType = new JButton("Tìm kiếm theo loại khách hàng"), searchPanel, gbc);
		
		return searchPanel;
		
	}
	
	private JPanel setupSouthPanel() {
		southPanel = LayoutHelper.getGridBagLayout();
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		LayoutHelper.addItem(0, 0, 1, 0, resetSearchBtn = new JButton("Làm mới tìm kiếm"), southPanel, gbc);
		
		return southPanel;
	}
	
	private JPanel setupTablePanel() {
		tablePanel = new JPanel(new BorderLayout());
		tablePanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Kết quả tìm kiếm"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		String[] columns = {"Mã KH","Họ","Tên","Địa chỉ","Số điện thoại","Loại Khách Hàng"};
		dfTbCus = new DefaultTableModel(columns, 0);
		tBCus = new JTable(dfTbCus);
		tablePanel.add(new JScrollPane(tBCus), BorderLayout.CENTER);
		
		return tablePanel;
	}

	public Container getPanel() {
		return panel;
	}

	public void setPanel(Container panel) {
		this.panel = panel;
	}

	public JPanel getSearchPanel() {
		return searchPanel;
	}

	public void setSearchPanel(JPanel searchPanel) {
		this.searchPanel = searchPanel;
	}

	public JPanel getSouthPanel() {
		return southPanel;
	}

	public void setSouthPanel(JPanel southPanel) {
		this.southPanel = southPanel;
	}

	public JPanel getTablePanel() {
		return tablePanel;
	}

	public void setTablePanel(JPanel tablePanel) {
		this.tablePanel = tablePanel;
	}

	public JTextField getSearchByName() {
		return searchByName;
	}

	public void setSearchByName(JTextField searchByName) {
		this.searchByName = searchByName;
	}

	public JTextField getSearchById() {
		return searchById;
	}

	public void setSearchById(JTextField searchById) {
		this.searchById = searchById;
	}

	
	

	public JComboBox<String> getSearchByType() {
		return searchByType;
	}

	public void setSearchByType(JComboBox<String> searchByType) {
		this.searchByType = searchByType;
	}

	public JButton getBtn_searchByName() {
		return btn_searchByName;
	}

	public void setBtn_searchByName(JButton btn_searchByName) {
		this.btn_searchByName = btn_searchByName;
	}

	public JButton getBtn_searchById() {
		return btn_searchById;
	}

	public void setBtn_searchById(JButton btn_searchById) {
		this.btn_searchById = btn_searchById;
	}

	

	public JButton getBtn_searchByType() {
		return btn_searchByType;
	}

	public void setBtn_searchByType(JButton btn_searchByType) {
		this.btn_searchByType = btn_searchByType;
	}

	public JButton getResetSearchBtn() {
		return resetSearchBtn;
	}

	public void setResetSearchBtn(JButton resetSearchBtn) {
		this.resetSearchBtn = resetSearchBtn;
	}

	
	public DefaultTableModel getDfTbCus() {
		return dfTbCus;
	}

	public void setDfTbCus(DefaultTableModel dfTbCus) {
		this.dfTbCus = dfTbCus;
	}

	public JTable gettBCus() {
		return tBCus;
	}

	public void settBCus(JTable tBCus) {
		this.tBCus = tBCus;
	}

	
}
