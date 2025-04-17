package tn.esprit.gaspillagezero.services.Inventory_Managemen_Service;

import tn.esprit.gaspillagezero.entites.Inventory_Managemen.SalesRecord;

import java.util.List;

public interface ISalesRecordService {
    SalesRecord addSalesRecord(SalesRecord salesrecord);
    SalesRecord updateSalesRecord(SalesRecord salesrecord);
    void deleteSalesRecord(long SalesID);
    SalesRecord retreiveSalesRecord(long SalesID);
    List<SalesRecord> retreiveAllSalesRecord();
}
