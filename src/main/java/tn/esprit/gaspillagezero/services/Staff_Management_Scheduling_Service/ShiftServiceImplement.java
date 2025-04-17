package tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Employee;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Shift;
import tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository.EmployeeRepository;
import tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository.ShiftRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class ShiftServiceImplement implements IShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public Shift addShift(Shift shift) {
        if (shift.getDate() == null) {
            shift.setDate(LocalDate.now());
        }

        return shiftRepository.save(shift);
    }

    @Override
    public Shift updateShift(Shift shift) {
        return shiftRepository.save(shift);
    }

    @Override
    public void deleteShift(long idShift) {  // Changed from long to int to match entity field type
        shiftRepository.deleteById(idShift);
    }

    @Override
    public Shift retreiveShift(long idShift) {  // Changed from long to int to match entity field type
        return shiftRepository.findById(idShift)
                .orElseThrow(() -> new RuntimeException("Shift not found with id: " + idShift));
    }

    @Override
    public List<Shift> retreiveAllShifts() {
        return shiftRepository.findAll();
    }

    @Override
    public Employee assignEmployeeToShifts(long idEmployee, List<Long> idShifts) {  // Changed List<Long> to List<Integer>
        // Retrieve existing shifts from their IDs
        List<Shift> shifts = shiftRepository.findAllById(idShifts);

        // Check if the employee exists
        Employee employee = employeeRepository.findById(idEmployee)
                .orElseThrow(() -> new RuntimeException("Employee not found with id: " + idEmployee));

        // Associate the shifts with the employee
        for (Shift shift : shifts) {
            shift.setEmployee(employee);
            shiftRepository.save(shift);
        }

        employee.getShifts().addAll(shifts);
        return employeeRepository.save(employee);
    }
}

