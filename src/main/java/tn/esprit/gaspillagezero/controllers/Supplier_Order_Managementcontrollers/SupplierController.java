package tn.esprit.gaspillagezero.controllers.Supplier_Order_Managementcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Supplier_Order_Management.Supplier;
import tn.esprit.gaspillagezero.services.Supplier_Order_Management_Service.SupplierService;

import java.util.List;
@RestController
@RequestMapping("/suppliers")
public class SupplierController {
        @Autowired
        private SupplierService supplierService;

        @GetMapping
        public List<Supplier> getAllSuppliers() {
            return supplierService.getAllSuppliers();
        }

        @GetMapping("/count")
    public Integer getCountSuppliers() {
        return supplierService.getCountSuppliers();
    }
        @GetMapping("/{supplierID}")
        public Supplier getSupplierById(@PathVariable Integer supplierID) {
            return supplierService.getSupplierById(supplierID);
        }

        @PostMapping("/add")
        public Supplier addSupplier(@RequestBody Supplier supplier) {
            return supplierService.addSupplier(supplier);
        }

        @PutMapping("/{supplierID}")
        public Supplier updateSupplier(@PathVariable Integer supplierID, @RequestBody Supplier supplier) {
            return supplierService.updateSupplier(supplierID, supplier);
        }

        @DeleteMapping("/{supplierID}")
        public void deleteSupplier(@PathVariable Integer supplierID) {
            supplierService.deleteSupplier(supplierID);
        }
    }

