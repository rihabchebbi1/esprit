package tn.esprit.gaspillagezero.controllers.Staff_Management_Schedulingcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.TrainingSession;
import tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service.ITrainingSessionService;

import java.util.List;

@RestController
@RequestMapping("/api/training-sessions")
public class TrainingSessionController {

    @Autowired
    private ITrainingSessionService trainingSessionService;

    @PostMapping
    public ResponseEntity<TrainingSession> addTrainingSession(@RequestBody TrainingSession trainingSession) {
        TrainingSession savedSession = trainingSessionService.addTrainingSession(trainingSession);
        return new ResponseEntity<>(savedSession, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<TrainingSession> updateTrainingSession(@RequestBody TrainingSession trainingSession) {
        TrainingSession updatedSession = trainingSessionService.updateTrainingSession(trainingSession);
        return ResponseEntity.ok(updatedSession);
    }

    @DeleteMapping("/{idTrainingSession}")  // Fixed typo in path
    public ResponseEntity<Void> deleteTrainingSession(@PathVariable int idTrainingSession) {  // Changed from long to int
        trainingSessionService.deleteTrainingSession(idTrainingSession);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{idTrainingSession}")  // Fixed typo in parameter name
    public ResponseEntity<TrainingSession> retrieveTrainingSession(@PathVariable int idTrainingSession) {  // Changed from long to int
        TrainingSession session = trainingSessionService.retreiveTrainingSession(idTrainingSession);
        return ResponseEntity.ok(session);
    }

    @GetMapping
    public ResponseEntity<List<TrainingSession>> retrieveAllTrainingSessions() {
        List<TrainingSession> sessions = trainingSessionService.retreiveAllTrainingSessions();
        return ResponseEntity.ok(sessions);
    }
}