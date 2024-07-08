package com.teachmeskills.student_info.service;

import com.teachmeskills.student_info.model.Student;
import com.teachmeskills.student_info.util.PostgresDriverManager;
import com.teachmeskills.student_info.util.StudentOperations;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentService {
    public void addStudentToDB(Student student) throws SQLException {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("INSERT INTO students(id, name, surname, group_id, grade) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setInt(1, student.getId());
        preparedStatement.setString(2, student.getName());
        preparedStatement.setString(3, student.getSurname());
        preparedStatement.setInt(4, student.getGroupID());
        preparedStatement.setDouble(5, student.getGrade());
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public boolean ifStudentExist(int id) throws SQLException {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        if (!resultSet.next()) {
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

    public void deleteStudentFromDB(int id) throws SQLException {
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public Student showInfoAboutStudent(int id) throws SQLException {
        Student student = null;
        PostgresDriverManager driverManager = PostgresDriverManager.getInstance();
        Connection connection = driverManager.getConnection();
        PreparedStatement preparedStatement;
        ResultSet resultSet;
        preparedStatement = connection.prepareStatement("SELECT * FROM students WHERE id = ?");
        preparedStatement.setInt(1, id);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            student = StudentOperations.createStudent(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getDouble(5));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return student;
    }
}
