package tn.esprit.gaspillagezero.controllers.Inventory_Managemencontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.SalesRecord;
import tn.esprit.gaspillagezero.services.Inventory_Managemen_Service.ISalesRecordService;

import java.util.List;

@RestController
@RequestMapping("/salesRecord")
public class SalesRecordController {

    @Autowired
    ISalesRecordService salesRecordService;

    @PostMapping("/addSalesRecord")
    public SalesRecord addSalesRecord(@RequestBody SalesRecord salesRecord) {
        return salesRecordService.addSalesRecord(salesRecord);
    }

    @PutMapping("/updateSalesRecord")
    public SalesRecord updateSalesRecord(@RequestBody SalesRecord salesRecord) {
        return salesRecordService.updateSalesRecord(salesRecord);
    }

    @DeleteMapping("/deleteSalesRecord/{SalesID}")
    public void deleteSalesRecord(@PathVariable long SalesID) {
        salesRecordService.deleteSalesRecord(SalesID);
    }

    @GetMapping("/retreiveSalesRecord/{SalesID}")
    public SalesRecord retreiveSalesRecord(@PathVariable long SalesID) {
        return salesRecordService.retreiveSalesRecord(SalesID);
    }

    @GetMapping("/retreiveAllSalesRecord")
    public List<SalesRecord> retreiveAllSalesRecord() {
        return salesRecordService.retreiveAllSalesRecord();
    }
}
