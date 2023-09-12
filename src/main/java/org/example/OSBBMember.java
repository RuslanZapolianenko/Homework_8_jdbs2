package org.example;
import org.apache.log4j.Logger;
import org.flywaydb.core.Flyway;
import java.sql.*;

public class OSBBMember {
    private static final String url = "jdbc:mysql://localhost:3306/osbb";
    private static String username = "root";
    private static String password = "Ruslan2703";
    private static Connection connection = null;

    public void fwMigration() {

        Flyway.configure()
                .dataSource(url, username, password)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();
    }public static Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(url, username, password);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
    public static void getMembersWithAutoNotAllowed() {
        try {
            Connection connection = DriverManager.getConnection
                    (url, username, password);

            String sqlQuery = "SELECT OSBBMembers.FirstName, OSBBMembers.LastName, OSBBMembers.Email, " +
                    "Buildings.Address, Apartments.ApartmentNumber, Apartments.Area " +
                    "FROM OSBBMembers " +
                    "JOIN OwnershipRights ON OSBBMembers.MemberID = OwnershipRights.MemberID " +
                    "JOIN Apartments ON OwnershipRights.ApartmentID = Apartments.ApartmentID " +
                    "JOIN Buildings ON Apartments.BuildingID = Buildings.BuildingID " +
                    "LEFT JOIN Residents ON Apartments.ApartmentID = Residents.ApartmentID " +
                    "WHERE Residents.HasCarAccess IS NULL " +
                    "AND (SELECT COUNT(*) FROM OwnershipRights " +
                    "WHERE OwnershipRights.MemberID =OSBBMembers.MemberID) <2";

            PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String email = resultSet.getString("Email");
                String address = resultSet.getString("Address");
                int apartmentNumber = resultSet.getInt("ApartmentNumber");
                double area = resultSet.getDouble("Area");

                System.out.println("ПІБ: " + firstName + " " + lastName);
                System.out.println("Електронна пошта: " + email);
                System.out.println("Адреса будинку: " + address);
                System.out.println("Номер квартири: " + apartmentNumber);
                System.out.println("Площа квартири: " + area);
                System.out.println();
            }

            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    public static void createMember(String firstName, String lastName, String email, String roles) {
        try (Connection connection = getConnection()) {
            String sql = "INSERT INTO OSBBMembers (FirstName, LastName, Email, Roles) VALUES (?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, roles);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void getMemberByEmail(String email) {
        try (Connection connection = getConnection()) {
            String sql = "SELECT * FROM OSBBMembers WHERE Email = maryn@ukr.net";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(3, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int memberID = resultSet.getInt("MemberID");
                String firstName = resultSet.getString("FirstName");
                String lastName = resultSet.getString("LastName");
                String roles = resultSet.getString("Roles");

                System.out.println("Member ID: " + memberID);
                System.out.println("First Name: " + firstName);
                System.out.println("Last Name: " + lastName);
                System.out.println("Roles: " + roles);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateMemberRoles(int memberID, String newRoles) {
        try (Connection connection = getConnection()) {
            String sql = "UPDATE OSBBMembers SET Roles = ? WHERE MemberID = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, newRoles);
            preparedStatement.setInt(1, memberID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void deleteMember(int memberID) {
        try (Connection connection = getConnection()) {
            String sql = "DELETE FROM OSBBMembers WHERE MemberID = 1";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, memberID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
