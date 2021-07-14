package com.cicerone.db.apps;

import com.cicerone.db.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class UpdateApp {

    public static void main(String[] args) {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            String sql = """
                         UPDATE Course
                            SET disabled = false;
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.execute();
                connection.commit();

                int modifiedLines = preparedStatement.getUpdateCount();
                System.out.println("Atualização executada com sucesso. Número de linhas modificadas: " + modifiedLines);

            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
