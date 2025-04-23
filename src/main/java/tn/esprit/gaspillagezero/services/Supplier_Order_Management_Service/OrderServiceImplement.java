package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Menu_Recipe_Management.Ingredient;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.*;
import tn.esprit.gaspillagezero.repository.Menu_Recipe_Management_Repository.IngredientRepository;
import tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository.OrderItemRepository;
import tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository.OrderRepository;
import tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository.SupplierRepository;
import java.time.LocalDateTime;
import java.util.List;


@Service
public class OrderServiceImplement implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    SupplierRepository supplierRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public void createOrderFromIngredient(tn.esprit.gaspillagezero.dto.IngredientDTO ingredientDTO) {
        Supplier supplier = supplierRepository.findById(ingredientDTO.getSupplierID())
                .orElseThrow(() -> new RuntimeException("Fournisseur introuvable"));

        Ingredient ingredient = ingredientRepository.findById(ingredientDTO.getIngredientID())
                .orElseThrow(() -> new RuntimeException("Ingrédient introuvable"));

        Order order = new Order();
        order.setDeliveryDate(LocalDateTime.now().plusDays(3));
        order.setOrderStatus(order.getOrderStatus()); // Default if no status is passe
        order.setSupplier(supplier);
        order.setIngredient(ingredient);
        order.setQuantity(ingredientDTO.getQuantity());
        orderRepository.save(order);

    }

    @Override
    public List<Order> findBySuplier(Integer idSuplier) {
        Supplier supplier= supplierRepository.findById(idSuplier).orElseThrow(
                () -> new RuntimeException("Not Found")
        );
        return orderRepository.findAllBySupplier(supplier);
    }

    @Override
    public List<Order> findByStatus(OrderStatus orderStatus) {
        return orderRepository.findAllByOrderStatus(orderStatus);
    }

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
        order.setOrderStatus(order.getOrderStatus());
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(Integer orderID, Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Integer orderID) {
        orderRepository.findById(orderID).orElseThrow(()->new RuntimeException("Not Found"));
        orderRepository.deleteById(orderID);
    }

    public Integer getCountOrders(){
        return Math.toIntExact(orderRepository.count());
    }

}
