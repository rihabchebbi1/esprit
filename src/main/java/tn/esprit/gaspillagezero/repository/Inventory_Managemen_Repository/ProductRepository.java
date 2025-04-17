package tn.esprit.gaspillagezero.repository.Inventory_Managemen_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.Product;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.StockTransaction;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT p FROM Product p LEFT JOIN p.stockTransactions s WHERE s.StockTransactionID = :id")
    Product findProductByTransactionId(@Param("id") int id);

    @Query("SELECT p FROM Product p WHERE p.ProductName = :productName")
    Product findByProductName(String productName);


}
