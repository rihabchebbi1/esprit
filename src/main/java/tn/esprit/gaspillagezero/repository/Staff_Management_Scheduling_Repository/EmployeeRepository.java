package tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.Employee;

import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    @Query("SELECT COUNT(e) FROM Employee e")
    long countEmployees();

    @Query("SELECT AVG(e.salary) FROM Employee e")
    double getAverageEmployeeSalary();

    @Query("SELECT e.role as role, COUNT(e) as count FROM Employee e GROUP BY e.role")
    List<Object[]> getEmployeeCountByRole();

    @Query("SELECT COUNT(e) FROM Employee e WHERE MONTH(e.hiredate) = MONTH(CURRENT_DATE) AND YEAR(e.hiredate) = YEAR(CURRENT_DATE)")
    int countEmployeesHiredThisMonth();

    @Query("SELECT MONTH(e.hiredate) as month, COUNT(e) as count FROM Employee e WHERE YEAR(e.hiredate) = YEAR(CURRENT_DATE) GROUP BY MONTH(e.hiredate) ORDER BY MONTH(e.hiredate)")
    List<Object[]> getMonthlyEmployeeHires();
}