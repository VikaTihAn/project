package util;

import model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:postgresql://localhost:5433/postgres";
    private static final String name = "postgres";
    private static final String password = "12345";
    private static final String driver = "org.postgresql.Driver";

    public static Connection connection;

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
            try {
                Properties properties = new Properties();
                Configuration config = new Configuration();

                properties.put(Environment.DRIVER, driver);
                properties.put(Environment.URL, URL);
                properties.put(Environment.USER, name);
                properties.put(Environment.PASS, password);
                properties.put(Environment.SHOW_SQL, true);
                properties.put(Environment.FORMAT_SQL, true);
                properties.put(Environment.HBM2DDL_AUTO, "create-drop");
                properties.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");

                config.setProperties(properties).addAnnotatedClass(User.class);

                StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                        .applySettings(config.getProperties())
                        .build();

                sessionFactory = config.buildSessionFactory(registry);

                System.out.println("Connection OK");
            } catch (Exception e) {
                System.out.println("Connection ERROR");
                e.printStackTrace();
            }
        }
            return sessionFactory;
        }

        public static Connection getConnection () {
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
