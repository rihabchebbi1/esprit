package tn.esprit.gaspillagezero.services.Inventory_Managemen_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.Product;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.StockTransaction;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.StockTransactionDTO;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.StockTransactionType;
import tn.esprit.gaspillagezero.repository.Inventory_Managemen_Repository.ProductRepository;
import tn.esprit.gaspillagezero.repository.Inventory_Managemen_Repository.StockTransactionRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class StockTransactionServiceImplement implements IStockTransactionService {

    @Autowired
    private StockTransactionRepository stockTransactionRepository;

    @Autowired
    ProductRepository productRepository;

    @Override
    public StockTransaction addStockTransaction(String productName, StockTransactionDTO stockTransactionDTO) {
        Product product = productRepository.findByProductName(productName);
        int stock = product.getStockLevel();
        if (stockTransactionDTO.getType().equals(StockTransactionType.Added)) {
            int total = stock + stockTransactionDTO.getChangeQuantity();
            product.setStockLevel(total);
        }
        if (stockTransactionDTO.getType().equals(StockTransactionType.Removed)) {
            int total = stock - stockTransactionDTO.getChangeQuantity();
            product.setStockLevel(total);
        }
        productRepository.save(product);
        StockTransaction stockTransaction = new StockTransaction();
        stockTransaction.setDateTransaction(stockTransactionDTO.getDateTransaction());
        stockTransaction.setChangeQuantity(stockTransactionDTO.getChangeQuantity());
        stockTransaction.setType(stockTransactionDTO.getType());
        stockTransaction.setProduct(product);
        return stockTransactionRepository.save(stockTransaction);
    }

    @Override
    public StockTransaction updateStockTransaction(long stockTransactionID, StockTransaction stockTransactionDetails) {
        StockTransaction stockTransaction = stockTransactionRepository.findById(stockTransactionID).orElse(null);
        stockTransaction.setType(stockTransactionDetails.getType());
        stockTransaction.setChangeQuantity(stockTransactionDetails.getChangeQuantity());
        stockTransaction.setDateTransaction(stockTransactionDetails.getDateTransaction());
        stockTransaction.setProduct(stockTransactionDetails.getProduct());
        return stockTransactionRepository.save(stockTransaction);
    }

    @Override
    public void deleteStockTransaction(long StockTransactionID) {
        stockTransactionRepository.deleteById(StockTransactionID);
    }

    @Override
    public StockTransaction retreiveStockTransaction(long StockTransactionID) {
        return stockTransactionRepository.findById(StockTransactionID).orElse(null);
    }
    @Override
    public List<StockTransactionDTO> retreiveAllStockTransaction() {
        List<StockTransaction> stockTransactions = stockTransactionRepository.findAll();
        List<StockTransactionDTO> stockTransactionDTOS = new ArrayList<>();
        for (StockTransaction stockTransaction : stockTransactions) {
            StockTransactionDTO stockTransactionDTO = new StockTransactionDTO();
            stockTransactionDTO.setStockTransactionID(stockTransaction.getStockTransactionID());
            stockTransactionDTO.setDateTransaction(stockTransaction.getDateTransaction());
            stockTransactionDTO.setType(stockTransaction.getType());
            stockTransactionDTO.setChangeQuantity(stockTransaction.getChangeQuantity());
            stockTransactionDTO.setProductName(productRepository.findProductByTransactionId(stockTransaction.getStockTransactionID()).getProductName());
            stockTransactionDTOS.add(stockTransactionDTO);
        }
        return stockTransactionDTOS;
    }
}
