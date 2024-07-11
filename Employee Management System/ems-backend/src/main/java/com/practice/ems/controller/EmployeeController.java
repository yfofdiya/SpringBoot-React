package com.practice.ems.controller;

import com.practice.ems.dto.EmployeeDTO;
import com.practice.ems.helper.Response;
import com.practice.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Response> createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employee = employeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(new Response(employee, "New employee created"), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Response> getAllEmployees() {
        List<EmployeeDTO> allEmployees = employeeService.getAllEmployees();
        return new ResponseEntity<>(new Response(allEmployees, "Fetched all employees"), HttpStatus.OK);
    }

    @GetMapping("/{employeeId}")
    public ResponseEntity<Response> getEmployeeById(@PathVariable Long employeeId) {
        EmployeeDTO employee = employeeService.getEmployeeById(employeeId);
        return new ResponseEntity<>(new Response(employee, "Fetch employee by id"), HttpStatus.OK);
    }

    @PutMapping("/{employeeId}")
    public ResponseEntity<Response> updateEmployee(@PathVariable Long employeeId, @RequestBody EmployeeDTO employeeDTO) {
        EmployeeDTO employee = employeeService.updateEmployee(employeeId, employeeDTO);
        return new ResponseEntity<>(new Response(employee, "Employee updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Response> deleteEmployeeById(@PathVariable Long employeeId) {
        employeeService.deleteEmployeeById(employeeId);
        return new ResponseEntity<>(new Response(null, "Employee deleted"), HttpStatus.OK);
    }
}
