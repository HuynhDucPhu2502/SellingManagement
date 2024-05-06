use master
go

-- Tạo cơ sở dữ liệu SellingManagement
CREATE DATABASE SellingManagement;
GO

-- Sử dụng cơ sở dữ liệu SellingManagement
USE SellingManagement;
GO

-- Tạo bảng ProductCategory
CREATE TABLE ProductCategory (
    CategoryID nvarchar(50) PRIMARY KEY,
    CategoryName nvarchar(50) NOT NULL UNIQUE,
    ItemCount int NOT NULL
);
GO

-- Tạo bảng Supplier
CREATE TABLE Supplier (
    SupplierID NVARCHAR(50) PRIMARY KEY,
    SupplierName NVARCHAR(100) NOT NULL UNIQUE,
    Address NVARCHAR(200) NOT NULL,
    PhoneNumber NVARCHAR(20) NOT NULL,
    Email NVARCHAR(50) NOT NULL
);

-- Kệ hàng
CREATE TABLE [dbo].[Ke](
	[IDKe] nvarchar(50) primary key NOT NULL,
	[Tenke] nvarchar(50) NOT NULL,
	[Vitri] nvarchar(50) NOT NULL,
	[Kichthuoc] nvarchar(50) NOT NULL,
	[Trangthai] nvarchar(50) NOT NULL,
	[Ghichu] nvarchar(50) NOT NULL,
);

-- Tạo bảng Product
CREATE TABLE Product (
    ProductID NVARCHAR(50) PRIMARY KEY,
    ProductName NVARCHAR(50) NOT NULL,
    PurchasePrice FLOAT NOT NULL,
    SellingPrice FLOAT NOT NULL,
    Stock INT NOT NULL,
    CategoryName NVARCHAR(50) NOT NULL,
    Unit NVARCHAR(10) CONSTRAINT CK_Product_Unit CHECK (Unit IN ('thùng', 'gói', 'chai', 'túi', 'cái', 'hộp')),
    ReceiveDate DATE NOT NULL,
    SupplierName NVARCHAR(100),
	ShelfID nvarchar(50)

	CONSTRAINT FK_Product_Shelf FOREIGN KEY (ShelfID)
        REFERENCES dbo.Ke(IDKe) ON DELETE SET NULL ON UPDATE CASCADE,
    CONSTRAINT FK_Product_ProductCategory_CategoryName FOREIGN KEY (CategoryName) 
        REFERENCES ProductCategory(CategoryName) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Product_Supplier_SupplierName FOREIGN KEY (SupplierName) 
        REFERENCES Supplier(SupplierName) ON DELETE CASCADE ON UPDATE CASCADE
);



-- Chèn dữ liệu vào bảng ProductCategory
INSERT INTO ProductCategory (CategoryID, CategoryName, ItemCount)
VALUES 
('TC', N'Trái cây', 0),
('GK', N'Giải khát', 0),
('SK', N'Sách', 0);


-- Chèn dữ liệu vào bảng Supplier
INSERT INTO Supplier (SupplierID, SupplierName, Address, PhoneNumber, Email) VALUES
('SPL001', N'Công ty TNHH ABC', N'123 Đường XYZ, Quận 1, TP.HCM', '0123456789', 'contact@abc.vn'),
('SPL002', N'Nhà Sách Minh Khai', N'456 Đường LĐH, Quận 5, TP.HCM', '0987654321', 'info@minhkhai.vn'),
('SPL003', N'Công ty Dụng Cụ Thể Thao DEF', N'789 Đường QWE, Quận 3, TP.HCM', '1234567890', 'sales@defsport.vn'),
('SPL004', N'Công ty Vật Liệu Xây Dựng GHI', N'101 Đường ASX, Quận 2, TP.HCM', '0987612345', 'support@ghimaterials.vn'),
('SPL005', N'Công ty Thực Phẩm JKL', N'202 Đường ZXC, Quận 9, TP.HCM', '0123987654', 'order@jklfoods.vn'),
('SPL006', N'Công ty Sách Đại Học', N'789 Đường UVW, Quận 4, TP.HCM', '0345678901', 'info@sachdaihoc.vn'),
('SPL007', N'Công ty Nước Ngọt XYZ', N'101 Đường KLM, Quận 7, TP.HCM', '0234567890', 'order@nuocngotxyz.vn');

-- Chèn dữ liệu vào bảng Ke 
INSERT INTO dbo.Ke (IDKe, Tenke, Vitri, Kichthuoc, Trangthai, Ghichu)
VALUES 
(N'K1', N'Kệ Trái Cây 1', N'Góc trái', N'80x40x180', N'Đang sử dụng', N'Kệ chứa trái cây nội địa'),
(N'K2', N'Kệ Trái Cây 2', N'Góc phải', N'80x40x180', N'Đang sử dụng', N'Kệ trái cây nhập khẩu'),
(N'K3', N'Kệ Giải Khát 1', N'Góc trái', N'100x50x200', N'Đang sử dụng', N'Kệ giải khát cao cấp'),
(N'K4', N'Kệ Trái Cây 3', N'Góc phải', N'100x50x200', N'Đang sử dụng', N'Kệ trái cây tổng hợp'),
(N'K5', N'Kệ Sách', N'Góc trái', N'120x60x220', N'Đang sử dụng', N'Kệ sách giáo khoa'),
(N'K6', N'Kệ Trái Cây 4', N'Góc phải', N'100x50x200', N'Đang sử dụng', N'Kệ trái cây theo mùa'),
(N'K7', N'Kệ Trái Cây 5', N'Góc trái', N'120x60x220', N'Đang sử dụng', N'Kệ trái cây đặc sản'),
(N'K8', N'Kệ Trái Cây 6', N'Góc phải', N'120x60x220', N'Đang sử dụng', N'Kệ trái cây hữu cơ'),
(N'K9', N'Kệ Giải Khát 2', N'Góc trái', N'100x50x200', N'Đang sử dụng', N'Kệ nước giải khát phổ thông'),
(N'K10', N'Kệ Giải Khát 3', N'Góc phải', N'80x40x180', N'Đang sử dụng', N'Kệ nước giải khát nhập khẩu');



-- Chèn dữ liệu vào bảng Product
-- Chèn dữ liệu vào bảng Product
INSERT INTO Product (ProductID, ProductName, PurchasePrice, SellingPrice, Stock, CategoryName, Unit, ReceiveDate, SupplierName, ShelfID)
VALUES 
(N'TC01', N'Dưa Hấu', 10, 14.00, 200, N'Trái cây', N'Cái', '2024-04-01', N'Công ty Thực Phẩm JKL', N'K1'),
(N'TC02', N'Bí Ngô', 12, 15.00, 200, N'Trái cây', N'Cái', '2024-04-02', N'Công ty Thực Phẩm JKL', N'K2'),
(N'TC03', N'Cà Rốt', 3, 4.50, 200, N'Trái cây', N'Cái', '2024-04-03', N'Công ty Thực Phẩm JKL', N'K4'),
(N'GK01', N'Trà xanh 0 độ', 5, 8, 120, N'Giải khát', N'Chai', '2024-04-04', N'Nhà Sách Minh Khai', N'K3'),
(N'GK02', N'Nước tăng lực Number One', 8, 12, 60, N'Giải khát', N'Chai', '2024-04-05', N'Nhà Sách Minh Khai', N'K9'),
(N'TC04', N'Chuối', 8, 12.00, 150, N'Trái cây', N'Cái', '2024-04-06', N'Công ty Thực Phẩm JKL', N'K6'),
(N'TC05', N'Xoài', 15, 20.00, 100, N'Trái cây', N'Cái', '2024-04-07', N'Công ty Thực Phẩm JKL', N'K7'),
(N'TC06', N'Cam', 6, 8.00, 180, N'Trái cây', N'Cái', '2024-04-08', N'Công ty Thực Phẩm JKL', N'K8'),
(N'GK03', N'Nước ngọt Coca Cola', 10, 15.00, 80, N'Giải khát', N'Chai', '2024-04-09', N'Công ty Nước Ngọt XYZ', N'K9'),
(N'GK04', N'Nước ngọt Pepsi', 9, 14.00, 90, N'Giải khát', N'Chai', '2024-04-10', N'Công ty Nước Ngọt XYZ', N'K10'),
(N'GK05', N'Nước ép cam', 7, 10.00, 120, N'Giải khát', N'Chai', '2024-04-11', N'Công ty Nước Ngọt XYZ', N'K3'),
(N'TC07', N'Nho', 20, 25.00, 80, N'Trái cây', N'Cái', '2024-04-12', N'Công ty Thực Phẩm JKL', N'K1'),
(N'TC08', N'Dừa', 5, 7.00, 200, N'Trái cây', N'Cái', '2024-04-13', N'Công ty Thực Phẩm JKL', N'K2'),
(N'GK06', N'Nước chanh', 4, 6.00, 150, N'Giải khát', N'Chai', '2024-04-14', N'Công ty Nước Ngọt XYZ', N'K9'),
(N'GK07', N'Nước táo', 6, 9.00, 100, N'Giải khát', N'Chai', '2024-04-15', N'Công ty Nước Ngọt XYZ', N'K10'),
(N'SK01', N'Sách Toán', 50, 70.00, 50, N'Sách', N'Cái', '2024-04-01', N'Công ty Sách Đại Học', N'K5'),
(N'SK02', N'Sách Văn', 40, 60.00, 70, N'Sách', N'Cái', '2024-04-02', N'Công ty Sách Đại Học', N'K5'),
(N'SK03', N'Sách Lịch Sử', 55, 75.00, 30, N'Sách', N'Cái', '2024-04-03', N'Công ty Sách Đại Học', N'K5');



-- Cập nhật số lượng mặt hàng trong bảng ProductCategory
GO
UPDATE ProductCategory
SET ItemCount = 
(
	SELECT COUNT(*) 
	FROM Product 
	WHERE Product.CategoryName = ProductCategory.CategoryName
)

-- Tạo bảng Employee
CREATE TABLE [dbo].[Employee](
	EmployeeID NVARCHAR(10) NOT NULL PRIMARY KEY,
	LastName NVARCHAR(50) NULL,
	FirstName NVARCHAR(50) NOT NULL,
	Address NVARCHAR(250) NULL,
	PhoneNumber NVARCHAR(11) NOT NULL UNIQUE,
	Email NVARCHAR(50) NULL,
	Position NVARCHAR(50) NOT NULL,
	Birth DATE NULL,
	Gender NVARCHAR(5) NULL,
	CoefficientsSalary float NULL
)
GO

-- Chèn dữ liệu vào bảng Employee
INSERT INTO [dbo].[Employee] ([EmployeeID], [LastName], [FirstName], [Address], [PhoneNumber], [Email], [Position], [Birth], [Gender], [CoefficientsSalary])
VALUES
('EMP001', N'Vũ', N'Bá Hải', N'123 Đường B, Quận C', N'01234567891', N'BaHaiKTCT@example.com', N'Nhân viên bán hàng', '1990-01-01', N'Nam', 1.5),
('EMP002', N'Nguyễn', N'Xuân Lô', N'456 Đường E, Quận F', N'09876543210', N'XuanLoNguyen@iuh.edu.vn', N'Nhân viên quản lý', '1985-05-05', N'Nam', 2.5),
('EMP003', N'Trần', N'Thủy Tiên', N'789 Đường G, Quận H', N'02345678901', N'ThuyTienTT@example.com', N'Kế toán', '1992-08-15', N'Nữ', 2.0),
('EMP004', N'Lê', N'Thành Đạt', N'987 Đường I, Quận K', N'03456789012', N'ThanhDatLe@company.com', N'Giám đốc', '1980-12-12', N'Nam', 3.0),
('EMP005', N'Phạm', N'Hoàng Anh', N'321 Đường L, Quận M', N'04567890123', N'HoangAnhPA@biz.com', N'Trợ lý', '1988-03-23', N'Nam', 1.8);
go


-- Tạo bảng Customer
CREATE TABLE Customer (
    CustomerID nvarchar(50) PRIMARY KEY,
    FirstName nvarchar(50),
    LastName nvarchar(50),
    Address nvarchar(50),
    PhoneNumber nvarchar(10) NOT NULL UNIQUE,
    CustomerType nvarchar(50) CHECK (CustomerType IN (N'Thành Viên', N'VIP'))
);

-- Thêm dữ liệu vào bảng Customer
INSERT INTO Customer (CustomerID, FirstName, LastName, Address, PhoneNumber, CustomerType) VALUES
(N'CUST001', N'Minh', N'Nguyễn Nhật', N'123 Đường ABC, TP.HCM', N'0123456789', N'Thành Viên'),
(N'CUST002', N'Hoa', N'Trần Mỹ', N'234 Đường XYZ, Hà Nội', N'0987654321', N'VIP'),
(N'CUST003', N'Thảo', N'Lê Vương', N'345 Đường DEF, Đà Nẵng', N'0234567891', N'Thành Viên'),
(N'CUST004', N'Duy', N'Phạm Minh', N'456 Đường GHI, Nha Trang', N'0345678902', N'VIP'),
(N'CUST005', N'Linh', N'Hoàng Thị Thỳ', N'567 Đường JKL, Cần Thơ', N'0456789012', N'Thành Viên');

-- Tạo bảng Account cho nhân viên
CREATE TABLE Account (
    Username NVARCHAR(50) PRIMARY KEY,
    Password NVARCHAR(50) NOT NULL,
    EmployeeID NVARCHAR(10),

    CONSTRAINT FK_Account_Employee FOREIGN KEY (EmployeeID)
        REFERENCES [dbo].[Employee](EmployeeID) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO Account (Username, Password, EmployeeID) VALUES
('vubahai', '123456', 'EMP001'),
('nguyenxuanlo', '123456', 'EMP002'),
('thuytientran', '123456', 'EMP003');



CREATE TABLE Invoice (
    InvoiceID NVARCHAR(50) PRIMARY KEY,
    EmployeeID NVARCHAR(10) NOT NULL,
    CustomerID NVARCHAR(50),
    InvoiceDate DATE NOT NULL,
    TotalAmount FLOAT NOT NULL,
    PurchaseType  NVARCHAR(50) NOT NULL DEFAULT 'Mua trực tiếp',

    CONSTRAINT FK_Invoice_Employee FOREIGN KEY (EmployeeID)
        REFERENCES Employee(EmployeeID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Invoice_Customer FOREIGN KEY (CustomerID)
        REFERENCES Customer(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE InvoiceDetails (
    InvoiceDetailID INT IDENTITY(1,1) PRIMARY KEY,
    InvoiceID NVARCHAR(50),
    ProductID NVARCHAR(50),
    Quantity INT NOT NULL,
    SellingPrice FLOAT NOT NULL,
    TotalPrice FLOAT NOT NULL,

    CONSTRAINT FK_InvoiceDetails_Invoice FOREIGN KEY (InvoiceID)
        REFERENCES Invoice(InvoiceID) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_InvoiceDetails_Product FOREIGN KEY (ProductID)
        REFERENCES Product(ProductID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Chèn dữ liệu vào bảng Invoice
INSERT INTO Invoice (InvoiceID, EmployeeID, CustomerID, InvoiceDate, TotalAmount, PurchaseType)
VALUES 
('HD00001', 'EMP001', 'CUST001', '2024-03-10', 0, N'Mua trực tiếp'),
('HD00002', 'EMP002', 'CUST002', '2024-03-12', 0, N'Mua trực tiếp'),
('HD00003', 'EMP001', 'CUST003', '2024-04-19', 0, N'Mua trực tiếp');

-- Chèn dữ liệu vào bảng InvoiceDetails cho hóa đơn INV001
INSERT INTO InvoiceDetails (InvoiceID, ProductID, Quantity, SellingPrice, TotalPrice)
VALUES 
('HD00001', 'TC01', 20, 14.00, 280),
('HD00001', 'GK01', 10, 8, 80),
('HD00001', 'SK01', 5, 70.00, 350);

-- Cập nhật tổng số tiền cho hóa đơn INV001
UPDATE Invoice
SET TotalAmount = (SELECT SUM(TotalPrice) FROM InvoiceDetails WHERE InvoiceID = 'HD00001')
WHERE InvoiceID = 'HD00001';

-- Chèn dữ liệu vào bảng InvoiceDetails cho hóa đơn INV002
INSERT INTO InvoiceDetails (InvoiceID, ProductID, Quantity, SellingPrice, TotalPrice)
VALUES 
('HD00002', 'TC05', 15, 20.00, 300),
('HD00002', 'GK03', 25, 15.00, 375),
('HD00002', 'SK02', 2, 60.00, 120);

-- Cập nhật tổng số tiền cho hóa đơn INV002
UPDATE Invoice
SET TotalAmount = (SELECT SUM(TotalPrice) FROM InvoiceDetails WHERE InvoiceID = 'HD00002')
WHERE InvoiceID = 'HD00002';

-- Chèn dữ liệu vào bảng InvoiceDetails cho hóa đơn INV003
INSERT INTO InvoiceDetails (InvoiceID, ProductID, Quantity, SellingPrice, TotalPrice)
VALUES 
('HD00003', 'TC02', 30, 15.00, 450),
('HD00003', 'GK05', 40, 10.00, 400),
('HD00003', 'SK03', 3, 75.00, 225);

-- Cập nhật tổng số tiền cho hóa đơn INV003
UPDATE Invoice
SET TotalAmount = (SELECT SUM(TotalPrice) FROM InvoiceDetails WHERE InvoiceID = 'HD00003')
WHERE InvoiceID = 'HD00003';

-- Thêm hóa đơn mới
INSERT INTO Invoice (InvoiceID, EmployeeID, CustomerID, InvoiceDate, TotalAmount, PurchaseType)
VALUES 
('HD00004', 'EMP003', 'CUST001', '2024-04-15', 0, N'Mua trực tiếp'),
('HD00005', 'EMP003', 'CUST002', '2023-06-14', 0, N'Mua trực tiếp'),
('HD00006', 'EMP003', 'CUST003', '2023-07-13', 0, N'Mua trực tiếp');

-- Thêm chi tiết hóa đơn cho HD00004
INSERT INTO InvoiceDetails (InvoiceID, ProductID, Quantity, SellingPrice, TotalPrice)
VALUES 
('HD00004', 'TC03', 25, 4.50, 112.5),
('HD00004', 'GK04', 30, 14.00, 420),
('HD00004', 'SK03', 10, 75.00, 750);

-- Cập nhật tổng số tiền cho hóa đơn HD00004
UPDATE Invoice
SET TotalAmount = (SELECT SUM(TotalPrice) FROM InvoiceDetails WHERE InvoiceID = 'HD00004')
WHERE InvoiceID = 'HD00004';

-- Thêm chi tiết hóa đơn cho HD00005
INSERT INTO InvoiceDetails (InvoiceID, ProductID, Quantity, SellingPrice, TotalPrice)
VALUES 
('HD00005', 'TC07', 40, 25.00, 1000),
('HD00005', 'GK05', 50, 10.00, 500),
('HD00005', 'SK01', 20, 70.00, 1400);

-- Cập nhật tổng số tiền cho hóa đơn HD00005
UPDATE Invoice
SET TotalAmount = (SELECT SUM(TotalPrice) FROM InvoiceDetails WHERE InvoiceID = 'HD00005')
WHERE InvoiceID = 'HD00005';

-- Thêm chi tiết hóa đơn cho HD00006
INSERT INTO InvoiceDetails (InvoiceID, ProductID, Quantity, SellingPrice, TotalPrice)
VALUES 
('HD00006', 'TC02', 15, 15.00, 225),
('HD00006', 'GK06', 60, 6.00, 360),
('HD00006', 'SK02', 30, 60.00, 1800);

-- Cập nhật tổng số tiền cho hóa đơn HD00006
UPDATE Invoice
SET TotalAmount = (SELECT SUM(TotalPrice) FROM InvoiceDetails WHERE InvoiceID = 'HD00006')
WHERE InvoiceID = 'HD00006';


-- Tạo hàm thống kê hàng hóa được bán nhiều nhất
GO
CREATE FUNCTION TotalSoldProductsQuantityAll()
RETURNS TABLE
AS
	RETURN (
		SELECT p.ProductID, p.ProductName, SUM(id.Quantity) AS TotalSoldQuantity
		FROM InvoiceDetails id
		JOIN Product p ON id.ProductID = p.ProductID
		GROUP BY p.ProductID, p.ProductName
	)

GO
CREATE FUNCTION TotalSoldProductsQuantityByYear(@year int)
RETURNS TABLE
AS
	RETURN (
		SELECT p.ProductID, p.ProductName, SUM(id.Quantity) AS TotalSoldQuantity
		FROM InvoiceDetails id
		JOIN Product p ON id.ProductID = p.ProductID
		JOIN Invoice i ON i.InvoiceID = id.InvoiceID
		WHERE YEAR(i.InvoiceDate) = @year
		GROUP BY p.ProductID, p.ProductName
	)

GO
CREATE FUNCTION TotalSoldProductsQuantityByYearMonth(@year int, @month int)
RETURNS TABLE
AS
	RETURN (
		SELECT p.ProductID, p.ProductName, SUM(id.Quantity) AS TotalSoldQuantity
		FROM InvoiceDetails id
		JOIN Product p ON id.ProductID = p.ProductID
		JOIN Invoice i ON i.InvoiceID = id.InvoiceID
		WHERE YEAR(i.InvoiceDate) = @year AND MONTH(i.InvoiceDate) = @month
		GROUP BY p.ProductID, p.ProductName
	)

	
-- Tạo hàm thống kê khách hàng mua nhiều hàng nhất
GO
CREATE FUNCTION CustomerTotalPurchasesAll()
RETURNS TABLE
AS
	RETURN (
		SELECT c.CustomerID, c.LastName + ' ' + c.FirstName FullName, SUM(i.TotalAmount) TotalAmount
		FROM Customer c
		JOIN Invoice i ON i.CustomerID = c.CustomerID
		GROUP BY c.CustomerID, c.LastName,c.FirstName	
	)

GO
CREATE FUNCTION CustomerTotalPurchasesByYear(@year int)
RETURNS TABLE
AS
	RETURN (
		SELECT c.CustomerID, c.LastName + ' ' + c.FirstName FullName, SUM(i.TotalAmount) TotalAmount
		FROM Customer c
		JOIN Invoice i ON i.CustomerID = c.CustomerID
		WHERE YEAR(i.InvoiceDate) = @year
		GROUP BY c.CustomerID, c.LastName,c.FirstName	
	)

GO
CREATE FUNCTION CustomerTotalPurchasesByYearMonth(@year int, @month int)
RETURNS TABLE
AS
	RETURN (
		SELECT c.CustomerID, c.LastName + ' ' + c.FirstName FullName, SUM(i.TotalAmount) TotalAmount
		FROM Customer c
		JOIN Invoice i ON i.CustomerID = c.CustomerID
		WHERE YEAR(i.InvoiceDate) = @year
		GROUP BY c.CustomerID, c.LastName,c.FirstName	
	)
	
-- Tạo hàm thống kê nhân viên số tiền đã bán
GO
CREATE FUNCTION EmployeeSalesTotalAll()
RETURNS TABLE
AS
	RETURN (
		SELECT e.EmployeeID, e.LastName + ' ' + e.FirstName FullName, SUM(i.TotalAmount) TotalAmount
		FROM Employee e
		JOIN Invoice i ON i.EmployeeID = e.EmployeeID
		GROUP BY e.EmployeeID, e.LastName, e.FirstName	
	)

GO
CREATE FUNCTION EmployeeSalesTotalByYear(@year int)
RETURNS TABLE
AS
	RETURN (
		SELECT e.EmployeeID, e.LastName + ' ' + e.FirstName FullName, SUM(i.TotalAmount) TotalAmount
		FROM Employee e
		JOIN Invoice i ON i.EmployeeID = e.EmployeeID
		WHERE YEAR(i.InvoiceDate) = @year
		GROUP BY e.EmployeeID, e.LastName, e.FirstName	
	)

GO
CREATE FUNCTION EmployeeSalesTotalByYearMonth(@year int, @month int)
RETURNS TABLE
AS
	RETURN (
		SELECT e.EmployeeID, e.LastName + ' ' + e.FirstName FullName, SUM(i.TotalAmount) TotalAmount
		FROM Employee e
		JOIN Invoice i ON i.EmployeeID = e.EmployeeID
		WHERE YEAR(i.InvoiceDate) = @year AND MONTH(i.InvoiceDate) = @month
		GROUP BY e.EmployeeID, e.LastName, e.FirstName	
	)
GO

create procedure proc_discountForInvoice (@p_invoiceID nvarchar(50))
as
	begin	
		declare @totalInvoice money
		set @totalInvoice = (
			select i.TotalAmount
			from [dbo].[Invoice] i
			where i.InvoiceID = @p_invoiceID
		) 
		update [dbo].[Invoice] 
		set TotalAmount = 
			case
				when c.CustomerType = N'Thành Viên' then (@totalInvoice - @totalInvoice * 0.03) * 1.1
				when c.CustomerType = N'VIP' then (@totalInvoice - @totalInvoice * 0.06) * 1.1
			end
		from [dbo].[Invoice] i
		join [dbo].[Customer] c on i.CustomerID = c.CustomerID
	end
go

create trigger trigger_Invoice_discount
on [dbo].[Invoice]
for insert, delete, update
as
	begin
	if exists(select * from inserted)
		begin
			declare @invoiceID nvarchar(50)
			set @invoiceID = (select InvoiceID from inserted)
			exec proc_discountForInvoice @invoiceID
		end
	end
go