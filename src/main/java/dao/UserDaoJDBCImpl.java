package dao;

import model.User;
import util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl() {
        this.connection = Util.getConnection();
    }

    public void createUsersTable() {
        String createTableUsers = "CREATE TABLE IF NOT EXISTS users" +
                "(id SERIAL PRIMARY KEY, " +
                "name VARCHAR(30), " +
                "last_name VARCHAR(30), " +
                "age SMALLINT)";

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(createTableUsers);
            System.out.println("A table Users is created");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error creating");
        }
    }

    public void dropUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String dropTable = "DELETE FROM users";
            statement.executeUpdate(dropTable);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("This table does not exist");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
            String addUser = "INSERT INTO users (name, last_name, age) VALUES (?, ?, ?)";
        try {
            PreparedStatement statement = connection.prepareStatement(addUser);
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("User with the name " + name + " has been added to the database");
    }

    public void removeUserById(long id) {
        String removeUser = "DELETE FROM users WHERE id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(removeUser);
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> allUsers = new ArrayList<>();
        String request = "SELECT * FROM users";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(request);
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("last_name");
                Byte age = resultSet.getByte("age");
                allUsers.add(new User(name, lastName, age));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println(allUsers);
        return allUsers;
    }

    public void cleanUsersTable() {
        try {
            Statement statement = connection.createStatement();
            String cleanTable = "DELETE FROM users";
            statement.executeUpdate(cleanTable);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("The table is already empty");
        }
    }
}
