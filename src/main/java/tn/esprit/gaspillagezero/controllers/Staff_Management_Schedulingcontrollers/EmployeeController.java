package tn.esprit.gaspillagezero.controllers.Staff_Management_Schedulingcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Employee;
import tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service.IEmployeeService;

import java.util.List;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        Employee savedEmployee = employeeService.addEmployee(employee);
        return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee) {
        Employee updatedEmployee = employeeService.updateEmployee(employee);
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{idEmployee}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable long idEmployee) {
        employeeService.deleteEmployee(idEmployee);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idEmployee}")
    public ResponseEntity<Employee> retrieveEmployee(@PathVariable long idEmployee) {
        Employee employee = employeeService.retreiveEmployee(idEmployee);
        return ResponseEntity.ok(employee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> retrieveAllEmployees() {
        List<Employee> employees = employeeService.retreiveAllEmployees();
        return ResponseEntity.ok(employees);
    }
}