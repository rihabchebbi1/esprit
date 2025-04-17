package tn.esprit.gaspillagezero.entites.Inventory_Managemen;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SalesRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int SalesID;
    private int QuantitySold;
    private Date DateSale;
    private float Total;

    @ManyToOne
    @JsonBackReference
    private Product product;

    public int getSalesID() {
        return SalesID;
    }

    public void setSalesID(int salesID) {
        SalesID = salesID;
    }

    public int getQuantitySold() {
        return QuantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        QuantitySold = quantitySold;
    }

    public Date getDateSale() {
        return DateSale;
    }

    public void setDateSale(Date dateSale) {
        DateSale = dateSale;
    }
    public float getTotal() {return Total;}
    public void setTotal(float total) {Total = total;}
}
