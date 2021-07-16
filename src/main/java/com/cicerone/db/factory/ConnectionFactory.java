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

        if(dataSource == null) dataSource = new ComboPooledDataSource();

        return dataSource.getConnection();
    }

}
