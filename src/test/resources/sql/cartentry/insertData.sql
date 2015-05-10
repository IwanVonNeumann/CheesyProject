INSERT INTO Cheeses (
  CheeseName, Description, Price
) VALUES (
  'Gouda', 'Gouda is a yellowish Dutch...', 1.65
);

INSERT INTO Cheeses (
  CheeseName, Description, Price
) VALUES (
  'Edam', 'Edam (Dutch Edammer) is a...', 1.05
);

INSERT INTO Cheeses (
  CheeseName, Description, Price
) VALUES (
  'Maasdam', 'Maasdam cheese is a Dutch...', 2.35
);

UPDATE Cheeses
    SET Deleted = 0;

INSERT INTO Customers (CustomerName, Street, ZipCode, City)
VALUES ('John Wayne', 'Penny Lane', 1123, 'New York');

INSERT INTO Customers (CustomerName, Street, ZipCode, City)
    VALUES ('Yanni Wirmann', 'Memory Lane', 1101, 'Espoo');

INSERT INTO Customers (CustomerName, Street, ZipCode, City)
VALUES ('John Newman', 'Drizzle Avenue', 2519, 'London');

UPDATE Customers SET Deleted = 0;

INSERT INTO Carts (CustomerID, Clock)
    VALUES (1, '2014-03-01 18:26:29');

INSERT INTO Carts (CustomerID, Clock)
    VALUES (1, '2014-03-21 05:14:23');

INSERT INTO Carts (CustomerID, Clock)
    VALUES (2, '2014-03-22 20:22:00');

INSERT INTO Cartentries (CartID, CheeseID, Quantity)
    VALUES (1, 1, 2);

INSERT INTO Cartentries (CartID, CheeseID, Quantity)
    VALUES (1, 2, 3);

INSERT INTO Cartentries (CartID, CheeseID, Quantity)
    VALUES (2, 3, 1);
