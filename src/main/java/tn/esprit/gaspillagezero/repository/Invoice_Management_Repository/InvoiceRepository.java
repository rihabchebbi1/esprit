package tn.esprit.gaspillagezero.repository.Invoice_Management_Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.gaspillagezero.entites.Invoice_Management.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> { }

