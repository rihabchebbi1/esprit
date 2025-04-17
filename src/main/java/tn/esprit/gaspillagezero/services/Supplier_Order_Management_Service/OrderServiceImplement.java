package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.*;
import tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository.OrderRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImplement implements OrderService{
    @Autowired
    OrderRepository orderRepository;
    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderById(Integer orderID) {
        return orderRepository.findById(orderID).get();
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Integer orderID, Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer orderID) {
    orderRepository.deleteById(orderID);
    }

    public void createOrderFromIngredient(IngredientDTO ingredient) {
        Order order = new Order();
        order.setProductType(ingredient.getPricePerUnit()); // ou selon ta logique
        order.setDeliveryDate(LocalDateTime.now().plusDays(3));
        order.setOrderStatus(OrderStatus.PENDING);

        Supplier supplier = new Supplier();
        supplier.setSupplierID(ingredient.getSupplierID());
        order.setSupplier(supplier);

        OrderItem item = new OrderItem();
        item.setQuantity(100); // selon logique métier
        item.setOrder(order);

        order.setOrderItems(Set.of(item));
        orderRepository.save(order);
    }
}
