package tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Shift;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Shift;

import java.util.List;

@Repository
public interface ShiftRepository extends JpaRepository<Shift, Long> {
    @Query("SELECT COUNT(s) FROM Shift s")
    long countShifts();

    @Query("SELECT COUNT(s) FROM Shift s WHERE MONTH(s.date) = MONTH(CURRENT_DATE) AND YEAR(s.date) = YEAR(CURRENT_DATE)")
    int countShiftsThisMonth();

    @Query("SELECT MONTH(s.date) as month, COUNT(s) as count FROM Shift s WHERE YEAR(s.date) = YEAR(CURRENT_DATE) GROUP BY MONTH(s.date) ORDER BY MONTH(s.date)")
    List<Object[]> getMonthlyShiftCounts();

    @Query("SELECT e.role as role, COUNT(s) as count FROM Shift s JOIN s.employee e GROUP BY e.role")
    List<Object[]> getShiftsCountByEmployeeRole();

    @Query("SELECT COUNT(s) FROM Shift s WHERE s.employee.idEmployee = ?1")
    long countShiftsByEmployee(int employeeId);
}