
CREATE TABLE IF NOT EXISTS Buildings (
    BuildingID INT AUTO_INCREMENT PRIMARY KEY,
    Address VARCHAR(255) NOT NULL
);


CREATE TABLE IF NOT EXISTS Apartments (
    ApartmentID INT AUTO_INCREMENT PRIMARY KEY,
    BuildingID INT,
    ApartmentNumber INT,
    Area DECIMAL(8, 2),
    FOREIGN KEY (BuildingID) REFERENCES Buildings(BuildingID)
);


CREATE TABLE IF NOT EXISTS OSBBMembers (
    MemberID INT AUTO_INCREMENT PRIMARY KEY,
    FirstName VARCHAR(255) NOT NULL,
    LastName VARCHAR(255) NOT NULL,
    Email VARCHAR(255) UNIQUE NOT NULL,
    Roles SET('OSBB Member', 'OSBB Employee', 'OSBB Board Member', 'OSBB Chair') NOT NULL
);


CREATE TABLE IF NOT EXISTS OwnershipRights (
    OwnershipID INT AUTO_INCREMENT PRIMARY KEY,
    MemberID INT,
    ApartmentID INT,
    FOREIGN KEY (MemberID) REFERENCES OSBBMembers(MemberID),
    FOREIGN KEY (ApartmentID) REFERENCES Apartments(ApartmentID)
);


CREATE TABLE IF NOT EXISTS Residents (
    ResidentID INT AUTO_INCREMENT PRIMARY KEY,
    MemberID INT,
    ApartmentID INT,
    HasCarAccess BOOLEAN NOT NULL,
    FOREIGN KEY (MemberID) REFERENCES OSBBMembers(MemberID),
    FOREIGN KEY (ApartmentID) REFERENCES Apartments(ApartmentID)
);