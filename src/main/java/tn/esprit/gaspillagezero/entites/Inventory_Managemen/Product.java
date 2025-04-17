package tn.esprit.gaspillagezero.entites.Inventory_Managemen;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.OrderItem;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"stockTransactions", "salesRecords"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ProductID;

    public List<SalesRecord> getSalesRecords() {
        return salesRecords;
    }

    public void setSalesRecords(List<SalesRecord> salesRecords) {
        this.salesRecords = salesRecords;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    private String ProductName;
    @Enumerated(EnumType.STRING)
    private  ProductCategory Category;
    private Date ExprireDate;
    private int StockLevel;

    private String productDescription;
    private int productPrice;
    private String imgPath;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<StockTransaction> stockTransactions;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference
    private List<SalesRecord> salesRecords;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public int getProductID() {
        return ProductID;
    }

    public void setProductID(int productID) {
        ProductID = productID;
    }

    public ProductCategory getCategory() {
        return Category;
    }

    public void setCategory(ProductCategory category) {
        Category = category;
    }

    public Date getExprireDate() {
        return ExprireDate;
    }

    public void setExprireDate(Date exprireDate) {
        ExprireDate = exprireDate;
    }

    public int getStockLevel() {
        return StockLevel;
    }

    public void setStockLevel(int stockLevel) {
        StockLevel = stockLevel;
    }

    public List<StockTransaction> getStockTransactions() {
        return stockTransactions;
    }

    public void setStockTransactions(List<StockTransaction> stockTransactions) {
        this.stockTransactions = stockTransactions;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }
}
