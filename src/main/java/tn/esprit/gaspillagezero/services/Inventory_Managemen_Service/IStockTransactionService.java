package tn.esprit.gaspillagezero.services.Inventory_Managemen_Service;

import tn.esprit.gaspillagezero.entites.Inventory_Managemen.StockTransaction;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.StockTransactionDTO;

import java.util.List;

public interface IStockTransactionService {
    StockTransaction addStockTransaction(String productName, StockTransactionDTO stocktransaction
    );
    StockTransaction updateStockTransaction(long stockTransactionID, StockTransaction stocktransaction);
    void deleteStockTransaction(long StockTransactionID);
    StockTransaction retreiveStockTransaction(long StockTransactionID);
    List<StockTransactionDTO> retreiveAllStockTransaction();
}
