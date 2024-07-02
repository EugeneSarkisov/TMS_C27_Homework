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
import java.time.LocalDate;

@WebServlet("/create")
public class UserCreationServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
            Connection connection = driverManager.getConnection();
            PreparedStatement preparedStatement;
            ResultSet resultSet;
            preparedStatement = connection.prepareStatement("INSERT INTO employees(employee_id, first_name, last_name" +
                    ", email, phone_number, hire_date, salary, commission_pct, department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            Employee employee = new Employee(Integer.parseInt(req.getParameter("employee_id")), req.getParameter("first_name"),
                    req.getParameter("last_name"), req.getParameter("email"), req.getParameter("phone_number"), Date.valueOf(req.getParameter("hire_date")),
                    Integer.parseInt(req.getParameter("salary")), Double.parseDouble(req.getParameter("commission_pct").replace(",", ".")), Integer.parseInt(req.getParameter("department_id")));
            preparedStatement.setInt(1, employee.getEmployee_id());
            preparedStatement.setString(2, employee.getFirst_name());
            preparedStatement.setString(3, employee.getLast_name());
            preparedStatement.setString(4, employee.getEmail());
            preparedStatement.setString(5, employee.getPhone_number());
            preparedStatement.setDate(6, employee.getHire_date());
            preparedStatement.setInt(7, employee.getSalary());
            preparedStatement.setDouble(8, employee.getCommission_pct());
            preparedStatement.setInt(9, employee.getDepartment_id());
            resultSet = preparedStatement.executeQuery();
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create.jsp").forward(req, resp);
    }
}
