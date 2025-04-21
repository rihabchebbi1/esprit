package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;

import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Supplier;

import java.util.List;

public interface SupplierService {
    List<Supplier> getAllSuppliers();
    Supplier getSupplierById(Integer supplierID);
    Supplier addSupplier(Supplier supplier);
    Supplier updateSupplier(Integer supplierID, Supplier supplier);
    void deleteSupplier(Integer supplierID);
    Integer getCountSuppliers();
}
