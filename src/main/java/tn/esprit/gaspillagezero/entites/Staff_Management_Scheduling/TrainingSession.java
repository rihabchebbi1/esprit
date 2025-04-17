package tn.esprit.gaspillagezero.entites.Staff_Management_Scheduling;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class TrainingSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTrainingSession;

    private String topic;
    private LocalDate date;
    private String trainer;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getIdTrainingSession() {
        return idTrainingSession;
    }

    public void setIdTrainingSession(Integer idTrainingSession) {
        this.idTrainingSession = idTrainingSession;
    }

    @ManyToOne
    private Employee employee;
}
