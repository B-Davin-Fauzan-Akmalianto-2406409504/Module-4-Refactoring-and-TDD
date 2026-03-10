package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private List<Payment> paymentData = new ArrayList<>();

    public Payment add(Payment payment){
        int i = 0;
        for(Payment savedPayment : paymentData){
            if(savedPayment.getPaymentId().equals(payment.getPaymentId())){
                paymentData.remove(i);
                paymentData.add(i, payment);
                return payment;
            }
            i += 1;
        }
        paymentData.add(payment);
        return payment;
    }


    public Payment findById(String id){
        for(Payment savedPayment : paymentData){
            if(savedPayment.getPaymentId().equals(id)){
                return savedPayment;
            }
        }
        return null;
    }

    public List<Payment> findAll(){
        return new ArrayList<>(paymentData);
    }

    public Payment update(String id, Payment updatedPayment) {
        for (int i = 0; i < paymentData.size(); i++) {
            Payment payment = paymentData.get(i);
            if (payment.getPaymentId().equals(id)) {
                paymentData.set(i, updatedPayment);
                return updatedPayment;
            }
        }
        return null;
    }

    public Payment delete(String id) {
        for (int i = 0; i < paymentData.size(); i++) {
            Payment payment = paymentData.get(i);
            if (payment.getPaymentId().equals(id)) {
                return paymentData.remove(i);
            }
        }
        return null;
    }
}