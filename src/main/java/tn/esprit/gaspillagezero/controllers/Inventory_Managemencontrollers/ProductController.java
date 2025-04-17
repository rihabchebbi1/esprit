package tn.esprit.gaspillagezero.controllers.Inventory_Managemencontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.Product;
import tn.esprit.gaspillagezero.services.Inventory_Managemen_Service.IProductService;
import tn.esprit.gaspillagezero.services.Inventory_Managemen_Service.IStockTransactionService;

import java.util.List;
@CrossOrigin(origins = "http://localhost:4201")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @Autowired
    IStockTransactionService stockTransactionService;

    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @PutMapping("/updateProduct/{ProductID}")
    public Product updateProduct(@PathVariable long ProductID, @RequestBody Product productDetails) {

        return productService.updateProduct(ProductID, productDetails);
    }
    @DeleteMapping("/deleteProduct/{ProductID}")
    public void deleteProduct(@PathVariable int ProductID) {
        productService.deleteProduct(ProductID);
    }

    @GetMapping("/retreiveProduct/{ProductID}")
    Product retreiveProduct(@PathVariable int ProductID) {
        return productService.retreiveProduct(ProductID);
    }

    @GetMapping("/retreiveAllProduct")
    public List<Product> retreiveAllProduct() {
        return productService.retreiveAllProduct();
    }
}
