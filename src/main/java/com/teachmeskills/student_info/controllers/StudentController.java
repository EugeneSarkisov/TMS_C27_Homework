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
    public ModelAndView createStudent(@ModelAttribute Student student, Model model) {
        try {
            studentService.addStudentToDB(student);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("student", student);
        return new ModelAndView("show-result");
    }

    @GetMapping("/show-result")
    public String viewStudent(Model model) {
        model.getAttribute("student");
        return "show-result";
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
        return new ModelAndView("delete-result");
    }

    @GetMapping("/delete-result")
    public String deleteResult(Model model) {
        model.getAttribute("result_text");
        return "delete-result";
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
        return new ModelAndView("show-result");
    }
}
