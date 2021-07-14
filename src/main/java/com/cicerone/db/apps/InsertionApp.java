package com.cicerone.db.apps;

import com.cicerone.db.factory.ConnectionFactory;

import java.sql.*;

public class InsertionApp {

    public static void main(String[] args) {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            String sql = """
                         INSERT INTO Course (title, code, time_to_finish_in_hours, disabled, instructor, subcategory_id)
                            VALUES ('Java: Reflection API', 'java-reflection', 8, false, 'Paulo Silveira', 1)
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if(resultSet.next()) {
                    System.out.println("Curso inserido com o id: " + resultSet.getInt(1));
                } else {
                    throw new RuntimeException("An error occurred while saving new course");
                }

            } catch(SQLException e) {
                connection.rollback();
                throw new RuntimeException(e.getMessage());
            }

            connection.commit();

        }catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
