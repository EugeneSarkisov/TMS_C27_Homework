package com.teachmeskills.employee_info.controllers;

import com.teachmeskills.employee_info.model.Employee;
import com.teachmeskills.employee_info.util.EmployeeInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.SQLException;

@Controller
@RequestMapping("/info")
public class InfoController {
    @GetMapping("/get/{id}")
    public String getInfo(@PathVariable(required = false) Integer id, Model model) {
        Employee employee = null;
        try {
            employee = EmployeeInfo.getInfo(id);
        } catch (SQLException e) {
            e.printStackTrace();
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
            if (EmployeeInfo.checkEmployee(id)) {
                EmployeeInfo.deleteEmployee(id);
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
            if (EmployeeInfo.checkEmployee(id)) {
                EmployeeInfo.changeLogin(id, newLogin);
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
    public String createEmployee(@RequestParam Integer employee_id, @RequestParam String first_name, @RequestParam String last_name, @RequestParam String email,
                                 @RequestParam String phone_number, @RequestParam Date hire_date, @RequestParam Integer salary, @RequestParam Double commission_pct,
                                 @RequestParam Integer department_id){
        Employee employee = new Employee(employee_id, first_name, last_name, email, phone_number,
                                               hire_date, salary, commission_pct, department_id);
        try {
            EmployeeInfo.createEmployee(employee);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "create";
    }
}

