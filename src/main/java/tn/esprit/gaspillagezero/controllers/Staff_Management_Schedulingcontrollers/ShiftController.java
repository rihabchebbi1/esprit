package tn.esprit.gaspillagezero.controllers.Staff_Management_Schedulingcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Employee;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Shift;
import tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service.IShiftService;

import java.util.List;

@RestController
@RequestMapping("/api/shifts")
public class ShiftController {

    @Autowired
    private IShiftService shiftService;

    @PostMapping
    public ResponseEntity<Shift> addShift(@RequestBody Shift shift) {
        Shift savedShift = shiftService.addShift(shift);
        return new ResponseEntity<>(savedShift, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Shift> updateShift(@RequestBody Shift shift) {
        Shift updatedShift = shiftService.updateShift(shift);
        return ResponseEntity.ok(updatedShift);
    }

    @DeleteMapping("/{idShift}")
    public ResponseEntity<Void> deleteShift(@PathVariable int idShift) {  // Changed from long to int
        shiftService.deleteShift(idShift);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idShift}")
    public ResponseEntity<Shift> retrieveShift(@PathVariable int idShift) {  // Changed from long to int
        Shift shift = shiftService.retreiveShift(idShift);
        return ResponseEntity.ok(shift);
    }

    @GetMapping
    public ResponseEntity<List<Shift>> retrieveAllShifts() {
        List<Shift> shifts = shiftService.retreiveAllShifts();
        return ResponseEntity.ok(shifts);
    }

    @PutMapping("/assign/{idEmployee}")  // Corrected parameter name
    public ResponseEntity<Employee> assignEmployeeToShifts(
            @PathVariable long idEmployee,
            @RequestBody List<Long> idShifts) {  // Changed from List<Long> to List<Integer>
        Employee employee = shiftService.assignEmployeeToShifts(idEmployee, idShifts);
        return ResponseEntity.ok(employee);
    }
}