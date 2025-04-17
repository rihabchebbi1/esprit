package tn.esprit.gaspillagezero.controllers.Staff_Management_Schedulingcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service.DashboardService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/dashboard")

public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/staff-statistics")
    public ResponseEntity<Map<String, Object>> getStaffStatistics() {
        return ResponseEntity.ok(dashboardService.getStaffStatistics());
    }

    @GetMapping("/monthly-statistics")
    public ResponseEntity<List<Map<String, Object>>> getMonthlyStatistics() {
        return ResponseEntity.ok(dashboardService.getMonthlyStatistics());
    }

    @GetMapping("/role-based-statistics")
    public ResponseEntity<Map<String, List<Map<String, Object>>>> getRoleBasedStatistics() {
        return ResponseEntity.ok(dashboardService.getRoleBasedStatistics());
    }
}