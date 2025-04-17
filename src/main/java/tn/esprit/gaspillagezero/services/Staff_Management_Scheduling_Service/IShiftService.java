package tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service;

import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Employee;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Shift;

import java.util.List;

public interface IShiftService {

    Shift addShift(Shift shift);
    Shift updateShift(Shift shift);
    void deleteShift(long idShift);
    Shift retreiveShift(long idShift);
    List<Shift> retreiveAllShifts();

    //affecter un employe a plusieur shift
    Employee assignEmployeeToShifts(long idEmployee, List<Long> idShift);
}
