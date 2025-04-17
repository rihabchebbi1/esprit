package tn.esprit.gaspillagezero.services.Inventory_Managemen_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.Product;
import tn.esprit.gaspillagezero.repository.Inventory_Managemen_Repository.ProductRepository;

import java.util.List;

@Service
public class ProductServiceImplement implements IProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product){
        return productRepository.save(product);
    }

    @Override
    public Product updateProduct(long productId, Product productDetails){
        Product product = productRepository.findById(productId).orElseThrow(() -> new RuntimeException("Produit non trouvé"));
        product.setProductName(productDetails.getProductName());
        product.setCategory(productDetails.getCategory());
        product.setStockLevel(productDetails.getStockLevel());
        product.setExprireDate(productDetails.getExprireDate());
        product.setProductDescription(productDetails.getProductDescription());
        product.setProductPrice(productDetails.getProductPrice());
        return productRepository.save(product);

    }

    @Override
    public void deleteProduct(long ProductID){
        productRepository.deleteById(ProductID);
    }

    @Override
    public Product retreiveProduct(long ProductID){
        return productRepository.findById(ProductID).get();
    }


    @Override
    public List<Product> retreiveAllProduct(){
        return productRepository.findAll();
    }
}
