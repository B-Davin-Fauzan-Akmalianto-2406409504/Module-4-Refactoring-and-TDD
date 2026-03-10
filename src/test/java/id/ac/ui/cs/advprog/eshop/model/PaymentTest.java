package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    Payment payment;
    String paymentGlobalId = "123e4567-e89b-12d3-a456-426614174000";

    @BeforeEach
    void setUp() {
        this.payment = new Payment();
        this.payment.setPaymentId(paymentGlobalId);
        this.payment.setPaymentType("Bank Transfer");
        this.payment.setPaymentStatus("SUCCESS");
        Map<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("bankName", "BCA");
        paymentDetails.put("referenceCode", "INV-001");
        this.payment.setPaymentDetails(paymentDetails);
    }

    @Test
    void testGetPaymentId() {
        assertEquals(paymentGlobalId, this.payment.getPaymentId());
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
