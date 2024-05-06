package view.base;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.Timer;

import controller.AccountManagerController;
import controller.CustomerManagerController;
import controller.CustomerSearchController;
import controller.EmployeeManagerController;
import controller.EmployeeSearchController;
import controller.FileManagerController;
import controller.InvoiceManagerController;
import controller.ProductManagerController;
import controller.ProductSearchController;
import controller.ShelfManagerController;
import controller.StatisticalController;
import controller.SupplierController;
import controller.SupplierSearchController;
import controller.MainFrameController;
import controller.NewInvoiceController;

import model.accounts.Account;
import model.employee.EmployeePosition;
import util.ColorHelper;
import util.FetchDataStatus;
import util.LayoutHelper;

import view.customers.CustomerManagerView;
import view.customers.CustomerSearchView;
import view.employees.EmployeeManagerView;
import view.employees.EmployeeSearchView;
import view.file.FileManagerView;
import view.invoices.InvoiceManagerView;
import view.invoices.NewInvoiceView;
import view.products.ProductManagerView;
import view.products.ProductSearchView;
import view.shelfs.ShelfManagerView;
import view.statistical.StatisticalView;
import view.suppliers.SupplierManagerView;
import view.suppliers.SupplierSearchView;
import view.accounts.AccountManagerView;

public class MainFrame extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -629643563332064457L;
	
	// Define the account currently logged into the system.
	private static Account account;
	private int accountPermission;
	private JButton accountInfoBtn;

	private JMenu invoiceMenu;
	private JMenu productMenu;
	private JMenu customerMenu;
	private JMenu employeeMenu;
	private JMenu analysisMenu;
	private JMenu supplierMenu;
	private JMenu accountMenu;
	
	private JMenuItem productManagerMenuItem;
	private JMenuItem productSearchMenuItem;
	private JMenuItem customerManagerMenuItem;
	private JMenuItem employeeManagerMenuItem;
	private JMenuItem supplierManagerMenuItem;
	private JMenuItem employeeSearhMenuItem;
	private JMenuItem fileManagerMenuItem;
	private JMenuItem newInvoiceMenuItem;
	private JMenuItem invoiceManagerMenuItem;
	private JMenuItem statisticalManagerMenuItem;
	private JMenuItem accountManagerMenuItem;
	private JMenuItem accountInfoMenuItem;
	private JMenuItem logoutAccountMenuItem;
	private JMenuItem supplierSearchMenuItem;
	private JMenuItem customerSearchMenuItem;
	private JMenuItem shelfManagerMenuItem;
	
	private CardLayout cardLayout;
	private JPanel cardPanel;
	
	private MainFrameController mainFrameController;
	
	private JPanel topPanel;
	
	// show which panel of currently display
	private JLabel titleLabel;
	
	// Products
	private ProductManagerView productManagerView;
	private ProductManagerController productManagerController;
	
	// Products Search
	private ProductSearchView productSearchView;
	private ProductSearchController productSearchController;
	
	// Suppliers
	private SupplierManagerView supplierManagerView;
	private SupplierController supplierController;
	
	// Suppliers Search
	private SupplierSearchView supplierSearchView;
	private SupplierSearchController supplierSearchController;
	
	
	// Employees:
	private EmployeeManagerView employeeManagerView;
	private EmployeeManagerController employeeManagerController;
	
	// Statistical
	private StatisticalView statisticalManagerView;
	private StatisticalController statisticalController;
	
	// Employee Search
	private EmployeeSearchView employeeSearchManagerView;
	private EmployeeSearchController employeeSearchController;
	
	// Customers:
	private CustomerManagerView customerManagerView; 
	private CustomerManagerController customerManagerController;
	
	// Customers Search:
	private CustomerSearchView customerSearchView;
	private CustomerSearchController customerSearchController;
	
	// File:
	private FileManagerView fileManagerView;
	private FileManagerController fileManagerController;
	
	// Invoice:
	private NewInvoiceView newInvoiceView;
	private NewInvoiceController newInvoiceController;
	private InvoiceManagerView invoiceMangerView;
	private InvoiceManagerController invoiceManagerController;

	// Account
	private AccountManagerView accountManagerView;
	private AccountManagerController accountManagerController;

	// Shelf
	private ShelfManagerView shelfManagerView;
	private ShelfManagerController shelfManagerController;

	
	public MainFrame(Account account) throws HeadlessException {
		MainFrame.account = account;
		setTitle("Quản lý bán hàng cửa hàng tiện lợi");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(1500, 800);
		setIconImage(LayoutHelper.getLogo());
		
		if (account.getEmployee().getPosition().equals(EmployeePosition.MANAGER))
			accountPermission = 1;
		else accountPermission = 0;
			
		FetchDataStatus.loadAll();
		initiateAll();
		
		setJMenuBar(setupMenuBar());
		add(setupCardPanel(), BorderLayout.CENTER);
		add(setupTopPanel(), BorderLayout.NORTH);
		
		setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
		
		setVisible(true);
	}
	
	private void initiateAll() {
		// Entities:
	
		// Views:
		productManagerView = new ProductManagerView();
		productSearchView = new ProductSearchView();
		supplierManagerView = new SupplierManagerView();
		employeeManagerView = new EmployeeManagerView();
		customerManagerView = new CustomerManagerView();
		employeeSearchManagerView = new EmployeeSearchView();
		fileManagerView = new FileManagerView();
		newInvoiceView = new NewInvoiceView(account);
		invoiceMangerView = new InvoiceManagerView(account);
		accountManagerView = new AccountManagerView();
		statisticalManagerView = new StatisticalView();
		supplierSearchView = new SupplierSearchView();
		customerSearchView = new CustomerSearchView();
		shelfManagerView= new ShelfManagerView();

		
		// Controllers:
		mainFrameController = new MainFrameController(this);
		productManagerController = new ProductManagerController(productManagerView, productSearchView);
		productSearchController = new ProductSearchController(productSearchView);
		supplierController = new SupplierController(supplierManagerView, productManagerView);
		employeeSearchController = new EmployeeSearchController(employeeSearchManagerView); 
		employeeManagerController = new EmployeeManagerController(employeeManagerView);
		fileManagerController = new FileManagerController(fileManagerView);
		newInvoiceController = new NewInvoiceController(newInvoiceView, account);
		invoiceManagerController = new InvoiceManagerController(invoiceMangerView);
		customerManagerController = new CustomerManagerController(customerManagerView);
		accountManagerController = new AccountManagerController(accountManagerView);
		supplierSearchController = new SupplierSearchController(supplierSearchView);
		customerSearchController = new CustomerSearchController(customerSearchView);
		statisticalController = new StatisticalController(statisticalManagerView);
		shelfManagerController= new ShelfManagerController(shelfManagerView);
	}
	
	// Define Layout
	private JPanel setupCardPanel() {
		cardLayout = new CardLayout();
		cardPanel = new JPanel(cardLayout);
		cardPanel.setBorder(BorderFactory.createCompoundBorder(
					BorderFactory.createLineBorder(Color.BLACK, 1),
					BorderFactory.createEmptyBorder(10, 10, 10, 10)
				));
		
		// Add layout here:
		cardPanel.add(newInvoiceView.getPanel(), "Tạo hóa đơn");
		cardPanel.add(productManagerView.getPanel(), "Quản lý hàng hóa");
		cardPanel.add(productSearchView.getPanel(), "Tìm kiếm hàng hóa");
		cardPanel.add(supplierManagerView.getPanel(), "Quản lý nhà cung cấp");
		cardPanel.add(employeeManagerView.getPnlView(), "Quản lý nhân viên");
		cardPanel.add(customerManagerView.getPanel(), "Quản lý khách hàng");
		cardPanel.add(employeeSearchManagerView.getPanel(), "Tìm kiếm nhân viên");
		cardPanel.add(fileManagerView.getPnlView(), "Xuất file");
		cardPanel.add(invoiceMangerView.getPanel(), "Quản lý hóa đơn");
		cardPanel.add(accountManagerView.getPnlView(), "Quản lí tài khoản");
		cardPanel.add(statisticalManagerView.getPnlView(), "Thống kế doanh thu");
		cardPanel.add(supplierSearchView.getPanel(), "Tìm kiếm nhà cung cấp");
		cardPanel.add(customerSearchView.getPanel(), "Tìm kiếm khách hàng");
		cardPanel.add(shelfManagerView.getPanel(), "Quản lý kệ");
		
		return cardPanel;
	}
	
	private JMenuBar setupMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		// Create Menu
		invoiceMenu = new JMenu("Bán hàng");
		productMenu = new JMenu("Hàng hóa");
		supplierMenu = new JMenu("Nhà Cung Cấp");
		customerMenu = new JMenu("Khách hàng");
		employeeMenu = new JMenu("Nhân viên");
		accountMenu = new JMenu(account.getUsername());
		
		// Set Icon Menu
		invoiceMenu.setIcon(new ImageIcon("src/Images/menu_icon/InvoiceMenu.png"));
		productMenu.setIcon(new ImageIcon("src/Images/menu_icon/ProductMenu.png"));
		supplierMenu.setIcon(new ImageIcon("src/Images/menu_icon/SupplierMenu.png"));
		customerMenu.setIcon(new ImageIcon("src/Images/menu_icon/CustomerMenu.png"));
		employeeMenu.setIcon(new ImageIcon("src/Images/menu_icon/EmployeeMenu.png"));
		accountMenu.setIcon(new ImageIcon("src/Images/auth_icon/account_icon.png"));
		
		// Register action event and add menu items
		setupProductMenu();
		setupCustomerMenu();
		setupEmployeeMenu();
		setupSupplierMenu();
		setupInvoiceMenu();
		setupAccountMenu();
		
		menuBar.add(invoiceMenu);
		menuBar.add(productMenu);
		
		if (accountPermission == 1) menuBar.add(supplierMenu);
		
		menuBar.add(customerMenu);
		
		if (accountPermission == 1) menuBar.add(employeeMenu);

		menuBar.add(Box.createHorizontalGlue());
		menuBar.add(accountMenu);
		
		return menuBar;
	}
	
	
	private void setupProductMenu() {
		productManagerMenuItem = new JMenuItem("Quản lý hàng hóa");
		productSearchMenuItem = new JMenuItem("Tìm kiếm hàng hóa");
		shelfManagerMenuItem= new JMenuItem("Quản lý kệ");
		
		productMenu.add(productManagerMenuItem);
		productMenu.add(productSearchMenuItem);
		productMenu.add(new JSeparator());
		productMenu.add(shelfManagerMenuItem);
		
		productManagerMenuItem.addActionListener(mainFrameController);
		productSearchMenuItem.addActionListener(mainFrameController);
		shelfManagerMenuItem.addActionListener(mainFrameController);
	}
	
	private void setupCustomerMenu()  {
		customerManagerMenuItem = new JMenuItem("Quản lý khách hàng");
		customerSearchMenuItem = new JMenuItem("Tìm kiếm khách hàng");
		
		customerMenu.add(customerManagerMenuItem);
		customerMenu.add(customerSearchMenuItem);
		
		customerManagerMenuItem.addActionListener(mainFrameController);
		customerSearchMenuItem.addActionListener(mainFrameController);
	}
	
	private void setupEmployeeMenu() {
		employeeManagerMenuItem = new JMenuItem("Quản lý nhân viên");
		employeeSearhMenuItem = new JMenuItem("Tìm kiếm nhân viên");
		fileManagerMenuItem = new JMenuItem("Xuất file dữ liệu");
		accountManagerMenuItem = new JMenuItem("Quản lí tài khoản");
		statisticalManagerMenuItem = new JMenuItem("Thống kế doanh thu");
		
		employeeMenu.add(employeeManagerMenuItem);
		employeeMenu.add(employeeSearhMenuItem);
		employeeMenu.add(new JSeparator());
		employeeMenu.add(fileManagerMenuItem);
		employeeMenu.add(new JSeparator());
		employeeMenu.add(accountManagerMenuItem);
		employeeMenu.add(new JSeparator());
		employeeMenu.add(statisticalManagerMenuItem);
		
		employeeManagerMenuItem.addActionListener(mainFrameController);
		employeeSearhMenuItem.addActionListener(mainFrameController);
		fileManagerMenuItem.addActionListener(mainFrameController);
		accountManagerMenuItem.addActionListener(mainFrameController);
		statisticalManagerMenuItem.addActionListener(mainFrameController);
	}
	
	private void setupSupplierMenu() {
		supplierManagerMenuItem = new JMenuItem("Quản lý NCC");
		supplierSearchMenuItem = new JMenuItem("Tìm kiếm NCC");
		
		supplierMenu.add(supplierManagerMenuItem);
		supplierMenu.add(supplierSearchMenuItem);
		
		
		supplierManagerMenuItem.addActionListener(mainFrameController);
		supplierSearchMenuItem.addActionListener(mainFrameController);
	}
	
	private void setupInvoiceMenu() {
		newInvoiceMenuItem = new JMenuItem("Tạo hóa đơn");
		invoiceManagerMenuItem = new JMenuItem("Quản lý hóa đơn");
		
		invoiceMenu.add(newInvoiceMenuItem);
		invoiceMenu.add(invoiceManagerMenuItem);
		
		newInvoiceMenuItem.addActionListener(mainFrameController);
		invoiceManagerMenuItem.addActionListener(mainFrameController);
	}
	
	private void setupAccountMenu() {
		accountMenu.setOpaque(true);
		accountMenu.setBackground(ColorHelper.getPrimaryColor());
		accountMenu.setForeground(Color.WHITE);
		
		accountInfoMenuItem = new JMenuItem("Thông tin tài khoản");
		logoutAccountMenuItem = new JMenuItem("Đăng xuất");
		
		accountMenu.add(accountInfoMenuItem);
		accountMenu.add(logoutAccountMenuItem);
		
		accountInfoMenuItem.addActionListener(mainFrameController);
		logoutAccountMenuItem.addActionListener(mainFrameController);
	}
	
	private JPanel setupTopPanel() {
	    topPanel = new JPanel();
	    topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.X_AXIS));
	    
	    JLabel clockLabel = new JLabel();
	    clockLabel.setHorizontalAlignment(JLabel.LEFT);  
	    clockLabel.setFont(new Font("Serif", Font.BOLD, 16));
	    clockLabel.setForeground(Color.BLUE);
	    clockLabel.setIcon(new ImageIcon("src/Images/auth_icon/calendar_icon.png"));

	    Timer timer = new Timer(1000, e -> {
	        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss, dd/MM/yyyy");
	        clockLabel.setText(sdf.format(new Date()));
	    });
	    timer.start();
	    
//	    accountInfoBtn = LayoutHelper.setupBtn(account.getUsername(), "src/Images/auth_icon/account_icon.png");
//	    accountInfoBtn.setHorizontalAlignment(JButton.RIGHT); 
//	    accountInfoBtn.setHorizontalTextPosition(JButton.LEFT);
//	    accountInfoBtn.addActionListener(mainFrameController);

	    topPanel.add(clockLabel);
	    topPanel.add(Box.createHorizontalGlue()); 
	    topPanel.add(titleLabel = LayoutHelper.getTitle("Tạo hóa đơn"));
	    topPanel.add(Box.createHorizontalGlue()); 
	    topPanel.add(new JLabel());
	    
	    
	    titleLabel.setForeground(ColorHelper.getDarkerPrimaryColor());
	    
	    topPanel.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 5));
	    topPanel.setPreferredSize(new Dimension(0, 50));

	    
	    return topPanel;
	}
	
	public void updateTitle(String newTitle) {
	    titleLabel.setText(newTitle);
	}


	public JLabel getTitleLabel() {
		return titleLabel;
	}

	public void setTitleLabel(JLabel titleLabel) {
		this.titleLabel = titleLabel;
	}

	public JMenu getInvoiceMenu() {
		return invoiceMenu;
	}

	public void setInvoiceMenu(JMenu invoiceMenu) {
		this.invoiceMenu = invoiceMenu;
	}

	public JMenu getProductMenu() {
		return productMenu;
	}

	public void setProductMenu(JMenu productMenu) {
		this.productMenu = productMenu;
	}

	public JMenu getCustomerMenu() {
		return customerMenu;
	}

	public void setCustomerMenu(JMenu customerMenu) {
		this.customerMenu = customerMenu;
	}

	public JMenu getEmployeeMenu() {
		return employeeMenu;
	}

	public void setEmployeeMenu(JMenu employeeMenu) {
		this.employeeMenu = employeeMenu;
	}

	public JMenu getAnalysisMenu() {
		return analysisMenu;
	}

	public void setAnalysisMenu(JMenu analysisMenu) {
		this.analysisMenu = analysisMenu;
	}

	public CardLayout getCardLayout() {
		return cardLayout;
	}

	public void setCardLayout(CardLayout cardLayout) {
		this.cardLayout = cardLayout;
	}

	public JPanel getCardPanel() {
		return cardPanel;
	}

	public void setCardPanel(JPanel cardPanel) {
		this.cardPanel = cardPanel;
	}

	public MainFrameController getViewController() {
		return mainFrameController;
	}

	public void setViewController(MainFrameController viewController) {
		this.mainFrameController = viewController;
	}

	public ProductManagerView getProductManagerView() {
		return productManagerView;
	}

	public void setProductManagerView(ProductManagerView productManagerView) {
		this.productManagerView = productManagerView;
	}

	public ProductManagerController getProductManagerController() {
		return productManagerController;
	}

	public void setProductManagerController(ProductManagerController productManagerController) {
		this.productManagerController = productManagerController;
	}

	public JMenuItem getProductManagerMenuItem() {
		return productManagerMenuItem;
	}

	public void setProductManagerMenuItem(JMenuItem productManagerMenuItem) {
		this.productManagerMenuItem = productManagerMenuItem;
	}

	public JMenuItem getProductSearchMenuItem() {
		return productSearchMenuItem;
	}

	public void setProductSearchMenuItem(JMenuItem productSearchMenuItem) {
		this.productSearchMenuItem = productSearchMenuItem;
	}

	public JMenu getSupplierMenu() {
		return supplierMenu;
	}

	public void setSupplierMenu(JMenu supplierMenu) {
		this.supplierMenu = supplierMenu;
	}

	public JMenuItem getCustomerManagerMenuItem() {
		return customerManagerMenuItem;
	}

	public void setCustomerManagerMenuItem(JMenuItem customerManagerMenuItem) {
		this.customerManagerMenuItem = customerManagerMenuItem;
	}

	public JMenuItem getEmployeeManagerMenuItem() {
		return employeeManagerMenuItem;
	}

	public void setEmployeeManagerMenuItem(JMenuItem employeeManagerMenuItem) {
		this.employeeManagerMenuItem = employeeManagerMenuItem;
	}

	public JMenuItem getSupplierManagerMenuItem() {
		return supplierManagerMenuItem;
	}

	public void setSupplierManagerMenuItem(JMenuItem supplierManagerMenuItem) {
		this.supplierManagerMenuItem = supplierManagerMenuItem;
	}

	public ProductSearchView getProductSearchView() {
		return productSearchView;
	}

	public void setProductSearchView(ProductSearchView productSearchView) {
		this.productSearchView = productSearchView;
	}

	public ProductSearchController getProductSearchController() {
		return productSearchController;
	}

	public void setProductSearchController(ProductSearchController productSearchController) {
		this.productSearchController = productSearchController;
	}

	public SupplierManagerView getSupplierManagerView() {
		return supplierManagerView;
	}

	public void setSupplierManagerView(SupplierManagerView supplierManagerView) {
		this.supplierManagerView = supplierManagerView;
	}

	public SupplierController getSupplierController() {
		return supplierController;
	}

	public void setSupplierController(SupplierController supplierController) {
		this.supplierController = supplierController;
	}

	public EmployeeManagerView getEmployeeManagerView() {
		return employeeManagerView;
	}

	public void setEmployeeManagerView(EmployeeManagerView employeeManagerView) {
		this.employeeManagerView = employeeManagerView;
	}

	public EmployeeManagerController getEmployeeManagerController() {
		return employeeManagerController;
	}

	public void setEmployeeManagerController(EmployeeManagerController employeeManagerController) {
		this.employeeManagerController = employeeManagerController;
	}

	public CustomerManagerView getCustomerManagerView() {
		return customerManagerView;
	}

	public void setCustomerManagerView(CustomerManagerView customerManagerView) {
		this.customerManagerView = customerManagerView;
	}

	public CustomerManagerController getCustomerManagerController() {
		return customerManagerController;
	}

	public void setCustomerManagerController(CustomerManagerController customerManagerController) {
		this.customerManagerController = customerManagerController;
	}

	public static Account getAccount() {
		return account;
	}

	public JButton getAccountInfoBtn() {
		return accountInfoBtn;
	}

	public void setAccountInfoBtn(JButton accountInfoBtn) {
		this.accountInfoBtn = accountInfoBtn;
	}

	public MainFrameController getMainFrameController() {
		return mainFrameController;
	}

	public void setMainFrameController(MainFrameController mainFrameController) {
		this.mainFrameController = mainFrameController;
	}

	public JPanel getTopPanel() {
		return topPanel;
	}

	public void setTopPanel(JPanel topPanel) {
		this.topPanel = topPanel;
	}

	public EmployeeSearchView getEmployeeSearchManagerView() {
		return employeeSearchManagerView;
	}

	public void setEmployeeSearchManagerView(EmployeeSearchView employeeSearchManagerView) {
		this.employeeSearchManagerView = employeeSearchManagerView;
	}

	public EmployeeSearchController getEmployeeSearchController() {
		return employeeSearchController;
	}

	public void setEmployeeSearchController(EmployeeSearchController employeeSearchController) {
		this.employeeSearchController = employeeSearchController;
	}

	public JMenuItem getEmployeeFileMenuItem() {
		return fileManagerMenuItem;
	}

	public void setEmployeeFileMenuItem(JMenuItem employeeFileMenuItem) {
		this.fileManagerMenuItem = employeeFileMenuItem;
	}

	public FileManagerView getEmployeeFileView() {
		return fileManagerView;
	}

	public void setEmployeeFileView(FileManagerView employeeFileView) {
		this.fileManagerView = employeeFileView;
	}

	public JMenuItem getEmployeeSearhMenuItem() {
		return employeeSearhMenuItem;
	}

	public void setEmployeeSearhMenuItem(JMenuItem employeeSearhMenuItem) {
		this.employeeSearhMenuItem = employeeSearhMenuItem;
	}

	public FileManagerController getFileManagerController() {
		return fileManagerController;
	}

	public void setFileManagerController(FileManagerController fileManagerController) {
		this.fileManagerController = fileManagerController;
	}

	public JMenuItem getFileManagerMenuItem() {
		return fileManagerMenuItem;
	}

	public void setFileManagerMenuItem(JMenuItem fileManagerMenuItem) {
		this.fileManagerMenuItem = fileManagerMenuItem;
	}

	public JMenuItem getNewInvoiceMenuItem() {
		return newInvoiceMenuItem;
	}

	public void setNewInvoiceMenuItem(JMenuItem newInvoiceMenuItem) {
		this.newInvoiceMenuItem = newInvoiceMenuItem;
	}

	public FileManagerView getFileManagerView() {
		return fileManagerView;
	}

	public void setFileManagerView(FileManagerView fileManagerView) {
		this.fileManagerView = fileManagerView;
	}

	public NewInvoiceView getNewInvoiceView() {
		return newInvoiceView;
	}

	public void setNewInvoiceView(NewInvoiceView newInvoiceView) {
		this.newInvoiceView = newInvoiceView;
	}

	public NewInvoiceController getNewInvoiceController() {
		return newInvoiceController;
	}

	public void setNewInvoiceController(NewInvoiceController newInvoiceController) {
		this.newInvoiceController = newInvoiceController;
	}

	public JMenuItem getInvoiceManagerMenuItem() {
		return invoiceManagerMenuItem;
	}

	public void setInvoiceManagerMenuItem(JMenuItem invoiceManagerMenuItem) {
		this.invoiceManagerMenuItem = invoiceManagerMenuItem;
	}

	public InvoiceManagerView getInvoiceMangerView() {
		return invoiceMangerView;
	}

	public void setInvoiceMangerView(InvoiceManagerView invoiceMangerView) {
		this.invoiceMangerView = invoiceMangerView;
	}

	public InvoiceManagerController getInvoiceManagerController() {
		return invoiceManagerController;
	}

	public void setInvoiceManagerController(InvoiceManagerController invoiceManagerController) {
		this.invoiceManagerController = invoiceManagerController;
	}

	public StatisticalView getStatisticalManagerView() {
		return statisticalManagerView;
	}

	public void setStatisticalManagerView(StatisticalView statisticalManagerView) {
		this.statisticalManagerView = statisticalManagerView;
	}

	public AccountManagerView getAccountManagerView() {
		return accountManagerView;
	}

	public void setAccountManagerView(AccountManagerView accountManagerView) {
		this.accountManagerView = accountManagerView;
	}

	public AccountManagerController getAccountManagerController() {
		return accountManagerController;
	}

	public void setAccountManagerController(AccountManagerController accountManagerController) {
		this.accountManagerController = accountManagerController;
	}

	public JMenuItem getAccountManagerMenuItem() {
		return accountManagerMenuItem;
	}

	public void setAccountManagerMenuItem(JMenuItem accountManagerMenuItem) {
		this.accountManagerMenuItem = accountManagerMenuItem;
	}

	public JMenuItem getStatisticalManagerMenuItem() {
		return statisticalManagerMenuItem;
	}

	public void setStatisticalManagerMenuItem(JMenuItem statisticalManagerMenuItem) {
		this.statisticalManagerMenuItem = statisticalManagerMenuItem;
	}

	public JMenu getAccountMenu() {
		return accountMenu;
	}

	public void setAccountMenu(JMenu accountMenu) {
		this.accountMenu = accountMenu;
	}

	public JMenuItem getAccountInfoMenuItem() {
		return accountInfoMenuItem;
	}

	public void setAccountInfoMenuItem(JMenuItem accountInfoMenuItem) {
		this.accountInfoMenuItem = accountInfoMenuItem;
	}

	public static void setAccount(Account account) {
		MainFrame.account = account;
	}

	public JMenuItem getLogoutAccountMenuItem() {
		return logoutAccountMenuItem;
	}

	public void setLogoutAccountMenuItem(JMenuItem logoutAccountMenuItem) {
		this.logoutAccountMenuItem = logoutAccountMenuItem;
	}

	public JMenuItem getSupplierSearchMenuItem() {
		return supplierSearchMenuItem;
	}

	public void setSupplierSearchMenuItem(JMenuItem supplierSearchMenuItem) {
		this.supplierSearchMenuItem = supplierSearchMenuItem;
	}

	public SupplierSearchView getSupplierSearchView() {
		return supplierSearchView;
	}

	public void setSupplierSearchView(SupplierSearchView supplierSearchView) {
		this.supplierSearchView = supplierSearchView;
	}

	public SupplierSearchController getSupplierSearchController() {
		return supplierSearchController;
	}

	public void setSupplierSearchController(SupplierSearchController supplierSearchController) {
		this.supplierSearchController = supplierSearchController;
	}

	public JMenuItem getCustomerSearchMenuItem() {
		return customerSearchMenuItem;
	}

	public void setCustomerSearchMenuItem(JMenuItem customerSearchMenuItem) {
		this.customerSearchMenuItem = customerSearchMenuItem;
	}

	public CustomerSearchView getCustomerSearchView() {
		return customerSearchView;
	}

	public void setCustomerSearchView(CustomerSearchView customerSearchView) {
		this.customerSearchView = customerSearchView;
	}

	public CustomerSearchController getCustomerSearchController() {
		return customerSearchController;
	}

	public void setCustomerSearchController(CustomerSearchController customerSearchController) {
		this.customerSearchController = customerSearchController;
	}

	public StatisticalController getStatisticalController() {
		return statisticalController;
	}

	public void setStatisticalController(StatisticalController statisticalController) {
		this.statisticalController = statisticalController;
	}

	public ShelfManagerView getShelfManagerView() {
		return shelfManagerView;
	}

	public void setShelfManagerView(ShelfManagerView shelfManagerView) {
		this.shelfManagerView = shelfManagerView;
	}

	public ShelfManagerController getShelfManagerController() {
		return shelfManagerController;
	}

	public void setShelfManagerController(ShelfManagerController shelfManagerController) {
		this.shelfManagerController = shelfManagerController;
	}

	public JMenuItem getShelfManagerMenuItem() {
		return shelfManagerMenuItem;
	}

	public void setShelfManagerMenuItem(JMenuItem shelfManagerMenuItem) {
		this.shelfManagerMenuItem = shelfManagerMenuItem;
	}
	
	
	
}
