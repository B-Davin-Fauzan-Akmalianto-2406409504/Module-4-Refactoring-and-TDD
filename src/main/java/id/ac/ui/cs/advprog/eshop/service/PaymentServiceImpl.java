package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        Payment payment = new Payment(order.getId(), method, paymentData);

        if (PaymentMethod.VOUCHERCODE.getValue().equals(method) || PaymentMethod.VOUCHERCODE.name().equals(method)) {
            String voucherCode = paymentData.get("voucherCode");
            if (voucherCode != null && isValidVoucherCode(voucherCode)) {
                payment.setPaymentStatus(PaymentStatus.SUCCESS.getValue());
            } else {
                payment.setPaymentStatus(PaymentStatus.REJECTED.getValue());
            }
        }
        else if (PaymentMethod.BANKTRANSFER.getValue().equals(method) || PaymentMethod.BANKTRANSFER.name().equals(method)) {
            if (isValidBankTransfer(paymentData)) {
                payment.setPaymentStatus(PaymentStatus.SUCCESS.getValue());
            } else {
                payment.setPaymentStatus(PaymentStatus.REJECTED.getValue());
            }
        }
        return paymentRepository.add(payment);
    }

    private boolean isValidBankTransfer(Map<String, String> paymentData) {
        String bankName = paymentData.get("bankName");
        String referenceCode = paymentData.get("referenceCode");

        return bankName != null && !bankName.trim().isEmpty() && referenceCode != null && !referenceCode.trim().isEmpty();
    }

    private boolean isValidVoucherCode(String voucherCode) {
        if (voucherCode.length() != 16) {
            return false;
        }
        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }
        int numCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (Character.isDigit(c)) {
                numCount++;
            }
        }
        return numCount == 8;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setPaymentStatus(status);

        paymentRepository.update(payment.getPaymentId(), payment);

        Order order = orderRepository.findById(payment.getPaymentId());

        if (order != null) {
            if (PaymentStatus.SUCCESS.getValue().equals(status)) {
                order.setStatus(OrderStatus.SUCCESS.getValue());
            } else if (PaymentStatus.REJECTED.getValue().equals(status)) {
                order.setStatus(OrderStatus.FAILED.getValue());
            }
            orderRepository.save(order);
        } else {
            throw new NoSuchElementException("Order tidak ditemukan untuk Payment ID: " + payment.getPaymentId());
        }

        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}