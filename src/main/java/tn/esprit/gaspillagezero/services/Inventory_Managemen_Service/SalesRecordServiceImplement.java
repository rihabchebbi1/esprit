package tn.esprit.gaspillagezero.services.Inventory_Managemen_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gaspillagezero.entites.Inventory_Managemen.SalesRecord;
import tn.esprit.gaspillagezero.repository.Inventory_Managemen_Repository.SalesRecordRepository;

import java.util.List;

@Service
public class SalesRecordServiceImplement implements ISalesRecordService {

    @Autowired
    private SalesRecordRepository salesRecordRepository;

    @Override
    public SalesRecord addSalesRecord(SalesRecord salesRecord) {
        return salesRecordRepository.save(salesRecord);
    }

    @Override
    public SalesRecord updateSalesRecord(SalesRecord salesRecord) {
        return salesRecordRepository.save(salesRecord);
    }

    @Override
    public void deleteSalesRecord(long SalesID) {
        salesRecordRepository.deleteById(SalesID);
    }

    @Override
    public SalesRecord retreiveSalesRecord(long SalesID) {
        return salesRecordRepository.findById(SalesID).orElse(null);
    }

    @Override
    public List<SalesRecord> retreiveAllSalesRecord() {
        return salesRecordRepository.findAll();
    }
}
