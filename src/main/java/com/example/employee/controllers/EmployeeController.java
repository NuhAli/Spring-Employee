package com.example.employee.controllers;

import com.example.employee.dtos.EmailPatch;
import com.example.employee.models.Employee;
import com.example.employee.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        return new ResponseEntity<>(employeeService.getAllEmployees(), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Employee> getSingleEmployee(@PathVariable Integer employeeId) {
        return new ResponseEntity<>(employeeService.getEmployee(employeeId), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId) {
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().body("Employee of id " + employeeId + " was removed.");
    }

    @PostMapping
    public ResponseEntity<String> addEmployee(@RequestBody Employee employee){
        employeeService.addEmployee(employee);
        return ResponseEntity.ok().body("Employee was added");
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<String> editEmployee(@PathVariable Integer employeeId, @RequestBody Employee employee){
        employeeService.editEmployee(employeeId,employee);
        return ResponseEntity.ok().body("Employee " + employeeId + " has been updated");
    }

    @PatchMapping("/{employeeId}")
    public ResponseEntity<String> editEmployeeEmail(@PathVariable Integer employeeId, @RequestBody EmailPatch emailPatch){
        employeeService.updateEmail(employeeId,emailPatch);
        return ResponseEntity.ok().body("Email was updated on employee " + employeeId);
    }

    @GetMapping("/lastname/{lastName}")
    public ResponseEntity<List<Employee>> getEmployeeByLastname(@PathVariable String lastName){
        return new ResponseEntity<>(employeeService.getByLastName(lastName),HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<String> numberOfEmployees(){
        Long count = employeeService.employeeCount();
        return new ResponseEntity<>("There are " + count + " employees at this company", HttpStatus.OK);
    }
}
