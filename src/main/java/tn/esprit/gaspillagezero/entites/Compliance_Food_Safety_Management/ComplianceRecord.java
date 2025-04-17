package tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.gaspillagezero.entites.Restaurant;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ComplianceRecord {

    @Id
    Long ComplianceId ;
    Long ComplianceScore;
    String Regulation,ViolationPrediction,AutomatedTempLog;
    //ComplianceStatus Status ;
    Date DateChecked,AuditDate,CreatedAt,UpdatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_restaurant_id")
    private Restaurant restaurant;


    //getters & setters


    public Long getComplianceId() {
        return ComplianceId;
    }

    public void setComplianceId(Long complianceId) {
        ComplianceId = complianceId;
    }

    public Long getComplianceScore() {
        return ComplianceScore;
    }

    public void setComplianceScore(Long complianceScore) {
        ComplianceScore = complianceScore;
    }

    public String getRegulation() {
        return Regulation;
    }

    public void setRegulation(String regulation) {
        Regulation = regulation;
    }


    public String getViolationPrediction() {
        return ViolationPrediction;
    }

    public void setViolationPrediction(String violationPrediction) {
        ViolationPrediction = violationPrediction;
    }

    public String getAutomatedTempLog() {
        return AutomatedTempLog;
    }

    public void setAutomatedTempLog(String automatedTempLog) {
        AutomatedTempLog = automatedTempLog;
    }

   /* public ComplianceStatus getStatus() {
        return Status;
    }

    public void setStatus(ComplianceStatus status) {
        Status = status;
    }*/

    public Date getDateChecked() {
        return DateChecked;
    }

    public void setDateChecked(Date dateChecked) {
        DateChecked = dateChecked;
    }

    public Date getAuditDate() {
        return AuditDate;
    }

    public void setAuditDate(Date auditDate) {
        AuditDate = auditDate;
    }

    public Date getCreatedAt() {
        return CreatedAt;
    }

    public void setCreatedAt(Date createdAt) {
        CreatedAt = createdAt;
    }

    public Date getUpdatedAt() {
        return UpdatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        UpdatedAt = updatedAt;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }
}
