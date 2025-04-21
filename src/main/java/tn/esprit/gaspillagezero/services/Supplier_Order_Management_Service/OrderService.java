package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;

import tn.esprit.gaspillagezero.dto.IngredientDTO;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Order;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.OrderStatus;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Integer orderID);
    Order createOrder(Order order);
    Order updateOrder(Integer orderID, Order order);
    void deleteOrder(Integer orderID);

    void createOrderFromIngredient(IngredientDTO ingredientDTO);

    List<Order> findBySuplier(Integer idSuplier);
    List<Order> findByStatus(OrderStatus orderStatus);

    Integer getCountOrders();


}
