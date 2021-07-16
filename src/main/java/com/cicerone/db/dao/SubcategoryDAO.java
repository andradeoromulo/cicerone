package com.cicerone.db.dao;

import com.cicerone.db.factory.ConnectionFactory;
import com.cicerone.exceptions.DAOException;
import com.cicerone.model.Category;
import com.cicerone.model.Subcategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubcategoryDAO {

    public Subcategory findByCode(String code) {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            Subcategory subcategory;
            String sql = """
                         SELECT s.id, s.title, s.category_id
                            FROM Subcategory s 
                            WHERE s.code = ?
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, code);
                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                if(resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String title = resultSet.getString(2);
                    Long categoryId = resultSet.getLong(3);

                    CategoryDAO categoryDAO = new CategoryDAO();
                    Category parentCategory = categoryDAO.findById(categoryId);

                    subcategory = new Subcategory(title, code, parentCategory);
                    subcategory.setId(id);
                }
                else {
                    throw new SQLException();
                }

                connection.commit();

            } catch(SQLException | DAOException e) {
                connection.rollback();
                throw new DAOException();
            }

            return subcategory;

        } catch (SQLException e) {
            throw new DAOException();
        }

    }

}