package tn.esprit.gaspillagezero.entites.Supplier_Order_Management;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.Product;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level =  AccessLevel.PRIVATE)
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemID;

    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
