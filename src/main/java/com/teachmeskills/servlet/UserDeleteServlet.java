package com.teachmeskills.servlet;

import com.teachmeskills.util.PostgresDriverManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/delete")
public class UserDeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE employee_id = ?");
        preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
        resultSet = preparedStatement.executeQuery();
        if (!resultSet.isBeforeFirst()) {
            req.setAttribute("status", "FALSE");
            req.setAttribute("info", "USER DO NOT EXIST");
            req.getServletContext().getRequestDispatcher("/delete.jsp").forward(req, resp);
        } else {
            preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE employee_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            resultSet = preparedStatement.executeQuery();
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
    } catch (
    SQLException e) {
        e.printStackTrace();
    }
    }
}
