CREATE TABLE Cheeses
 (
	CheeseID int NOT NULL AUTO_INCREMENT PRIMARY KEY,
	CheeseName varchar(25),
	Description varchar(255),
	Price real,
	Deleted bit
);