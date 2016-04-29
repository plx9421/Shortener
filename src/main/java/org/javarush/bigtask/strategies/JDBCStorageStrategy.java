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
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE if exists `storagestrategy`");
            statement.execute("CREATE TABLE `mydatabase`.`storagestrategy` (\n" +
                    "  `key` INT(8) NOT NULL,\n" +
                    "  `value` VARCHAR(255) NULL,\n" +
                    "  PRIMARY KEY (`key`),\n" +
                    "  UNIQUE INDEX `key_UNIQUE` (`key` ASC));");
        }
    }

    @Override
    public boolean containsKey(Long key) {
        return false;
    }

    @Override
    public boolean containsValue(String value) {
        return false;
    }

    @Override
    public void put(Long key, String value) {
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement()) {
//            statement.executeQuery("");
//            statement.executeUpdate("");

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Long getKey(String value) {
        return null;
    }

    @Override
    public String getValue(Long key) {
        return null;
    }
}
