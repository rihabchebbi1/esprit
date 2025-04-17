package tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Supplier;
import tn.esprit.gaspillagezero.repository.Supplier_Order_Management_Repository.SupplierRepository;

import java.util.List;
@Service
public class SupplierServiceImplement implements SupplierService{
    @Autowired
    SupplierRepository supplierRepository;
    @Override
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    @Override
    public Supplier getSupplierById(Integer supplierID) {
        return supplierRepository.findById(supplierID).get();
    }

    @Override
    public Supplier addSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier updateSupplier(Integer supplierID, Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public void deleteSupplier(Integer supplierID) {
    supplierRepository.deleteById(supplierID);
    }
}
