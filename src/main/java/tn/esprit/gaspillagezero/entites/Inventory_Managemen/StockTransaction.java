package tn.esprit.gaspillagezero.entites.Inventory_Managemen;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"product"})
public class StockTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int StockTransactionID;
    @Enumerated(EnumType.STRING)
    @JsonProperty
    private StockTransactionType Type;
    private Date DateTransaction;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @JsonProperty
    private int ChangeQuantity;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference
    private Product product;


    public int getStockTransactionID() {
        return StockTransactionID;
    }

    public void setStockTransactionID(int stockTransactionID) {
        StockTransactionID = stockTransactionID;
    }

    public StockTransactionType getType() {
        return Type;
    }

    public void setType(StockTransactionType type) {
        Type = type;
    }

    public Date getDateTransaction() {
        return DateTransaction;
    }

    public void setDateTransaction(Date date) {
        DateTransaction = date;
    }

    public int getChangeQuantity() {
        return ChangeQuantity;
    }

    public void setChangeQuantity(int changeQuantity) {
        ChangeQuantity = changeQuantity;
    }
}
