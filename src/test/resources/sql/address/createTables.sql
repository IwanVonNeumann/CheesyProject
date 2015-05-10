CREATE TABLE Customers
(
	CustomerID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CustomerName varchar(25),
	Street varchar(25),
	ZipCode int,
	City varchar(25),
	PasswordHash binary(16),
	Deleted bit
);