INSERT INTO Buildings (Address) VALUES
    ('12 Крещатик улица'),
    ('15 Радужная улица'),
     ('19 Радужная улица'),
      ('10 Радужная улица'),
       ('1 Крещатик улица'),
       ('2 Крещатик улица'),
       ('13 Крещатик улица'),
       ('15 Крещатик улица'),
       ('17 Радужная улица'),
    ('78 Маяковского улица');

INSERT INTO Apartments (BuildingID, ApartmentNumber, Area) VALUES
    (1, 101, 75.5),
    (1, 102, 68.0),
    (2, 201, 80.2),
    (2, 202, 72.8),
	(2, 203, 81.8),
    (3, 12, 49.0),
    (4, 19, 68.0),
    (5, 56, 101.10),
    (6, 46, 78.0),
    (7, 75, 68.0);



INSERT INTO OSBBMembers (FirstName, LastName, Email, Roles) VALUES
    ('Марьян', 'Фпанко', 'maryn@ukr.net', 'OSBB Member'),
    ('Виктор', 'Сергиенко', 'viktor@gmail.com', 'OSBB Board Member'),
    ('Василий', 'Игнатенко', 'Ignatenko@bigmir.com', 'OSBB Chair'),
    ('Андрей', 'Смирнов', 'smerr@gmail.com', 'OSBB Employee'),
    ('Александр', 'Точков', 'tochka@gmail.com', 'OSBB Employee'),
    ('Виктор', 'Разумков', 'razumkov23@gmail.com', 'OSBB Employee'),
    ('Юрий', 'Федотов', 'uf123@gmail.com', 'OSBB Employee'),
    ('Андрей', 'Радивилов', 'rada56@gmail.com', 'OSBB Employee'),
    ('Ростислав', 'Франдок', 'frad90@gmail.com', 'OSBB Employee'),
    ('Руслан', 'Михальчук', 'mr@gmail.com', 'OSBB Employee');


INSERT INTO OwnershipRights (MemberID, ApartmentID) VALUES
    (1, 1),
    (2, 2),
    (3, 3),
    (3, 4),
    (4, 1),
    (5, 1),
    (6, 1),
	(7, 2),
    (8, 1),
    (9, 1);


INSERT INTO Residents (MemberID, ApartmentID, HasCarAccess) VALUES
    (1, 1, 1),
    (2, 2, 0),
    (3, 3, 1),
    (3, 4, 0),
    (4, 1, 0),
    (5, 1, 1),
    (6, 1, 0),
	(7, 2, 1),
    (8, 1, 1),
    (9, 1, 1);