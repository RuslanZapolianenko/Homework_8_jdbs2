package org.example;

import org.flywaydb.core.Flyway;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import static org.example.Config.*;

public class OSBBMember {

    private Connection connection = null;
    private String sqlQuery = "SELECT OSBBMembers.FirstName, OSBBMembers.LastName, OSBBMembers.Email, " +
            "Buildings.Address, Apartments.ApartmentNumber, Apartments.Area " +
            "FROM OSBBMembers " +
            "JOIN OwnershipRights ON OSBBMembers.MemberID = OwnershipRights.MemberID " +
            "JOIN Apartments ON OwnershipRights.ApartmentID = Apartments.ApartmentID " +
            "JOIN Buildings ON Apartments.BuildingID = Buildings.BuildingID " +
            "LEFT JOIN Residents ON Apartments.ApartmentID = Residents.ApartmentID " +
            "WHERE Residents.HasCarAccess IS NULL " +
            "AND (SELECT COUNT(*) FROM OwnershipRights " +
            "WHERE OwnershipRights.MemberID = OSBBMembers.MemberID) < 2";

    public void fwMigration() {
        Flyway.configure()
                .dataSource(url, username, password)
                .locations("classpath:flyway/scripts")
                .load()
                .migrate();
    }

    public Connection getConnection() throws SQLException {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

    public List<Member> getMembersWithAutoNotAllowed() {
        try {
            this.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Member member = new Member();
        final List<Member> memberList = new LinkedList<>();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery)) {
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                memberList.add(member.setFirstName(resultSet.getString("FirstName")));
                memberList.add(member.setFirstName(resultSet.getString("FirstName")));
                memberList.add(member.setLastName(resultSet.getString("LastName")));
                memberList.add(member.setEmail(resultSet.getString("Email")));
                memberList.add(member.setAddress(resultSet.getString("Address")));
                memberList.add(member.setApartmentNumber(resultSet.getInt("ApartmentNumber")));
                memberList.add(member.setArea(resultSet.getDouble("Area")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);


        }
        return memberList;
    }
}

