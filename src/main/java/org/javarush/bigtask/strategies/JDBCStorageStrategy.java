package org.javarush.bigtask.strategies;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.*;

/**
 * Created by Alexey on 29.04.2016.
 */
public class JDBCStorageStrategy implements StorageStrategy {
    private final String URL = "jdbc:mysql://localhost:3306/mydatabase?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "SungSung743";

    public JDBCStorageStrategy() throws SQLException {
        Driver driver = new FabricMySQLDriver();
        DriverManager.registerDriver(driver);

//        Class.forName("com.mysql.jdbc.Driver").newInstance();
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE if exists `storagestrategy`");
            statement.execute("CREATE TABLE `mydatabase`.`storagestrategy` (\n" +
                    "  `hash_key` INT(8) NOT NULL,\n" +
                    "  `ss_value` VARCHAR(255) NULL,\n" +
                    "  PRIMARY KEY (`hash_key`),\n" +
                    "  UNIQUE INDEX `key_UNIQUE` (`hash_key` ASC));");
        }
    }

    @Override
    public boolean containsKey(Long key) {
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        return getKey(value) != null;
    }

    @Override
    public void put(Long key, String value) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sqlString = "INSERT INTO storagestrategy VALUES (" + key + ",'" + value + "');";
            statement.execute(sqlString);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Long getKey(String value) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             PreparedStatement st = connection.prepareStatement("SELECT hash_key FROM storagestrategy WHERE ss_value = ?")) {
            st.setString(1, value);
            ResultSet resultSet = st.executeQuery();
            if (resultSet != null && resultSet.first()) {
                return resultSet.getLong("hash_key");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//             Statement statement = connection.createStatement()) {
//
//            String sqlString = "SELECT hash_key FROM storagestrategy WHERE ss_value='" + value + "';";
//            ResultSet resultSet = statement.executeQuery(sqlString);
//            if (resultSet != null && resultSet.first()) {
//                return resultSet.getLong("hash_key");
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }

        return null;
    }

    @Override
    public String getValue(Long key) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            String sqlString = "SELECT ss_value FROM storagestrategy WHERE hash_key=" + key + ";";
            ResultSet resultSet = statement.executeQuery(sqlString);
            if (resultSet != null && resultSet.first()) {
                return resultSet.getString("ss_value");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
