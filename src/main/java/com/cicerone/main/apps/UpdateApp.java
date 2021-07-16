package com.cicerone.main.apps;

import com.cicerone.db.dao.CourseDAO;
import com.cicerone.db.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateApp {

    public static void main(String[] args) {

        CourseDAO courseDAO = new CourseDAO();
        int modifiedLines = courseDAO.updateAllAsEnabled();

        System.out.println("\nAtualização realização com sucesso: " + modifiedLines + " linhas modificadas");

    }

}
