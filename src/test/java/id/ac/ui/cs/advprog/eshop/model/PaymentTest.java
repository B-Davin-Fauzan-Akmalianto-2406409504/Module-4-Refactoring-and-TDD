package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashMap;
import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {
    Payment payment;
    String paymentGlobalId = "123e4567-e89b-12d3-a456-426614174000";

    @BeforeEach
    void setUp() {
        Map<String, String> paymentDetails = new HashMap<>();
        paymentDetails.put("bankName", "BCA");
        paymentDetails.put("referenceCode", "INV-001");
        this.payment = new Payment(paymentGlobalId, PaymentMethod.BANKTRANSFER.getValue(), paymentDetails, PaymentStatus.SUCCESS.getValue());
    }

    @Test
    void testGetPaymentId() {
        assertEquals(paymentGlobalId, this.payment.getPaymentId());
    }
    
    @Test
    void testGetPaymentMethod() {
        assertEquals(PaymentMethod.BANKTRANSFER.getValue(), this.payment.getPaymentMethod());
    }

    @Test
    void testGetPaymentStatus() {
        assertEquals(PaymentStatus.SUCCESS.getValue(), this.payment.getPaymentStatus());
    }
}
