package tn.esprit.gaspillagezero.entites;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.ComplianceRecord;
import tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management.SafetyInspection;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or AUTO, SEQUENCE
    Integer Restaurantid;
    private String name;
    private String location;
    private String cuisineType;
    private String phoneNumber;
    private String email;
    private int seatingCapacity;
    private LocalTime openingTime;
    private LocalTime closingTime;
    private boolean isActive;

    @ToString.Exclude
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ComplianceRecord> complianceRecords = new ArrayList<>();

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SafetyInspection> safetyInspections = new ArrayList<>();

    //Getters & Setters

    public Integer getRestaurantid() {
        return Restaurantid;
    }

    public void setRestaurantid(Integer restaurantid) {
        Restaurantid = restaurantid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    public void setCuisineType(String cuisineType) {
        this.cuisineType = cuisineType;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public void setSeatingCapacity(int seatingCapacity) {
        this.seatingCapacity = seatingCapacity;
    }

    public LocalTime getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(LocalTime openingTime) {
        this.openingTime = openingTime;
    }

    public LocalTime getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(LocalTime closingTime) {
        this.closingTime = closingTime;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public List<ComplianceRecord> getComplianceRecords() {
        return complianceRecords;
    }

    public void setComplianceRecords(List<ComplianceRecord> complianceRecords) {
        this.complianceRecords = complianceRecords;
    }

    public List<SafetyInspection> getSafetyInspections() {
        return safetyInspections;
    }

    public void setSafetyInspections(List<SafetyInspection> safetyInspections) {
        this.safetyInspections = safetyInspections;
    }
}
