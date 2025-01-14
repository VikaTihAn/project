package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
   private static final String URL = "jdbc:postgresql://localhost:5433/postgres";
   private static final String name = "postgres";
   private static final String password = "12345";

   private static Connection connection;

   public static Connection getConnection() {
           try {
               Class.forName("org.postgresql.Driver");
               connection = DriverManager.getConnection(URL, name, password);
               System.out.println("Successful connection");
           } catch (SQLException | ClassNotFoundException e) {
               System.out.println("Connection error");
           }
           return connection;
       }
   }
