package tn.esprit.gaspillagezero.entites.Supplier_Order_Management;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer supplierID;
    String name;
    String contactInfo;
    float ratings;
    String productCatalog;



}