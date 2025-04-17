package tn.esprit.gaspillagezero.entites.Compliance_Food_Safety_Management;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import tn.esprit.gaspillagezero.entites.Restaurant;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SafetyInspection {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // or AUTO, SEQUENCE
    Long InspectionID;


    @NotBlank(message = "Inspector name is required")
    private String inspector_name;

    @NotNull(message = "Inspection date is required")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date inspection_date;

    private String premises_name;
    private String address;

    @NotNull(message = "Inspection type is required")
    private InspectionType inspectionType;

    private InspectionStatus inspectionStatus;
    private String infractions;
    private Integer crucial_infractions;
    private Integer significant_infractions;
    private Integer minor_infractions;
    private Integer corrected_during_inspection;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date report_time;
    private String Description;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date reinspectionDate;
    private Boolean isOutOfBusiness;




   // @ManyToOne(cascade = CascadeType.MERGE)
    @ManyToOne
    @JoinColumn(name = "restaurant_restaurant_id")
    private Restaurant restaurant;

    //GeStters & Setters

    public Long getInspectionID() {
        return InspectionID;
    }

    public void setInspectionID(Long inspectionID) {
        InspectionID = inspectionID;
    }

    public String getInspector_name() {
        return inspector_name;
    }

    public void setInspector_name(String inspector_name) {
        this.inspector_name = inspector_name;
    }

    public Date getInspection_date() {
        return inspection_date;
    }

    public void setInspection_date(Date inspection_date) {
        this.inspection_date = inspection_date;
    }

    public String getPremises_name() {
        return premises_name;
    }

    public void setPremises_name(String premises_name) {
        this.premises_name = premises_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public InspectionType getInspectionType() {
        return inspectionType;
    }

    public void setInspectionType(InspectionType inspectionType) {
        this.inspectionType = inspectionType;
    }

    /*public InspectionStatus getInspectionStatus() {
        return inspectionStatus;
    }*/

    /*public void setInspectionStatus(InspectionStatus inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }*/

    public String getInfractions() {
        return infractions;
    }

    public void setInfractions(String infractions) {
        this.infractions = infractions;
    }

    public Integer getCrucial_infractions() {
        return crucial_infractions;
    }

    public void setCrucial_infractions(Integer crucial_infractions) {
        this.crucial_infractions = crucial_infractions;
    }

    public Integer getSignificant_infractions() {
        return significant_infractions;
    }

    public void setSignificant_infractions(Integer significant_infractions) {
        this.significant_infractions = significant_infractions;
    }

    public Integer getMinor_infractions() {
        return minor_infractions;
    }

    public void setMinor_infractions(Integer minor_infractions) {
        this.minor_infractions = minor_infractions;
    }

    public Integer getCorrected_during_inspection() {
        return corrected_during_inspection;
    }

    public void setCorrected_during_inspection(Integer corrected_during_inspection) {
        this.corrected_during_inspection = corrected_during_inspection;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Date getReinspectionDate() {
        return reinspectionDate;
    }

    public void setReinspectionDate(Date reinspectionDate) {
        this.reinspectionDate = reinspectionDate;
    }

    public Boolean getOutOfBusiness() {
        return isOutOfBusiness;
    }

    public void setOutOfBusiness(Boolean outOfBusiness) {
        isOutOfBusiness = outOfBusiness;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public InspectionStatus getInspectionStatus() {
        return inspectionStatus;
    }

    public void setInspectionStatus(InspectionStatus inspectionStatus) {
        this.inspectionStatus = inspectionStatus;
    }
}