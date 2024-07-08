package com.teachmeskills.student_info.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
    private int id;
    private String name;
    private String surname;
    private int groupID;
    private double grade;
}

