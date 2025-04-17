package tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Supplier;
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {
}
