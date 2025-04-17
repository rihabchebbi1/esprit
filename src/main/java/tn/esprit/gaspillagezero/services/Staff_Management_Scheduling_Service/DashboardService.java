package tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository.EmployeeRepository;
import tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository.ShiftRepository;
import tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository.TrainingSessionRepository;


import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DashboardService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    public Map<String, Object> getStaffStatistics() {
        Map<String, Object> stats = new HashMap<>();

        // Basic counts
        stats.put("totalEmployees", employeeRepository.countEmployees());
        stats.put("totalShifts", shiftRepository.countShifts());
        stats.put("totalTrainingSessions", trainingSessionRepository.countTrainingSessions());

        // Average salary
        stats.put("averageEmployeeSalary", employeeRepository.getAverageEmployeeSalary());

        // This month's counts
        stats.put("shiftsThisMonth", shiftRepository.countShiftsThisMonth());
        stats.put("trainingSessionsThisMonth", trainingSessionRepository.countTrainingSessionsThisMonth());

        // Employee role distribution
        List<Object[]> roleCounts = employeeRepository.getEmployeeCountByRole();
        List<Map<String, Object>> roleDistribution = new ArrayList<>();

        long totalEmployees = (long) stats.get("totalEmployees");
        for (Object[] row : roleCounts) {
            String role = (String) row[0];
            long count = ((Number) row[1]).longValue();
            double percentage = totalEmployees > 0 ? (count * 100.0 / totalEmployees) : 0;

            Map<String, Object> roleData = new HashMap<>();
            roleData.put("role", role);
            roleData.put("count", count);
            roleData.put("percentage", percentage);

            roleDistribution.add(roleData);
        }

        stats.put("roleDistribution", roleDistribution);

        return stats;
    }

    public List<Map<String, Object>> getMonthlyStatistics() {
        List<Map<String, Object>> monthlyStats = new ArrayList<>();
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] months = dfs.getMonths();

        // Initialize data structure
        Map<Integer, Map<String, Object>> monthMap = new HashMap<>();
        for (int i = 0; i < 12; i++) {
            Map<String, Object> monthStat = new HashMap<>();
            monthStat.put("month", months[i]);
            monthStat.put("shiftsCount", 0L);
            monthStat.put("trainingsCount", 0L);
            monthStat.put("employeeCount", 0L);
            monthMap.put(i + 1, monthStat);
        }

        // Fill with employee data
        List<Object[]> monthlyEmployeeHires = employeeRepository.getMonthlyEmployeeHires();
        for (Object[] row : monthlyEmployeeHires) {
            int month = ((Number) row[0]).intValue();
            long count = ((Number) row[1]).longValue();
            if (monthMap.containsKey(month)) {
                monthMap.get(month).put("employeeCount", count);
            }
        }

        // Fill with shifts data
        List<Object[]> monthlyShiftCounts = shiftRepository.getMonthlyShiftCounts();
        for (Object[] row : monthlyShiftCounts) {
            int month = ((Number) row[0]).intValue();
            long count = ((Number) row[1]).longValue();
            if (monthMap.containsKey(month)) {
                monthMap.get(month).put("shiftsCount", count);
            }
        }

        // Fill with training data
        List<Object[]> monthlyTrainingCounts = trainingSessionRepository.getMonthlyTrainingCounts();
        for (Object[] row : monthlyTrainingCounts) {
            int month = ((Number) row[0]).intValue();
            long count = ((Number) row[1]).longValue();
            if (monthMap.containsKey(month)) {
                monthMap.get(month).put("trainingsCount", count);
            }
        }

        // Convert map to list
        for (Map<String, Object> monthData : monthMap.values()) {
            monthlyStats.add(monthData);
        }

        return monthlyStats;
    }

    public Map<String, List<Map<String, Object>>> getRoleBasedStatistics() {
        Map<String, List<Map<String, Object>>> roleStats = new HashMap<>();

        // Get shifts by role
        List<Object[]> shiftsByRole = shiftRepository.getShiftsCountByEmployeeRole();
        List<Map<String, Object>> shiftRoleStats = convertToMapList(shiftsByRole, "role", "count");
        roleStats.put("shiftsByRole", shiftRoleStats);

        // Get trainings by role
        List<Object[]> trainingsByRole = trainingSessionRepository.getTrainingCountByEmployeeRole();
        List<Map<String, Object>> trainingRoleStats = convertToMapList(trainingsByRole, "role", "count");
        roleStats.put("trainingsByRole", trainingRoleStats);

        // Get trainings by topic
        List<Object[]> trainingsByTopic = trainingSessionRepository.getTrainingSessionCountByTopic();
        List<Map<String, Object>> trainingTopicStats = convertToMapList(trainingsByTopic, "topic", "count");
        roleStats.put("trainingsByTopic", trainingTopicStats);

        return roleStats;
    }

    private List<Map<String, Object>> convertToMapList(List<Object[]> data, String keyName, String valueName) {
        List<Map<String, Object>> result = new ArrayList<>();

        for (Object[] row : data) {
            Map<String, Object> item = new HashMap<>();
            item.put(keyName, row[0]);
            item.put(valueName, row[1]);
            result.add(item);
        }

        return result;
    }
}
