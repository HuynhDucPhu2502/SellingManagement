package view.file;



import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import util.ColorHelper;
import util.LayoutHelper;


public class FileManagerView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel pnlView;
	private JPanel pnlCenter;
	private JPanel pnlWest;

	private JList<String> listOption;

	private JButton btnLuu;

	private CardLayout cardLayout;

	private JTable tblPrd;

	private JTable tblCategory;

	private JTable tblSupplier;

	private JTable tblCus;

	private JTable tblEmployee;

	

	private DefaultTableModel dfTblPrd;

	private DefaultTableModel dfTblCategory;

	private DefaultTableModel dfTblSupplier;

	private DefaultTableModel dfTblCus;

	private DefaultTableModel dfTblEmp;

	

	private JButton btnXuatPDF;

	private DefaultTableModel dfTbInvoice;

	private JTable tbInvoice;
	
	public FileManagerView() {
	
		pnlView = new JPanel(new BorderLayout());
		pnlView.add(setWestView(), BorderLayout.WEST);
		pnlView.add(setCenterView(), BorderLayout.CENTER);
		
	}
	
	private JPanel setWestView() {
		pnlWest = LayoutHelper.getGridBagLayout();
		GridBagConstraints gbc = LayoutHelper.getGbc();
        
        gbc.gridy++;
        String[] listObject = {"Hàng hóa", "Loại hàng hóa", "Nhà cung cấp", "Khách hàng", "Nhân viên", "Hóa đơn"};
        listOption = new JList<String>(listObject);
        listOption.setSelectedIndex(0);
        listOption.setPreferredSize(new Dimension(200, listOption.getPreferredSize().height));
        JScrollPane scrollPane = new JScrollPane(listOption);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        
        LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Danh sách dữ liệu"), pnlWest, gbc);
        LayoutHelper.addItem(0, 1, 0, 0, scrollPane, pnlWest, gbc);
        LayoutHelper.addItem(0, 2, 0, 0, btnLuu = LayoutHelper.setupBtn("Xuất File Excel", "src/images/btn_icon/print_icon.png"), pnlWest, gbc);
        LayoutHelper.addItem(0, 3, 0, 1
        		, new JLabel(), pnlWest, gbc);
        
		return pnlWest;
	}
	
	
	
	private JPanel setCenterView() {
		cardLayout = new CardLayout();
		pnlCenter = new JPanel(cardLayout);
		
		pnlCenter.add(tableProduct(), "Hàng hóa");
		pnlCenter.add(tableEmployee(), "Nhân viên");
		pnlCenter.add(tableCategory(), "Loại hàng hóa");
		pnlCenter.add(tableSupplier(), "Nhà cung cấp");
		pnlCenter.add(tableCustomer(), "Khách hàng");
		pnlCenter.add(tableInvoice(), "Hóa đơn");
		
		return pnlCenter;
	}
	
	private JPanel tableProduct() {
		JPanel pnlProduct = new JPanel(new BorderLayout());
		pnlProduct.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin hàng hóa"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		String[] columns = {"Tên sản phẩm", "Mã sản phẩm", "Giá Nhập", "Giá Bán", "Mã loại", "Số lượng", "Đơn vị", "Ngày Nhập", "Nhà cung cấp"};
		dfTblPrd = new DefaultTableModel(columns, 0);
		tblPrd = new JTable(dfTblPrd);
		pnlProduct.add(new JScrollPane(tblPrd));
		
		return pnlProduct;
	}
	
	private JPanel tableCategory() {
		JPanel pnlCategory = new JPanel(new BorderLayout());
		pnlCategory.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin loại hàng hóa"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		String[] columns = {"Mã loại", "Tên loại", "Số mặt hàng"};
		dfTblCategory = new DefaultTableModel(columns, 0);
		tblCategory = new JTable(dfTblCategory);
		pnlCategory.add(new JScrollPane(tblCategory));
		return pnlCategory;
	}
	
	private JPanel tableSupplier() {
		JPanel pnlSupplier = new JPanel(new BorderLayout());
		pnlSupplier.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin nhà cung cấp"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		String[] columns = {"Mã NCC", "Tên NCC", "Địa chỉ", "Số điện thoại", "Địa chỉ"};
		dfTblSupplier = new DefaultTableModel(columns, 0);
		tblSupplier = new JTable(dfTblSupplier);
		pnlSupplier.add(new JScrollPane(tblSupplier));
		
		return pnlSupplier;
	}
	
	private JPanel tableCustomer() {
		JPanel pnlCus = new JPanel(new BorderLayout());
		pnlCus.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin nhà cung cấp"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		String[] columns = {"Mã khách hàng", "Họ", "Tên", "Địa chỉ", "Số điện thoại", "Loại khách hàng"};
		dfTblCus = new DefaultTableModel(columns, 0);
		tblCus = new JTable(dfTblCus);
		pnlCus.add(new JScrollPane(tblCus));
		
		return pnlCus;
	}
	
	private JPanel tableEmployee() {
		JPanel pnlEmployee = new JPanel(new BorderLayout());
		pnlEmployee.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin nhân viên"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		String[] columns = {"Mã nhân viên", "Họ", "Tên", "Địa chỉ", "Số điện thoại",
				"Email", "Chức vụ", "Ngày sinh", "Giới tính", "Hệ số lương"};
		dfTblEmp = new DefaultTableModel(columns, 0);
		tblEmployee = new JTable(dfTblEmp);
		pnlEmployee.add(new JScrollPane(tblEmployee));
		
		return pnlEmployee;
	}
	
	private JPanel tableInvoice() {
		JPanel pnlInvoice = new JPanel(new BorderLayout());
		pnlInvoice.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Thông tin hóa đơn"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		String[] columns = {"Mã hóa đơn", "Mã NV", "Tên NV", "Mã KH", "Tên KH", "Loại KH", "Ngày lập", "Tổng tiền"};
		
		dfTbInvoice = new DefaultTableModel(columns, 0);
		tbInvoice = new JTable(dfTbInvoice);
		
		pnlInvoice.add(new JScrollPane(tbInvoice));
		
		return pnlInvoice;
		
	}

	public JPanel getPnlView() {
		return pnlView;
	}

	public void setPnlView(JPanel pnlView) {
		this.pnlView = pnlView;
	}

	public JPanel getPnlCenter() {
		return pnlCenter;
	}

	public void setPnlCenter(JPanel pnlCenter) {
		this.pnlCenter = pnlCenter;
	}

	public JPanel getPnlWest() {
		return pnlWest;
	}

	public void setPnlWest(JPanel pnlWest) {
		this.pnlWest = pnlWest;
	}

	public JList<String> getListOption() {
		return listOption;
	}

	public void setListOption(JList<String> listOption) {
		this.listOption = listOption;
	}

	public JButton getBtnLuu() {
		return btnLuu;
	}

	public void setBtnLuu(JButton btnLuu) {
		this.btnLuu = btnLuu;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JTable getTblPrd() {
		return tblPrd;
	}

	public void setTblPrd(JTable tblPrd) {
		this.tblPrd = tblPrd;
	}

	public JTable getTblCategory() {
		return tblCategory;
	}

	public void setTblCategory(JTable tblCategory) {
		this.tblCategory = tblCategory;
	}

	public JTable getTblSupplier() {
		return tblSupplier;
	}

	public void setTblSupplier(JTable tblSupplier) {
		this.tblSupplier = tblSupplier;
	}

	public JTable getTblCus() {
		return tblCus;
	}

	public void setTblCus(JTable tblCus) {
		this.tblCus = tblCus;
	}

	public JTable getTblEmployee() {
		return tblEmployee;
	}

	public void setTblEmployee(JTable tblEmployee) {
		this.tblEmployee = tblEmployee;
	}

	public DefaultTableModel getDfTblPrd() {
		return dfTblPrd;
	}

	public void setDfTblPrd(DefaultTableModel dfTblPrd) {
		this.dfTblPrd = dfTblPrd;
	}

	public DefaultTableModel getDfTblCategory() {
		return dfTblCategory;
	}

	public void setDfTblCategory(DefaultTableModel dfTblCategory) {
		this.dfTblCategory = dfTblCategory;
	}

	public DefaultTableModel getDfTblSupplier() {
		return dfTblSupplier;
	}

	public void setDfTblSupplier(DefaultTableModel dfTblSupplier) {
		this.dfTblSupplier = dfTblSupplier;
	}

	public DefaultTableModel getDfTblCus() {
		return dfTblCus;
	}

	public void setDfTblCus(DefaultTableModel dfTblCus) {
		this.dfTblCus = dfTblCus;
	}

	public DefaultTableModel getDfTblEmp() {
		return dfTblEmp;
	}

	public void setDfTblEmp(DefaultTableModel dfTblEmp) {
		this.dfTblEmp = dfTblEmp;
	}

	public JButton getBtnXuatPDF() {
		return btnXuatPDF;
	}

	public void setBtnXuatPDF(JButton btnXuatPDF) {
		this.btnXuatPDF = btnXuatPDF;
	}

	public DefaultTableModel getDfTbInvoice() {
		return dfTbInvoice;
	}

	public void setDfTbInvoice(DefaultTableModel dfTbInvoice) {
		this.dfTbInvoice = dfTbInvoice;
	}

	public JTable getTbInvoice() {
		return tbInvoice;
	}

	public void setTbInvoice(JTable tbInvoice) {
		this.tbInvoice = tbInvoice;
	}
	
	
}
