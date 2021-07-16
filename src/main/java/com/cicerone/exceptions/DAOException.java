package com.cicerone.exceptions;

import java.sql.SQLException;

public class DAOException extends RuntimeException {

    public DAOException() {
        super("An error occurred during data source manipulation");
    }

    public DAOException(SQLException ex) {
        super(ex);
    }
}
