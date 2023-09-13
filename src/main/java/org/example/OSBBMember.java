package org.example;

import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import java.sql.*;

import static org.example.Config.*;

public class OSBBMember {
    private static final Logger logger = Logger.getLogger(OSBBMember.class);
    private static Connection connection = null;

    public void fwMigration() {
        Flyway.configure()
                .dataSource(url, username, password)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();
    }
    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            // If the connection is not initialized or closed, create a new one
            connection = DriverManager.getConnection(url, username, password);
        }
        return connection;
    }

    public static void getMembersWithAutoNotAllowed() {
        try (Connection connection = getConnection()) {
            String sqlQuery = "SELECT OSBBMembers.FirstName, OSBBMembers.LastName, OSBBMembers.Email, " +
                    "Buildings.Address, Apartments.ApartmentNumber, Apartments.Area " +
                    "FROM OSBBMembers " +
                    "JOIN OwnershipRights ON OSBBMembers.MemberID = OwnershipRights.MemberID " +
                    "JOIN Apartments ON OwnershipRights.ApartmentID = Apartments.ApartmentID " +
                    "JOIN Buildings ON Apartments.BuildingID = Buildings.BuildingID " +
                    "LEFT JOIN Residents ON Apartments.ApartmentID = Residents.ApartmentID " +
                    "WHERE Residents.HasCarAccess IS NULL " +
                    "AND (SELECT COUNT(*) FROM OwnershipRights " +
                    "WHERE OwnershipRights.MemberID = OSBBMembers.MemberID) < 2";

            try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
                 ResultSet resultSet = preparedStatement.executeQuery()) {

                while (resultSet.next()) {
                    String firstName = resultSet.getString("FirstName");
                    String lastName = resultSet.getString("LastName");
                    String email = resultSet.getString("Email");
                    String address = resultSet.getString("Address");
                    int apartmentNumber = resultSet.getInt("ApartmentNumber");
                    double area = resultSet.getDouble("Area");

                    logger.info("ПІБ: " + firstName + " " + lastName);
                    logger.info("Електронна пошта: " + email);
                    logger.info("Адреса будинку: " + address);
                    logger.info("Номер квартири: " + apartmentNumber);
                    logger.info("Площа квартири: " + area);
                    logger.info(logger);
                }
            }
        } catch (SQLException e) {
            logger.error("Error executing SQL query: " + e.getMessage());
        }
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Error closing connection: " + e.getMessage());
            }
        }
    }
}

