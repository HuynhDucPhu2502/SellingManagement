package view.employees;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import model.employee.EmployeePosition;
import util.ColorHelper;
import util.LayoutHelper;

public class EmployeeSearchView extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Container panel;
	private JPanel searchPanel;
	private JPanel southPanel;
	private JPanel tablePanel;

	private JTextField searchByName;

	private JTextField searchById;

	private JComboBox<String> searchByPosition;

	private JButton btn_searchByName;

	private JButton btn_searchById;

	private JButton btn_searchByPosition;

	private JButton resetSearchBtn;

	private DefaultTableModel dfTbEmp;

	private JTable tBEmp;

	public EmployeeSearchView() {
		
		panel = new JPanel(new BorderLayout());
		
		panel.add(setupSearchPanel(), BorderLayout.NORTH);
		panel.add(setupTablePanel(), BorderLayout.CENTER);
		panel.add(setupSouthPanel(), BorderLayout.SOUTH);
	}
	
	public static void main(String[] args) {
		new EmployeeSearchView().setVisible(true);
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
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("Loại nhân viên:"), searchPanel, gbc);
		
		LayoutHelper.addItem(1, 0, 1, 0, searchByName = new JTextField(), searchPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, searchById = new JTextField(), searchPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, searchByPosition = new JComboBox<String>(), searchPanel, gbc);
		searchByPosition.addItem(EmployeePosition.MANAGER.toString());
		searchByPosition.addItem(EmployeePosition.SALE_EMPLOYEE.toString());
		searchByPosition.setSelectedIndex(0);
		
		LayoutHelper.addItem(2, 0, 0, 0, btn_searchByName = new JButton("Tìm kiếm theo tên"), searchPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, btn_searchById = new JButton("Tìm kiếm theo mã"), searchPanel, gbc);
		LayoutHelper.addItem(2, 2, 0, 0, btn_searchByPosition = new JButton("Tìm kiếm theo vị trí"), searchPanel, gbc);
		
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
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin nhân viên"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		String[] columns = {"Mã nhân viên", "Họ", "Tên", "Địa chỉ", "Số điện thoại", "Email",
				"Chức vụ", "Ngày sinh", "Giới tính", "Hệ số lương"};
		dfTbEmp = new DefaultTableModel(columns, 0);
		tBEmp = new JTable(dfTbEmp);
		tablePanel.add(new JScrollPane(tBEmp), BorderLayout.CENTER);
		
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

	public JComboBox<String> getSearchByPosition() {
		return searchByPosition;
	}

	public void setSearchByPosition(JComboBox<String> searchByPosition) {
		this.searchByPosition = searchByPosition;
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

	public JButton getBtn_searchByPosition() {
		return btn_searchByPosition;
	}

	public void setBtn_searchByPosition(JButton btn_searchByPosition) {
		this.btn_searchByPosition = btn_searchByPosition;
	}

	public JButton getResetSearchBtn() {
		return resetSearchBtn;
	}

	public void setResetSearchBtn(JButton resetSearchBtn) {
		this.resetSearchBtn = resetSearchBtn;
	}

	public DefaultTableModel getDfTbEmp() {
		return dfTbEmp;
	}

	public void setDfTbEmp(DefaultTableModel dfTbEmp) {
		this.dfTbEmp = dfTbEmp;
	}

	public JTable gettBEmp() {
		return tBEmp;
	}

	public void settBEmp(JTable tBEmp) {
		this.tBEmp = tBEmp;
	}
	
	
}
