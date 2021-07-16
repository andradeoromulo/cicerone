package com.cicerone.exceptions;

public class DAOException extends RuntimeException {

    public DAOException() {
        super("An error occurred during data source manipulation");
    }
}
