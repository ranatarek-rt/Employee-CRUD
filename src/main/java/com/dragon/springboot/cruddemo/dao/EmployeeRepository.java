package com.dragon.springboot.cruddemo.dao;

import com.dragon.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

   //Use Query method to sort the users list
    public List<Employee> findAllByOrderByFirstNameAsc();

}
