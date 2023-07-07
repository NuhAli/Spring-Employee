package com.example.employee.services;

import com.example.employee.dtos.EmailPatch;
import com.example.employee.models.Employee;
import com.example.employee.repositories.EmployeeRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer id) {
        return employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
    }

    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    public void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    public void editEmployee(Integer id, Employee employee) {
        var newEmployee = Employee.builder().employeeId(id).firstName(employee.getFirstName()).lastName(employee.getLastName()).employeeEmail(employee.getEmployeeEmail()).build();
        employeeRepository.save(newEmployee);
    }

    public void updateEmail(Integer id, EmailPatch emailPatch){
        var employee = employeeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Employee not found"));
        employee.setEmployeeEmail(emailPatch.getEmployeeEmail());
        employeeRepository.save(employee);
    }

    public List<Employee> getByLastName(String lastName){
        return employeeRepository.findByLastNameIgnoreCase(lastName);
    }

    public Long employeeCount(){
        return employeeRepository.count();
    }
}
