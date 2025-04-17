package tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.TrainingSession;

import java.util.List;

@Repository
public interface TrainingSessionRepository extends JpaRepository<TrainingSession, Long> {
    @Query("SELECT COUNT(t) FROM TrainingSession t")
    long countTrainingSessions();

    @Query("SELECT COUNT(t) FROM TrainingSession t WHERE MONTH(t.date) = MONTH(CURRENT_DATE) AND YEAR(t.date) = YEAR(CURRENT_DATE)")
    int countTrainingSessionsThisMonth();

    @Query("SELECT MONTH(t.date) as month, COUNT(t) as count FROM TrainingSession t WHERE YEAR(t.date) = YEAR(CURRENT_DATE) GROUP BY MONTH(t.date) ORDER BY MONTH(t.date)")
    List<Object[]> getMonthlyTrainingCounts();

    @Query("SELECT t.topic as topic, COUNT(t) as count FROM TrainingSession t GROUP BY t.topic")
    List<Object[]> getTrainingSessionCountByTopic();

    @Query("SELECT e.role as role, COUNT(t) as count FROM TrainingSession t JOIN t.employee e GROUP BY e.role")
    List<Object[]> getTrainingCountByEmployeeRole();
}