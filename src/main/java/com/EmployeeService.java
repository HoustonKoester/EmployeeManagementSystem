package com;

import java.util.List;

import com.EmployeeServiceImpl.EmployeeNotFound;

public interface EmployeeService {
public List<Employee> displayAllEmployees();
public double calculateYearlySalary(Employee e1);
public Employee findByEmployeeNo(int empNo);
public void updateEmployee();
public void deleteEmployee() throws EmployeeNotFound;
public void addEmployee();
}
