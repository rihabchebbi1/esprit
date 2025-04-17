package tn.esprit.gaspillagezero.controllers.Supplier_Order_Managementcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Order;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Supplier;
import tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service.IngredientStockChecker;
import tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private IngredientStockChecker ingredientStockChecker;
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{orderID}")
    public Order getOrderById(@PathVariable Integer orderID) {
        return orderService.getOrderById(orderID);
    }
    @PutMapping("/{orderID}")
    public Order updateOrder(@PathVariable Integer orderID, @RequestBody Order order) {
        return orderService.updateOrder(orderID, order);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        Order createdOrder = orderService.createOrder(order);
        return ResponseEntity.ok(createdOrder);
    }

    @DeleteMapping("/{orderID}")
    public void deleteOrder(@PathVariable Integer orderID) {
        orderService.deleteOrder(orderID);
    }
    @PostMapping("/check-stock/{ingId}")
    public ResponseEntity<String> checkIngredientStock(@PathVariable Long ingId) {
        ingredientStockChecker.checkAndCreateOrder(ingId);
        return ResponseEntity.ok("Vérification du stock effectuée.");
    }

}

