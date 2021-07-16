package com.cicerone.db.dao;

import com.cicerone.exceptions.DAOException;
import com.cicerone.model.Subcategory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.cicerone.db.TransactionTemplate.doInTransactionWithResult;

public class SubcategoryDAO {

    public Optional<Subcategory> findByCode(String code) {

        String sql = """
                         SELECT s.id
                            FROM Subcategory s 
                            WHERE s.code = ?
                         """;

       return doInTransactionWithResult(sql, preparedStatement -> {

                preparedStatement.setString(1, code);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                if(resultSet.next()) {
                    Long id = resultSet.getLong(1);

                    Subcategory subcategory = new Subcategory();
                    subcategory.setId(id);
                    return Optional.of(subcategory);

                }
                return Optional.empty();

        } );

    }

}