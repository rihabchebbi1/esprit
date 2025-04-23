package tn.esprit.gaspillagezero.entites.Supplier_Order_Management;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.User;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.Product;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer orderID;
    LocalDateTime deliveryDate;
    Integer quantity;

    @Enumerated(EnumType.STRING)
    OrderStatus orderStatus;

    @ManyToOne
    Ingredient ingredient;

    @ManyToOne
    Supplier supplier;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;




}