package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;

import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.IngredientDTO;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Order;

import java.util.List;

public interface OrderService {
    List<Order> getAllOrders();
    Order getOrderById(Integer orderID);
    Order createOrder(Order order);
    Order updateOrder(Integer orderID, Order order);
    void deleteOrder(Integer orderID);
    void createOrderFromIngredient(IngredientDTO ingredient);


}
