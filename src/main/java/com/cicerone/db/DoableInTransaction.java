package com.cicerone.db;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DoableInTransaction<T> {

    T doInTransaction (PreparedStatement preparedStatement) throws SQLException;

}
