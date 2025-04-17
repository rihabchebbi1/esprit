package tn.esprit.gaspillagezero.services.Inventory_Managemen_Service;

import tn.esprit.gaspillagezero.entites.Inventory_Managemen.Product;

import java.util.List;

public interface IProductService {
    Product addProduct(Product product);
    Product updateProduct(long productID, Product productDetails);
    void deleteProduct(long ProductID);
    Product retreiveProduct(long ProductID);
    List<Product> retreiveAllProduct();
}
