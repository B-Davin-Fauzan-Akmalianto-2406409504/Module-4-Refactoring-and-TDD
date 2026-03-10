package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    Order order;
    Payment payment;
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        List<Product> products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);

        order = new Order("13652556-012a-4c07-b546-54eb1396d79b", products, 1708560000L, "Safira Sudrajat");

        paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP1234ABC5678");

        payment = new Payment(order.getId(), "VOUCHERCODE", paymentData);
    }

    @Test
    void testAddPayment() {

        when(paymentRepository.add(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.addPayment(order, "VOUCHERCODE", paymentData);

        assertNotNull(result);
        assertEquals(order.getId(), result.getPaymentId());
        assertEquals("VOUCHERCODE", result.getPaymentMethod());
        assertEquals(paymentData, result.getPaymentDetails());
        assertEquals(PaymentStatus.PENDING.getValue(), result.getPaymentStatus());

        verify(paymentRepository, times(1)).add(any(Payment.class));
    }

    @Test
    void testSetStatusSuccess() {
        when(orderRepository.findById(payment.getPaymentId())).thenReturn(order);

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertNotNull(result);
        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getPaymentStatus());

        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());

        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testSetStatusRejected() {
        when(orderRepository.findById(payment.getPaymentId())).thenReturn(order);

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertNotNull(result);
        assertEquals(PaymentStatus.REJECTED.getValue(), result.getPaymentStatus());

        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());

        verify(orderRepository, times(1)).save(order);
    }

    @Test
    void testGetPayment() {
        when(paymentRepository.findById(payment.getPaymentId())).thenReturn(payment);

        Payment result = paymentService.getPayment(payment.getPaymentId());

        assertNotNull(result);
        assertEquals(payment.getPaymentId(), result.getPaymentId());
        verify(paymentRepository, times(1)).findById(payment.getPaymentId());
    }

    @Test
    void testGetPaymentNotFound() {
        when(paymentRepository.findById("invalid-id")).thenReturn(null);

        Payment result = paymentService.getPayment("invalid-id");

        assertNull(result);
        verify(paymentRepository, times(1)).findById("invalid-id");
    }

    @Test
    void testGetAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);

        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<Payment> result = paymentService.getAllPayments();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(payment.getPaymentId(), result.getFirst().getPaymentId());
        verify(paymentRepository, times(1)).findAll();
    }
}