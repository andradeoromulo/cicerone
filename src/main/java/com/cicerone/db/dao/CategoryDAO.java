package com.cicerone.db.dao;

import com.cicerone.db.factory.ConnectionFactory;
import com.cicerone.exceptions.DAOException;
import com.cicerone.model.Category;
import com.cicerone.model.Subcategory;

import java.sql.*;

public class CategoryDAO {

    public Category findById(Long id) {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            Category category;
            String sql = """
                         SELECT c.title, c.code
                            FROM Category c 
                            WHERE c.id = ?
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setLong(1, id);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                if(resultSet.next()) {
                    String title = resultSet.getString(1);
                    String code = resultSet.getString(2);

                    category = new Category(title, code);
                    category.setId(id);
                }
                else {
                    throw new SQLException();
                }

                connection.commit();

            } catch(SQLException e) {
                connection.rollback();
                throw new DAOException();
            }

            return category;

        } catch (SQLException e) {
            throw new DAOException();
        }

    }

}