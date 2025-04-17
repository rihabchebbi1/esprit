package tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service;

import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Employee;

import java.util.List;

public interface IEmployeeService {

    Employee addEmployee(Employee employee);
    Employee updateEmployee(Employee employee);
    void deleteEmployee(long idEmployee);
    Employee retreiveEmployee(long idEmployee);
    List<Employee> retreiveAllEmployees();
}
