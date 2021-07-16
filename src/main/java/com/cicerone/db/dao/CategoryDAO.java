package com.cicerone.db.dao;

import com.cicerone.exceptions.DAOException;
import com.cicerone.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static com.cicerone.db.TransactionTemplate.doInTransactionWithResult;

public class CategoryDAO {

    public Optional<Category> findById(Long id) {

        String sql = """
                         SELECT c.title, c.code
                            FROM Category c
                            WHERE c.id = ?
                         """;

        return doInTransactionWithResult(sql, preparedStatement -> {
                preparedStatement.setLong(1, id);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                if(resultSet.next()) {
                    String title = resultSet.getString(1);
                    String code = resultSet.getString(2);

                    Category category = new Category(title, code);
                    category.setId(id);
                    return Optional.of(category);
                }

                return Optional.empty();
        });

    }

}