package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> paymentList;
    String paymentGlobalId = "123e4567-e89b-12d3-a456-426614174000";
    String paymentGlobalId2 = "123e4567-e89b-12d3-a456-426614174003";

    @BeforeEach
    void setUp() {
        this.paymentRepository = new PaymentRepository();
        this.paymentList = new ArrayList<>();
        Map<String, String> paymentDetails1 = new HashMap<>();
        paymentDetails1.put("bankName", "BCA");
        paymentDetails1.put("referenceCode", "INV-001");
        Payment payment = new Payment(paymentGlobalId, PaymentMethod.BANKTRANSFER.getValue(), paymentDetails1, PaymentStatus.SUCCESS.getValue());
        this.paymentList.add(payment);
        Map<String, String> paymentDetails2 = new HashMap<>();
        paymentDetails2.put("bankName", "BRI");
        paymentDetails2.put("referenceCode", "INV-002");
        Payment payment2 = new Payment(paymentGlobalId2, PaymentMethod.BANKTRANSFER.getValue(), paymentDetails2);
        this.paymentList.add(payment2);
    }

    @Test
    void testAddPayment() {
        Payment payment = paymentList.get(0);
        Payment result = paymentRepository.add(payment);

        Payment findResult = paymentRepository.findById(paymentList.get(0).getPaymentId());
        assertEquals(payment.getPaymentId(), result.getPaymentId());
        assertEquals(payment.getPaymentId(), findResult.getPaymentId());
        assertEquals(payment.getPaymentMethod(), findResult.getPaymentMethod());
        assertEquals(payment.getPaymentDetails(), findResult.getPaymentDetails());
        assertEquals(payment.getPaymentStatus(), findResult.getPaymentStatus());
    }

    @Test
    void testFindByIdIfIdFound() {
        for (Payment payment : paymentList) {
            paymentRepository.add(payment);
        }
        Payment findResult = paymentRepository.findById(paymentList.get(1).getPaymentId());
        assertEquals(paymentList.get(1).getPaymentId(), findResult.getPaymentId());
        assertEquals(paymentList.get(1).getPaymentDetails(), findResult.getPaymentDetails());
        assertEquals(paymentList.get(1).getPaymentMethod(), findResult.getPaymentMethod());
        assertEquals(paymentList.get(1).getPaymentStatus(), findResult.getPaymentStatus());
    }

    @Test
    void testFindByIdIfIdNotFound() {
        for (Payment payment : paymentList) {
            paymentRepository.save(payment);
        }
        Payment findResult = paymentRepository.findById("zczc");
        assertNull(findResult);
    }

    @Test
    void testGetAllPayments() {
        paymentRepository.add(paymentList.get(0));
        paymentRepository.add(paymentList.get(1));

        List<Payment> allPayments = paymentRepository.findAll();

        assertEquals(2, allPayments.size());
        assertEquals(paymentList.get(0).getPaymentId(), allPayments.get(0).getPaymentId());
    }

    @Test
    void testGetAllPaymentsIfEmpty() {
        List<Payment> allPayments = paymentRepository.findAll();

        assertEquals(0, allPayments.size());
    }

    @Test
    void testUpdatePayment() {
        paymentRepository.add(paymentList.get(0));

        Payment updatedPayment = new Payment(paymentGlobalId, PaymentMethod.BANKTRANSFER.getValue(), paymentList.get(0).getPaymentDetails(), PaymentStatus.REJECTED.getValue());

        Payment result = paymentRepository.update(updatedPayment);
        Payment findResult = paymentRepository.findById(paymentGlobalId);

        assertEquals(updatedPayment.getPaymentStatus(), result.getPaymentStatus());
        assertEquals(updatedPayment.getPaymentStatus(), findResult.getPaymentStatus());
    }

    @Test
    void testUpdatePaymentIfIdNotFound() {
        paymentRepository.add(paymentList.get(0));

        Payment updatedPayment = new Payment("1010", PaymentMethod.BANKTRANSFER.getValue(), paymentList.get(0).getPaymentDetails(), PaymentStatus.REJECTED.getValue());

        Payment result = paymentRepository.update(updatedPayment);

        assertNull(result);
    }

    @Test
    void testDeletePayment() {
        paymentRepository.add(paymentList.get(0));

        paymentRepository.delete(paymentGlobalId);
        Payment findResult = paymentRepository.findById(paymentGlobalId);

        assertNull(findResult);
    }

    @Test
    void testDeletePaymentIfIdNotFound() {
        paymentRepository.add(paymentList.get(0));

        paymentRepository.delete("idngawur");

        Payment findResult = paymentRepository.findById(paymentGlobalId);
        assertEquals(paymentGlobalId, findResult.getPaymentId());
    }

    @Test
    void testAddPaymentWithNullInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            paymentRepository.add(null);
        });
    }
}
