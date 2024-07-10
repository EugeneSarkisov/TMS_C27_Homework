package com.teachmeskills.employee_info.util;

import com.teachmeskills.employee_info.model.Employee;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class EmployeeService {
    public Employee getInfo(int id) throws SQLException {
        Employee employee = null;
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE employee_id = ?");
        preparedStatement.setInt(1, id);
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
        return employee;
    }

    public void deleteEmployee(int id) throws SQLException {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM employees WHERE employee_id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public boolean checkEmployee(int id) throws SQLException {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        preparedStatement = connection.prepareStatement("SELECT * FROM employees WHERE employee_id = ?");
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if(!resultSet.next()){
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return false;
        } else {
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return true;
        }
    }

    public void changeLogin(int id, String newLogin) throws SQLException {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("UPDATE employees SET first_name = ? WHERE employee_id = ?");
        preparedStatement.setString(1, newLogin);
        preparedStatement.setInt(2, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public void createEmployee(Employee employee) throws SQLException {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO employees(employee_id, first_name, last_name" +
                ", email, phone_number, hire_date, salary, commission_pct, department_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, employee.getEmployee_id());
        preparedStatement.setString(2, employee.getFirst_name());
        preparedStatement.setString(3, employee.getLast_name());
        preparedStatement.setString(4, employee.getEmail());
        preparedStatement.setString(5, employee.getPhone_number());
        preparedStatement.setDate(6, employee.getHire_date());
        preparedStatement.setInt(7, employee.getSalary());
        preparedStatement.setDouble(8, employee.getCommission_pct());
        preparedStatement.setInt(9, employee.getDepartment_id());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }
}
