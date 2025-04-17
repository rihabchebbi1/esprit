package tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Employee;
import tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplement implements IEmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(long idEmployee) {
        employeeRepository.deleteById(idEmployee);
    }

    @Override
    public Employee retreiveEmployee(long idEmployee) {
        return employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + idEmployee));
    }

    @Override
    public List<Employee> retreiveAllEmployees() {
        return employeeRepository.findAll();
    }
}