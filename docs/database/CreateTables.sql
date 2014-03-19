CREATE TABLE Cheeses
 (
	CheeseID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CheeseName varchar(25),
	Description varchar(255),
	Price real,
	Deleted bit
);

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

CREATE TABLE Carts
(
	CartID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CustomerID int NOT NULL,
	Clock DATETIME,
	FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE CartEntries
(
	EntryID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CartID int NOT NULL,
	CheeseID int NOT NULL,
	Quantity int,
	FOREIGN KEY (CartID) REFERENCES Carts(CartID),
	FOREIGN KEY (CheeseID) REFERENCES Cheeses(CheeseID)
);