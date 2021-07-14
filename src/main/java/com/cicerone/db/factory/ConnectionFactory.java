package com.cicerone.db.factory;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static DataSource dataSource = null;

    private ConnectionFactory(){}

    public static Connection getConnection() throws SQLException {

        if(dataSource == null) dataSource = initializeDataSource();

        return dataSource.getConnection();
    }

    private static ComboPooledDataSource initializeDataSource() {
        ComboPooledDataSource pool = new ComboPooledDataSource();
        pool.setJdbcUrl("jdbc:mysql://localhost/cicerone?useTimezone=true&serverTimezone=UTC");
        pool.setUser("root");
        pool.setPassword("");
        pool.setMaxPoolSize(10);

        return pool;
    }

}
