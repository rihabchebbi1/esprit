package tn.esprit.gaspillagezero.services.Invoice_Management_Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.esprit.gaspillagezero.entites.Invoice_Management.Invoice;
import tn.esprit.gaspillagezero.entites.Invoice_Management.InvoiceItem;
import tn.esprit.gaspillagezero.repository.Authentication_User_Management_Repository.UserRepo;
import tn.esprit.gaspillagezero.repository.Invoice_Management_Repository.InvoiceRepository;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private static final Logger log = LoggerFactory.getLogger(InvoiceService.class);
    @Autowired
    private InvoiceRepository invoiceRepository;



    @Autowired
    private UserRepo userRepo;

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    @Transactional
    public Invoice createInvoice(Invoice invoice) {
        invoice.setUser(userRepo.findById(invoice.getUser().getId())
                .orElseThrow(() -> new RuntimeException("User not found")));

        for (InvoiceItem item : invoice.getItems()) {
            item.setInvoice(invoice); // Set the relationship
        }

        calculateInvoiceTotal(invoice);
        return invoiceRepository.save(invoice);
    }

    private void calculateInvoiceTotal(Invoice invoice) {
        BigDecimal total = BigDecimal.ZERO;
        for (InvoiceItem item : invoice.getItems()) {
            total = total.add(item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        invoice.setTotal(total);
    }


    @Transactional
    public Invoice updateInvoice(Long id, Invoice updatedInvoice) {

        return invoiceRepository.findById(id).map(invoice -> {
            invoice.setDate(updatedInvoice.getDate());
            invoice.setDueDate(updatedInvoice.getDueDate());
            invoice.setStatus(updatedInvoice.getStatus());
            invoice.setUser(updatedInvoice.getUser());
            invoice.setTotal(updatedInvoice.getTotal());
            //invoice.setItems(updatedInvoice.getItems());
            //calculateInvoiceTotal(invoice);
            return invoiceRepository.save(invoice);
        }).orElseThrow(() -> new RuntimeException("Invoice not found"));
    }

    @Transactional
    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }




    public byte[] generatePdf(Long invoiceId) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(invoiceId);
        if (invoiceOpt.isEmpty()) {
            throw new RuntimeException("Invoice not found");
        }

        Invoice invoice = invoiceOpt.get();

        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            Document document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();

            // Title
            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20);
            Paragraph title = new Paragraph("Invoice id : " + invoice.getId(), titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" ")); // Spacer

            // Invoice Info
            document.add(new Paragraph("Date: " + invoice.getDate()));
            document.add(new Paragraph("Due Date: " + invoice.getDueDate()));
            document.add(new Paragraph("Status: " + invoice.getStatus()));
            document.add(new Paragraph("Total: " + invoice.getTotal() + " DT"));

            if (invoice.getUser() != null) {
                document.add(new Paragraph("Customer: " + invoice.getUser().getName()));
            }

            document.add(new Paragraph(" ")); // Spacer

            // Table for Invoice Items
            PdfPTable table = new PdfPTable(4);
            table.setWidthPercentage(100);
            table.setWidths(new float[]{4f, 2f, 2f, 2f});

            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

            PdfPCell hcell;

            hcell = new PdfPCell(new Phrase("Product", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Quantity", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            hcell = new PdfPCell(new Phrase("Total", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);

            for (InvoiceItem item : invoice.getItems()) {
                table.addCell(item.getProductName());
                table.addCell(String.valueOf(item.getQuantity()));
                table.addCell(item.getProductPrice().toPlainString());
                BigDecimal itemTotal = item.getProductPrice().multiply(BigDecimal.valueOf(item.getQuantity()));
                table.addCell(itemTotal.toPlainString());
            }

            document.add(table);
            document.close();

            return out.toByteArray();

        } catch (DocumentException e) {
            throw new RuntimeException("Failed to generate PDF", e);
        }

    }
}


