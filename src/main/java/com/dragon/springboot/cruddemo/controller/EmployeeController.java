package com.dragon.springboot.cruddemo.controller;


import com.dragon.springboot.cruddemo.entity.Employee;
import com.dragon.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private EmployeeService employeeService;


    //Employee Service injection
    @Autowired
    public EmployeeController (EmployeeService employeeService){
        this.employeeService = employeeService;
    }

//    @GetMapping
//    public String Home(){
//        return "redirect:/list";
//    }

    //this to save the user Data in the data base
    @PostMapping("/addNewEmployee")
    public String addEmployee(@ModelAttribute Employee employee){
        employeeService.save(employee);
        return "addEmployee";
    }

    //to display the add new employee form
    @GetMapping("/addNewEmployeeForm")
    public String addNewEmployeeForm(Model model){
        model.addAttribute("employee",new Employee());
        return "addEmployee";
    }
    //list all employees
    @GetMapping("/list")
    public String findAll(Model model){
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employees",employeeList);

        return "employeeList";
    }

}
