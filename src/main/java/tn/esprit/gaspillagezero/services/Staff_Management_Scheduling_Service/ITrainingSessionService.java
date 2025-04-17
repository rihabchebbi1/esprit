package tn.esprit.gaspillagezero.services.Staff_Management_Scheduling_Service;

import tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling.TrainingSession;

import java.util.List;

public interface ITrainingSessionService {

    TrainingSession addTrainingSession(TrainingSession trainingSession);
    TrainingSession updateTrainingSession(TrainingSession trainingSession);
    void deleteTrainingSession(long idTrainingSession);
    TrainingSession retreiveTrainingSession(long idTrainingSession);
    List<TrainingSession> retreiveAllTrainingSessions();

}
