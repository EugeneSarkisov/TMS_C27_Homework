package com.teachmeskills.employee_info.controllers;

import com.teachmeskills.employee_info.model.Employee;
import com.teachmeskills.employee_info.util.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@Controller
@RequestMapping("/info")
public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();
    @GetMapping("/get/{id}")
    public String getInfo(@PathVariable(required = false) Integer id, Model model) {
        Employee employee = null;
        try {
            employee = employeeService.getInfo(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        model.addAttribute("employee", employee);
        return "get";
    }

    @GetMapping("/delete")
    public String getDeletePage(){
        return "delete";
    }

    @PostMapping("/delete")
    public String deleteUser(@RequestParam Integer id, Model model) {
        try {
            if (employeeService.checkEmployee(id)) {
                employeeService.deleteEmployee(id);
                model.addAttribute("status", "TRUE");
                model.addAttribute("info", "USER SUCCESSFULLY DELETED");
            } else {
                model.addAttribute("status", "FALSE");
                model.addAttribute("info", "USER DO NOT EXIST");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "delete-result";
    }

    @GetMapping("/change-login")
    public String getChangeLoginPage(){
        return "change-login";
    }

    @PostMapping("/change-login")
    public String changeLogin(@RequestParam Integer id, @RequestParam String newLogin, Model model) {
        try {
            if (employeeService.checkEmployee(id)) {
                employeeService.changeLogin(id, newLogin);
                model.addAttribute("status", "TRUE");
                model.addAttribute("info", "SUCCESSFULLY CHANGED LOGIN");
            } else {
                model.addAttribute("status", "FALSE");
                model.addAttribute("info", "USER DO NOT EXIST");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "change-login-result";
    }
    @GetMapping("/create")
    public String getCreatePage(){
        return "create";
    }

    @PostMapping("/create")
    public String createEmployee(@ModelAttribute Employee employee){
        try {
            employeeService.createEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "create";
    }
}

