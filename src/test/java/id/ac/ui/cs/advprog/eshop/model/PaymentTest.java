package id.ac.ui.cs.advprog.eshop.model;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class PaymentTest {
    Payment payment;
    UUID paymentGlobalId = UUID.fromString("eb558e9f-1c39-460e-8860-71af6af63bd6");

    @BeforeEach
    void setUp() {
        this.payment = new Payment();
        this.payment.setPaymentId(paymentGlobalId.toString());
        this.payment.setPaymentType("Bank Transfer");
        this.payment.setPaymentStatus("SUCCESS");
        Map<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("bankName", "BCA");
        paymentDetails.put("referenceCode", "INV-001");
        this.payment.setPaymentDetails(paymentDetails);
    }

    @Test
    void testGetPaymentId() {
        assertEquals(paymentGlobalId.toString(), this.payment.getPaymentId());
    }
    
    @Test
    void testGetPaymentType() {
        assertEquals("Bank Transfer", this.payment.getPaymentType());
    }

    @Test
    void testGetPaymentStatus() {
        assertEquals("SUCCESS", this.payment.getPaymentStatus());
    }
}
