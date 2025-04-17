package tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Order;
@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
}
