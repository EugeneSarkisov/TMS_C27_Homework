package com.teachmeskills.student_info.controllers;

import com.teachmeskills.student_info.model.Student;
import com.teachmeskills.student_info.service.StudentService;
import com.teachmeskills.student_info.util.StudentOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.sql.SQLException;

@Controller
public class StudentController {
    StudentService studentService = new StudentService();

    @GetMapping("/create")
    public String viewSetStudentPage() {
        return "create";
    }

    @PostMapping("/create")
    public ModelAndView createStudent(@RequestParam int id, @RequestParam String name, @RequestParam String surname, @RequestParam Integer groupID, @RequestParam Double grade, Model model) {
        Student student = StudentOperations.createStudent(id, name, surname, groupID, grade);
        try {
            studentService.addStudentToDB(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("student", student);
        return new ModelAndView("create-result.html");
    }

    @GetMapping("/create-result")
    public void viewStudent(Model model) {
        model.getAttribute("student");
    }

    @GetMapping("/delete")
    public String viewDeleteStudentPage() {
        return "delete";
    }

    @PostMapping("/delete")
    public ModelAndView deleteStudent(@RequestParam int id, Model model) {
        try {
            if (studentService.ifStudentExist(id)) {
                studentService.deleteStudentFromDB(id);
                model.addAttribute("result_text", "STUDENT SUCCESSFULLY DELETED");
            } else {
                model.addAttribute("result_text", "STUDENT ALREADY DELETED OR NOT EXIST");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("delete-result.html");
    }

    @GetMapping("/delete-result")
    public void deleteResult(Model model) {
        model.getAttribute("result_text");
    }

    @GetMapping("/show-student")
    public String viewShowStudentPage() {
        return "show-student";
    }

    @PostMapping("/show-student")
    public ModelAndView showStudentInfo(int id, Model model) {
        try {
            Student student = studentService.showInfoAboutStudent(id);
            model.addAttribute("student", student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return new ModelAndView("show-student-result.html");
    }

    @GetMapping("/show-student-result")
    public void showInfoResult(Model model) {
        model.getAttribute("student");
    }
}
