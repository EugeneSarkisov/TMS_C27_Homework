package com.teachmeskills.servlet;

import com.teachmeskills.model.Employee;
import com.teachmeskills.util.PostgresDriverManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.*;

@WebServlet("/get")
public class UserInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = null;
        try {
            PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
            Connection connection = driverManager.getConnection();
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE employee_id = ?");
            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                employee = new Employee(resultSet.getInt("employee_id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("email"),
                        resultSet.getString("phone_number"),
                        resultSet.getDate("hire_date"),
                        resultSet.getInt("salary"),
                        resultSet.getDouble("commission_pct"),
                        resultSet.getInt("department_id"));
            }
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        req.setAttribute("employee", employee.toString());
        req.getServletContext().getRequestDispatcher("/get.jsp").forward(req, resp);
    }
}
