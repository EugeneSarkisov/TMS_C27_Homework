package com.teachmeskills.student_info.util;

import com.teachmeskills.student_info.model.Student;

public class StudentOperations {
    public static Student createStudent(int id, String name, String surname, int groupID, double grade) {
        Student student = new Student(0,"", "", 0, 0);
        student.setName(name);
        student.setSurname(surname);
        student.setGroupID(groupID);
        student.setGrade(grade);
        return student;
    }
}
