package com.example.employeemanagement.service;

import com.example.employeemanagement.entity.Employee;

import java.util.List;

public interface EmployeeService {

    Employee createEmployee(Employee employee);

    List<Employee> getAllEmployees();

    Employee getEmployeeById(Long id);

    Employee updateEmployee(Long id, Employee employeeDetails);

    void deleteEmployee(Long id);
}
