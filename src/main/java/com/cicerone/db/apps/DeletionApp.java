package com.cicerone.db.apps;

import com.cicerone.db.factory.ConnectionFactory;

import java.sql.*;
import java.util.Scanner;

public class DeletionApp {

    public static void main(String[] args) {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            Scanner scan = new Scanner(System.in);

            System.out.println("Digite o código do curso que deseja excluir: ");
            String code = scan.nextLine();

            String sql = """
                         DELETE FROM Course
                            WHERE code = ?;
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, code);
                preparedStatement.execute();
                connection.commit();

                int modifiedLines = preparedStatement.getUpdateCount();
                System.out.println("Deleção executada com sucesso. Número de linhas modificadas: " + modifiedLines);

            } catch(SQLException e) {
                connection.rollback();
                throw new RuntimeException(e.getMessage());
            }

        } catch(SQLException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
