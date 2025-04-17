package tn.esprit.gaspillagezero.entites.Inventory_Managemen;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class StockTransactionDTO {
    private int StockTransactionID;
    private StockTransactionType Type;
    private Date DateTransaction;
    private int ChangeQuantity;
    private String productName;

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

    public void setDateTransaction(Date dateTransaction) {
        DateTransaction = dateTransaction;
    }

    public int getChangeQuantity() {
        return ChangeQuantity;
    }

    public void setChangeQuantity(int changeQuantity) {
        ChangeQuantity = changeQuantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
}