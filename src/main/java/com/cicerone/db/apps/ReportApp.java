package com.cicerone.db.apps;

import com.cicerone.db.factory.ConnectionFactory;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class ReportApp {

    public static void main(String[] args) {

        try(Connection connection = ConnectionFactory.getConnection();
            PrintWriter writer = new PrintWriter( new FileWriter(("src/main/resources/html/courses.html"), false));) {

            connection.setAutoCommit(false);

            String sql = """
                         SELECT c.id, c.title, c.time_to_finish_in_hours, s.title, s.id
                            FROM Course c
                                JOIN Subcategory s ON c.subcategory_id = s.id
                            WHERE c.disabled = false;
                         """;
            String courseList = "";

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getResultSet();

                while(resultSet.next()) {
                    courseList += """
                              \t\t\t<li>
                              \t\t\t\t<h2>%d: %s</h2> 
                              \t\t\t\t<p>Duração total em horas: %dh</p>
                              \t\t\t\t<p>Subcategoria: %s (%d)</p>
                              \t\t\t</li>
                              """.formatted(resultSet.getLong(1), resultSet.getString(2),
                            resultSet.getInt(3), resultSet.getString(4), resultSet.getLong(5));
                }
            } catch (SQLException e) {
                connection.rollback();
                throw new RuntimeException(e.getMessage());
            }

            connection.commit();

            String htmlTemplate = """
                                  <html lang="pt-br">
                                  \t<head>
                                  \t\t<meta charset="utf-8">
                                  \t\t<title>Cicerone | Cursos Ativos </title>
                                  \t</head>
                                  \t<body>
                                  \t\t<h1>Cursos Ativos</h1>
                                  \t\t<ul>
                                  %s
                                  \t\t</ul>
                                  \t</body>
                                  </html>
                                  """.formatted(courseList);

            writer.println(htmlTemplate);

            System.out.println("Arquivo gerado com sucesso e disponível em 'src/main/resources/html/courses.html'");

        } catch (SQLException | IOException e) {
            throw new RuntimeException(e.getMessage());
        }

    }

}
