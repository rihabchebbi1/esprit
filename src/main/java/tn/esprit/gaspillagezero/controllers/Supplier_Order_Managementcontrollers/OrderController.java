package tn.esprit.gaspillagezero.controllers.Supplier_Order_Managementcontrollers;

import org.apache.http.client.methods.HttpOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.dto.IngredientDTO;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Order;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.OrderStatus;
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

    @PostMapping("/ingredient")
    public ResponseEntity<?> createOrderFromIngredient(@RequestBody IngredientDTO dto) {
        orderService.createOrderFromIngredient(dto);
        return ResponseEntity.ok("Commande d’ingrédient créée !");
    }

    @GetMapping("/suplier/{id}")
    public ResponseEntity<List<Order>> findBySUplier(@PathVariable Integer id){
        return ResponseEntity.ok(orderService.findBySuplier(id));
    }

    @GetMapping("/status/{Status}")
    public ResponseEntity<List<Order>> findBySUplier(@PathVariable String status){
        return ResponseEntity.ok(orderService.findByStatus(OrderStatus.valueOf(status)));
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

        return ResponseEntity.status(HttpStatus.CREATED).body(createdOrder);
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

