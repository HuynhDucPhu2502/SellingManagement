package view.products;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.util.stream.IntStream;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import com.toedter.calendar.JDateChooser;

import model.categories.Categories;
import model.categories.Category;
import model.products.Product;
import model.products.Products;
import model.shelf.Shelfs;
import model.suppliers.Suppliers;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

public class ProductManagerView {
	private JPanel panel;
	
	private JTextField productIDTxtField;
	private JTextField productNameTxtField;
	private JTextField purchasePriceTxtField;
	private JTextField sellingPriceTxtField;
	private JTextField stockTxtField;
	
	private JTextField categoryIDTxtField;
	private JTextField categoryNameTxtField;
	private JTextField categoryItemCountTxtField;
	
	private JDateChooser receiveDateDChooser;
	
	private DefaultComboBoxModel<String> categoryNameComboBoxModel;
	private JComboBox<String> categoryNameCBox;
	
	private DefaultComboBoxModel<String> unitComboBoxModel;
	private JComboBox<String> unitCBox;
	
	private DefaultComboBoxModel<String> supplierComBoxModel;
	private JComboBox<String> supplierCBox;
	
	private DefaultComboBoxModel<String> shelfDefaultComboxModel;
	private JComboBox<String> shelfIDCBox;

	
	private JButton addProductBtn;
	private JButton removeProductBtn;
	private JButton updateProductBtn;
	
	private JButton addCategoryBtn;
	private JButton removeCategoryBtn;
	private JButton updateCategoryBtn;
	
	private JTable productTable;
	private Products products;
	
	private JTable categoryTable;
	private Categories categories;
	
	private Suppliers suppliers;

	private Shelfs shelfs;
	
	
	public ProductManagerView() {
		this.panel = new JPanel(new BorderLayout());
		
		this.products = new Products(FetchDataStatus.getProductData());
		this.categories = new Categories(FetchDataStatus.getCategoryData());
		this.suppliers = new Suppliers(FetchDataStatus.getSupplierData());
		this.shelfs= new Shelfs(FetchDataStatus.getShelfData());
		
		panel.add(setupProductPanel(), BorderLayout.CENTER);
		panel.add(setupCategoryPanel(), BorderLayout.EAST);
	}
	
	public JPanel getPanel() {
		return panel;
	}
	
	private JPanel setupProductPanel() {
		JPanel productPanel = LayoutHelper.getBorderLayout();
		productPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách Hàng hóa"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		productPanel.add(setupProductFormPanel(), BorderLayout.NORTH);
		productPanel.add(setupProductTablePanel(), BorderLayout.CENTER);
		productPanel.add(setupProductBtnPanel(), BorderLayout.SOUTH);
		
		return productPanel;
	}
	
	private JPanel setupCategoryPanel() {
		JPanel categoryPanel = LayoutHelper.getBorderLayout();
		categoryPanel.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(ColorHelper.getDarkerPrimaryColor(), 3), "Danh sách loại hàng hóa"),
				BorderFactory.createEmptyBorder(5, 5, 5, 5)
			));
		
		categoryPanel.add(setupCategoryFormPanel(), BorderLayout.NORTH);
		categoryPanel.add(setupCategoryTablePanel(), BorderLayout.CENTER);
		categoryPanel.add(setupCategoryBtnPanel(), BorderLayout.SOUTH);
		
		return categoryPanel;
	}

	private JPanel setupProductFormPanel() {
		JPanel formPanel = LayoutHelper.getGridBagLayout();
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		String[] unit = {"Thùng", "Gói", "Chai", "Túi", "Cái", "Hộp"};
		
		categoryNameComboBoxModel = new DefaultComboBoxModel<String>();
		unitComboBoxModel = new DefaultComboBoxModel<String>(unit);
		supplierComBoxModel = new DefaultComboBoxModel<String>();
		shelfDefaultComboxModel = new DefaultComboBoxModel<String>();
		
		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Tên Hàng:"), formPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, productNameTxtField = new JTextField(), formPanel, gbc);
		LayoutHelper.addItem(2, 0, 0, 0, new JLabel("Mã Hàng:"), formPanel, gbc);
		LayoutHelper.addItem(3, 0, 1, 0, productIDTxtField = new JTextField(), formPanel, gbc);
		
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Giá Nhập:"), formPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, purchasePriceTxtField = new JTextField(), formPanel, gbc);
		LayoutHelper.addItem(2, 1, 0, 0, new JLabel("Giá Bán:"), formPanel, gbc);
		LayoutHelper.addItem(3, 1, 1, 0, sellingPriceTxtField = new JTextField(), formPanel, gbc);
		
		
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("Tên loại hàng:"), formPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, categoryNameCBox = new JComboBox<>(categoryNameComboBoxModel), formPanel, gbc);
		LayoutHelper.addItem(2, 2, 0, 0, new JLabel("Tồn kho:"), formPanel, gbc);
		LayoutHelper.addItem(3, 2, 1, 0, stockTxtField = new JTextField(), formPanel, gbc);
		
		LayoutHelper.addItem(0, 3, 0, 0, new JLabel("Đơn vị:"), formPanel, gbc);
		LayoutHelper.addItem(1, 3, 1, 0, unitCBox = new JComboBox<String>(unitComboBoxModel), formPanel, gbc);
		LayoutHelper.addItem(2, 3, 0, 0, new JLabel("Ngày nhập:"), formPanel, gbc);
		LayoutHelper.addItem(3, 3, 1, 0, receiveDateDChooser = new JDateChooser(), formPanel, gbc);
		
		LayoutHelper.addItem(0, 4, 0, 0, new JLabel("Nhà cung cấp:"), formPanel, gbc);
		LayoutHelper.addItem(1, 4, 1, 0, supplierCBox = new JComboBox<String>(supplierComBoxModel), formPanel, gbc);
		
		LayoutHelper.addItem(0, 5, 0, 0, new JLabel("Id kệ:"), formPanel, gbc);
		LayoutHelper.addItem(1, 5, 1, 0, shelfIDCBox = new JComboBox<String>(shelfDefaultComboxModel), formPanel, gbc);
		
		receiveDateDChooser.setDateFormatString("yyyy-MM-dd");
		
		return formPanel;
	}
	
	private JPanel setupCategoryFormPanel() {
		JPanel formPanel = LayoutHelper.getGridBagLayout();
		
		GridBagConstraints gbc = LayoutHelper.getGbc();
		
		LayoutHelper.addItem(0, 0, 0, 0, new JLabel("Tên loại:"), formPanel, gbc);
		LayoutHelper.addItem(1, 0, 1, 0, categoryNameTxtField = new JTextField(), formPanel, gbc);
		LayoutHelper.addItem(0, 1, 0, 0, new JLabel("Mã loại:"), formPanel, gbc);
		LayoutHelper.addItem(1, 1, 1, 0, categoryIDTxtField = new JTextField(), formPanel, gbc);
		LayoutHelper.addItem(0, 2, 0, 0, new JLabel("Số lượng mặt hàng:"), formPanel, gbc);
		LayoutHelper.addItem(1, 2, 1, 0, categoryItemCountTxtField = new JTextField(), formPanel, gbc);
		
		categoryItemCountTxtField.setText("0");
		categoryItemCountTxtField.setEditable(false);
		
		return formPanel;
	}
	
	
	
	private JPanel setupProductTablePanel() {
		JPanel tablePanel = LayoutHelper.getBorderLayout();
		tablePanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
		
		productTable = new JTable(products);
		
		productTable.getTableHeader().setReorderingAllowed(false);
		productTable.getTableHeader().setResizingAllowed(false);
		
		productTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		productTable.getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int index = productTable.getSelectedRow();
				if (index != -1) showProductTxtField(index);
				else resetProductTxtField();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane(productTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		tablePanel.add(scrollPane);
		
		return tablePanel;
	}
	
	private JPanel setupCategoryTablePanel() {
		JPanel tablePanel = LayoutHelper.getBorderLayout();
		
		categoryTable = new JTable(categories);
		categoryTable.getTableHeader().setReorderingAllowed(false);
		categoryTable.getTableHeader().setResizingAllowed(false);
		
		categoryTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		categoryTable.getSelectionModel().addListSelectionListener(event -> {
			if (!event.getValueIsAdjusting()) {
				int index = categoryTable.getSelectedRow();
				if (index != -1) showCategoryTxtField(index);
				else resetCategoryTxtField();
			}
		});
		
		
		JScrollPane scrollPane = new JScrollPane(categoryTable);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		tablePanel.add(scrollPane);
		
		return tablePanel;
	}
	
	private JPanel setupProductBtnPanel() {
		JPanel btnPanel = new JPanel();
		
		btnPanel.add(addProductBtn = LayoutHelper.setupBtn("Thêm hàng hóa", "src/Images/btn_icon/add_icon.png"));
		btnPanel.add(removeProductBtn = LayoutHelper.setupBtn("Xóa hàng hóa", "src/Images/btn_icon/remove_icon.png"));
		btnPanel.add(updateProductBtn =  LayoutHelper.setupBtn("Cập nhật hàng hóa", "src/Images/btn_icon/update_icon.png"));
		
		
		return btnPanel;
	}
	
	private JPanel setupCategoryBtnPanel() {
		JPanel btnPanel = new JPanel();
		
		btnPanel.add(addCategoryBtn = LayoutHelper.setupBtn("Thêm loại hàng", "src/Images/btn_icon/add_icon.png"));
		btnPanel.add(removeCategoryBtn = LayoutHelper.setupBtn("Xóa loại hàng", "src/Images/btn_icon/remove_icon.png"));
		btnPanel.add(updateCategoryBtn =  LayoutHelper.setupBtn("Cập nhật loại hàng", "src/Images/btn_icon/update_icon.png"));
		
		return btnPanel;
	}
	
	private void showProductTxtField(int index) {
		Product product = products.getProducts().get(index);
		productIDTxtField.setText(String.valueOf(product.getId()));
		productNameTxtField.setText(String.valueOf(product.getName()));
		purchasePriceTxtField.setText(String.valueOf(product.getPurchasePrice()));
		sellingPriceTxtField.setText(String.valueOf(product.getSellingPrice()));
		stockTxtField.setText(String.valueOf(product.getStock()));
		receiveDateDChooser.setDate(product.getRecevieDate());
		
		int optionCategoryName = IntStream.range(0, categoryNameCBox.getItemCount())
				.filter(x -> categoryNameCBox.getItemAt(x).equalsIgnoreCase(product.getCategory().getCategoryName()))
				.findFirst()
				.orElse(-1);
		
		int optionUnit = IntStream.range(0, unitCBox.getItemCount())
				.filter(x -> unitCBox.getItemAt(x).equalsIgnoreCase(product.getUnit()))
				.findFirst()
				.orElse(-1);
		
		if (optionCategoryName == -1)
			throw new IllegalArgumentException("Mã loại hàng hóa không hợp lệ");
		
		if (optionUnit == -1)
			throw new IllegalArgumentException("Đơn vị không hợp lệ");
		
		categoryNameCBox.setSelectedIndex(optionCategoryName);
		unitCBox.setSelectedIndex(optionUnit);
		categoryNameCBox.setEnabled(false);
		productIDTxtField.setEditable(false);
	}
	
	private void showCategoryTxtField(int index) {
		Category category = categories.getCategories().get(index);
		
		categoryIDTxtField.setText(String.valueOf(category.getCategoryID()));
		categoryNameTxtField.setText(category.getCategoryName());
		categoryItemCountTxtField.setText(String.valueOf(category.getItemCount()));
		categoryIDTxtField.setEditable(false);
		
	}
	
	private void resetProductTxtField() {
		productIDTxtField.setText(null);
		productNameTxtField.setText(null);
		stockTxtField.setText(null);
		purchasePriceTxtField.setText(null);
		sellingPriceTxtField.setText(null);
		categoryNameCBox.setSelectedIndex(0);
		unitCBox.setSelectedIndex(0);
		categoryNameCBox.setEnabled(true);
		productIDTxtField.setEditable(true);
		receiveDateDChooser.setDate(null);
	}
	
	private void resetCategoryTxtField() {
		categoryIDTxtField.setText(null);
		categoryNameTxtField.setText(null);
		categoryIDTxtField.setEditable(true);
		categoryItemCountTxtField.setText("0");
	}

	public JTextField getProductIDTxtField() {
		return productIDTxtField;
	}

	public void setProductIDTxtField(JTextField productIDTxtField) {
		this.productIDTxtField = productIDTxtField;
	}

	public JTextField getProductNameTxtField() {
		return productNameTxtField;
	}

	public void setProductNameTxtField(JTextField productNameTxtField) {
		this.productNameTxtField = productNameTxtField;
	}

	public JTextField getPurchasePriceTxtField() {
		return purchasePriceTxtField;
	}

	public void setPurchasePriceTxtField(JTextField purchasePriceTxtField) {
		this.purchasePriceTxtField = purchasePriceTxtField;
	}

	public JTextField getSellingPriceTxtField() {
		return sellingPriceTxtField;
	}

	public void setSellingPriceTxtField(JTextField sellingPriceTxtField) {
		this.sellingPriceTxtField = sellingPriceTxtField;
	}

	public JTextField getStockTxtField() {
		return stockTxtField;
	}

	public void setStockTxtField(JTextField stockTxtField) {
		this.stockTxtField = stockTxtField;
	}

	public JTextField getCategoryIDTxtField() {
		return categoryIDTxtField;
	}

	public void setCategoryIDTxtField(JTextField categoryIDTxtField) {
		this.categoryIDTxtField = categoryIDTxtField;
	}

	public JTextField getCategoryNameTxtField() {
		return categoryNameTxtField;
	}

	public void setCategoryNameTxtField(JTextField categoryNameTxtField) {
		this.categoryNameTxtField = categoryNameTxtField;
	}

	public JTextField getCategoryItemCountTxtField() {
		return categoryItemCountTxtField;
	}

	public void setCategoryItemCountTxtField(JTextField categoryItemCountTxtField) {
		this.categoryItemCountTxtField = categoryItemCountTxtField;
	}

	public JDateChooser getReceiveDateDChooser() {
		return receiveDateDChooser;
	}

	public void setReceiveDateDChooser(JDateChooser receiveDateDChooser) {
		this.receiveDateDChooser = receiveDateDChooser;
	}

	public DefaultComboBoxModel<String> getCategoryNameComboBoxModel() {
		return categoryNameComboBoxModel;
	}

	public void setCategoryNameComboBoxModel(DefaultComboBoxModel<String> categoryNameComboBoxModel) {
		this.categoryNameComboBoxModel = categoryNameComboBoxModel;
	}

	public JComboBox<String> getCategoryNameCBox() {
		return categoryNameCBox;
	}

	public void setCategoryNameCBox(JComboBox<String> categoryNameCBox) {
		this.categoryNameCBox = categoryNameCBox;
	}

	public DefaultComboBoxModel<String> getUnitComboBoxModel() {
		return unitComboBoxModel;
	}

	public void setUnitComboBoxModel(DefaultComboBoxModel<String> unitComboBoxModel) {
		this.unitComboBoxModel = unitComboBoxModel;
	}

	public JComboBox<String> getUnitCBox() {
		return unitCBox;
	}

	public void setUnitCBox(JComboBox<String> unitCBox) {
		this.unitCBox = unitCBox;
	}

	public DefaultComboBoxModel<String> getSupplierComBoxModel() {
		return supplierComBoxModel;
	}

	public void setSupplierComBoxModel(DefaultComboBoxModel<String> supplierComBoxModel) {
		this.supplierComBoxModel = supplierComBoxModel;
	}

	public JComboBox<String> getSupplierCBox() {
		return supplierCBox;
	}

	public void setSupplierCBox(JComboBox<String> supplierCBox) {
		this.supplierCBox = supplierCBox;
	}

	public JButton getAddProductBtn() {
		return addProductBtn;
	}

	public void setAddProductBtn(JButton addProductBtn) {
		this.addProductBtn = addProductBtn;
	}

	public JButton getRemoveProductBtn() {
		return removeProductBtn;
	}

	public void setRemoveProductBtn(JButton removeProductBtn) {
		this.removeProductBtn = removeProductBtn;
	}

	public JButton getUpdateProductBtn() {
		return updateProductBtn;
	}

	public void setUpdateProductBtn(JButton updateProductBtn) {
		this.updateProductBtn = updateProductBtn;
	}

	public JButton getAddCategoryBtn() {
		return addCategoryBtn;
	}

	public void setAddCategoryBtn(JButton addCategoryBtn) {
		this.addCategoryBtn = addCategoryBtn;
	}

	public JButton getRemoveCategoryBtn() {
		return removeCategoryBtn;
	}

	public void setRemoveCategoryBtn(JButton removeCategoryBtn) {
		this.removeCategoryBtn = removeCategoryBtn;
	}
	
	public JButton getUpdateCategoryBtn() {
		return updateCategoryBtn;
	}

	public void setUpdateCategoryBtn(JButton updateCategoryBtn) {
		this.updateCategoryBtn = updateCategoryBtn;
	}

	public JTable getProductTable() {
		return productTable;
	}

	public void setProductTable(JTable productTable) {
		this.productTable = productTable;
	}

	public Products getProducts() {
		return products;
	}

	public void setProducts(Products products) {
		this.products = products;
	}

	public JTable getCategoryTable() {
		return categoryTable;
	}

	public void setCategoryTable(JTable categoryTable) {
		this.categoryTable = categoryTable;
	}

	public Categories getCategories() {
		return categories;
	}

	public void setCategories(Categories categories) {
		this.categories = categories;
	}

	public Suppliers getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(Suppliers suppliers) {
		this.suppliers = suppliers;
	}

	public void setPanel(JPanel panel) {
		this.panel = panel;
	}

	public Shelfs getShelfs() {
		return shelfs;
	}

	public void setShelfs(Shelfs shelfs) {
		this.shelfs = shelfs;
	}

	public DefaultComboBoxModel<String> getShelfDefaultComboxModel() {
		return shelfDefaultComboxModel;
	}

	public void setShelfDefaultComboxModel(DefaultComboBoxModel<String> shelfDefaultComboxModel) {
		this.shelfDefaultComboxModel = shelfDefaultComboxModel;
	}

	public JComboBox<String> getShelfIDCBox() {
		return shelfIDCBox;
	}

	public void setShelfIDCBox(JComboBox<String> shelfIDCBox) {
		this.shelfIDCBox = shelfIDCBox;
	}
	
	
}
