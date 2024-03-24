package tn.esprit.devops_project.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.devops_project.entities.Invoice;
import tn.esprit.devops_project.entities.Operator;
import tn.esprit.devops_project.entities.Supplier;
import tn.esprit.devops_project.repositories.InvoiceDetailRepository;
import tn.esprit.devops_project.repositories.InvoiceRepository;
import tn.esprit.devops_project.repositories.OperatorRepository;
import tn.esprit.devops_project.repositories.SupplierRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class InvoiceServiceImplTest {

    @InjectMocks
    private InvoiceServiceImpl invoiceService;

    @Mock
    private InvoiceRepository invoiceRepository;

    @Mock
    private OperatorRepository operatorRepository;

    @Mock
    private InvoiceDetailRepository invoiceDetailRepository;

    @Mock
    private SupplierRepository supplierRepository;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveAllInvoices() {
        Invoice invoice1 = new Invoice();
        Invoice invoice2 = new Invoice();
        when(invoiceRepository.findAll()).thenReturn(Arrays.asList(invoice1, invoice2));

        List<Invoice> result = invoiceService.retrieveAllInvoices();

        assertEquals(2, result.size());
        verify(invoiceRepository, times(1)).findAll();
    }

    @Test
    public void testCancelInvoice() {
        Long id = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(invoice)).thenReturn(invoice);

        invoiceService.cancelInvoice(id);

        verify(invoiceRepository, times(1)).findById(id);
        verify(invoiceRepository, times(1)).save(invoice);
    }

    @Test
    public void testRetrieveInvoice() {
        Long id = 1L;
        Invoice invoice = new Invoice();
        when(invoiceRepository.findById(id)).thenReturn(Optional.of(invoice));

        Invoice result = invoiceService.retrieveInvoice(id);

        assertEquals(invoice, result);
        verify(invoiceRepository, times(1)).findById(id);
    }

    @Test
    public void testGetInvoicesBySupplier() {
        Long id = 1L;
        Supplier supplier = new Supplier();
        when(supplierRepository.findById(id)).thenReturn(Optional.of(supplier));

        List<Invoice> result = invoiceService.getInvoicesBySupplier(id);

        assertEquals(supplier.getInvoices(), result);
        verify(supplierRepository, times(1)).findById(id);
    }

   
    @Test
    public void testGetTotalAmountInvoiceBetweenDates() {
        Date startDate = new Date();
        Date endDate = new Date();
        float totalAmount = 100.0f;
        when(invoiceRepository.getTotalAmountInvoiceBetweenDates(startDate, endDate)).thenReturn(totalAmount);

        float result = invoiceService.getTotalAmountInvoiceBetweenDates(startDate, endDate);

        assertEquals(totalAmount, result);
        verify(invoiceRepository, times(1)).getTotalAmountInvoiceBetweenDates(startDate, endDate);
    }
}