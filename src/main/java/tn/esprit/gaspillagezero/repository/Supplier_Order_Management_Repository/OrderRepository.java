package tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Order;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.OrderStatus;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Supplier;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {

    List<Order> findAllByOrderStatus(OrderStatus orderStatus);
    List<Order> findAllBySupplier(Supplier supplier);

}
