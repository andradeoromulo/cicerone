package com.cicerone.db;

import com.cicerone.db.factory.ConnectionFactory;
import com.cicerone.exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TransactionTemplate {

    public static <T> T doInTransactionWithResult(String sql, DoableInTransaction<T> doableInTransaction) {
        try (Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                T result = doableInTransaction.doInTransaction(preparedStatement);

                connection.commit();

                return result;
            } catch (SQLException e) {
                connection.rollback();
                throw new DAOException();
            }
        } catch (SQLException e) {
            throw new DAOException();
        }
    }

}
