package com.dragon.springboot.cruddemo.controller;


import com.dragon.springboot.cruddemo.entity.Employee;
import com.dragon.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/deleteEmployee")
    public String DeleteEmployee(@RequestParam("employeeId") int id){
        employeeService.deleteById(id);
        return "redirect:/employees/list";
    }
    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/updateForm")
    public String updateEmployeeForm(Model model,@RequestParam("employeeId") int id){
        Employee tempEmp = employeeService.findById(id);
        model.addAttribute("employee",tempEmp);
        return "updateEmployee";
    }

    //this to save the user Data in the Database
    @PostMapping("/addNewEmployee")
    public String addEmployee(@ModelAttribute Employee employee){
        employeeService.save(employee);
        return "redirect:/employees/addNewEmployeeForm";
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
