package tn.esprit.gaspillagezero.controllers.Inventory_Managemencontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.Product;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.StockTransaction;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.StockTransactionDTO;
import tn.esprit.gaspillagezero.repository.Inventory_Managemen_Repository.ProductRepository;
import tn.esprit.gaspillagezero.services.Inventory_Managemen_Service.IStockTransactionService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stockTransaction")
public class StockTransactionController {

    @Autowired
    IStockTransactionService stockTransactionService;

    @Autowired
    ProductRepository productRepository;

    @PostMapping("/addStockTransaction/{productName}")
    public StockTransaction addStockTransaction(
            @PathVariable String productName,
            @RequestBody StockTransactionDTO stockTransaction) {

        System.out.println("productName: " + productName);
        System.out.println("type: " + stockTransaction.getType());
        System.out.println("date: " + stockTransaction.getDateTransaction());
        System.out.println("quantity: " + stockTransaction.getChangeQuantity());

        return stockTransactionService.addStockTransaction(productName, stockTransaction);
    }



    @PutMapping("/updateStockTransaction/{StockTransactionID}")
    public StockTransaction updateStockTransaction(@PathVariable long StockTransactionID, @RequestBody StockTransaction stockTransaction) {
        return stockTransactionService.updateStockTransaction(StockTransactionID, stockTransaction);
    }

    @DeleteMapping("/deleteStockTransaction/{StockTransactionID}")
    public void deleteStockTransaction(@PathVariable long StockTransactionID) {
        stockTransactionService.deleteStockTransaction(StockTransactionID);
    }

    @GetMapping("/retreiveStockTransaction/{StockTransactionID}")
    public StockTransaction retreiveStockTransaction(@PathVariable long StockTransactionID) {
        return stockTransactionService.retreiveStockTransaction(StockTransactionID);
    }

    @GetMapping("/retreiveAllStockTransaction")
    public List<StockTransactionDTO> retreiveAllStockTransaction() {
        return stockTransactionService.retreiveAllStockTransaction();
    }
}
