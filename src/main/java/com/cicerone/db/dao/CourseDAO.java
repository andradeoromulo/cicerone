package com.cicerone.db.dao;

import com.cicerone.db.factory.ConnectionFactory;
import com.cicerone.exceptions.DAOException;
import com.cicerone.model.Course;
import com.cicerone.model.Subcategory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public List<Course> findAllEnabled() {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            List<Course> enabledCourses = new ArrayList<>();
            String sql = """
                         SELECT c.id, c.title, c.code, c.time_to_finish_in_hours, c.instructor, c.subcategory_id
                            FROM Course c
                            WHERE c.disabled = false;
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.execute();
                ResultSet resultSet = preparedStatement.getResultSet();

                while(resultSet.next()) {
                    Long id = resultSet.getLong(1);
                    String title = resultSet.getString(2);
                    String code = resultSet.getString(3);
                    int timetoFinishInHours = resultSet.getInt(4);
                    String instructor = resultSet.getString(5);
                    Long subcategoryId = resultSet.getLong(6);
                    Subcategory subcategory = new Subcategory();
                    subcategory.setId(subcategoryId);

                    Course course = new Course(title, code, timetoFinishInHours, instructor, subcategory);
                    course.setId(id);
                    enabledCourses.add(course);
                }

                connection.commit();

            } catch (SQLException | DAOException e) {
                connection.rollback();
                throw new DAOException();
            }

            return enabledCourses;

        } catch (SQLException e) {
            throw new DAOException();
        }

    }

    public void save(Course course) {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            String sql = """
                         INSERT INTO Course (title, code, time_to_finish_in_hours, disabled, instructor, subcategory_id)
                            VALUES (?, ?, ?, ?, ?, ?)
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

                preparedStatement.setString(1, course.getTitle());
                preparedStatement.setString(2, course.getCode());
                preparedStatement.setInt(3, course.getTimeToFinishInHours());
                preparedStatement.setBoolean(4, course.isDisabled());
                preparedStatement.setString(5, course.getInstructor());
                preparedStatement.setLong(6, course.getSubcategoryId());

                preparedStatement.execute();

                ResultSet resultSet = preparedStatement.getGeneratedKeys();

                if(resultSet.next()) {
                    course.setId(resultSet.getLong(1));
                } else {
                    throw new SQLException();
                }

                connection.commit();

            } catch(SQLException | DAOException e) {
                connection.rollback();
                throw new DAOException();
            }

        } catch (SQLException e) {
            throw new DAOException();
        }

    }

    public int updateAllAsEnabled() {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            int modifiedLines = 0;
            String sql = """
                         UPDATE Course
                            SET disabled = false;
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.execute();
                connection.commit();

                modifiedLines = preparedStatement.getUpdateCount();

            } catch(SQLException e) {
                connection.rollback();
                throw new DAOException();
            }

            return modifiedLines;

        } catch (SQLException e) {
            throw new DAOException();
        }

    }

    public void deleteByCode(String code) {

        try(Connection connection = ConnectionFactory.getConnection()) {

            connection.setAutoCommit(false);

            String sql = """
                         DELETE FROM Course
                            WHERE code = ?;
                         """;

            try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

                preparedStatement.setString(1, code);
                preparedStatement.execute();

                connection.commit();

            } catch(SQLException e) {
                connection.rollback();
                throw new DAOException();
            }

        } catch (SQLException e) {
            throw new DAOException();
        }

    }

}