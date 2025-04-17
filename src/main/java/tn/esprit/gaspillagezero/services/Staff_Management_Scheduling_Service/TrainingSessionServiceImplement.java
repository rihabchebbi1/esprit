package tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.TrainingSession;
import tn.esprit.gaspillagezero.repository.Staff_Management_Scheduling_Repository.TrainingSessionRepository;

import java.util.List;

@Service
public class TrainingSessionServiceImplement implements ITrainingSessionService {

    @Autowired
    private TrainingSessionRepository trainingSessionRepository;

    @Override
    public TrainingSession addTrainingSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

    @Override
    public TrainingSession updateTrainingSession(TrainingSession trainingSession) {
        return trainingSessionRepository.save(trainingSession);
    }

    @Override
    public void deleteTrainingSession(long idTrainingSession) {  // Changed from long to int to match entity field type
        trainingSessionRepository.deleteById(idTrainingSession);
    }

    @Override
    public TrainingSession retreiveTrainingSession(long idTrainingSession) {  // Changed from long to int to match entity field type
        return trainingSessionRepository.findById(idTrainingSession)
                .orElseThrow(() -> new RuntimeException("Training Session not found with id: " + idTrainingSession));
    }

    @Override
    public List<TrainingSession> retreiveAllTrainingSessions() {
        return trainingSessionRepository.findAll();
    }
}