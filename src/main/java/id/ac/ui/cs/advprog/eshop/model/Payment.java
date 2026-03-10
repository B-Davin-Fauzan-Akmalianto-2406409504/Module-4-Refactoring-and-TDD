package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Payment {
    private String paymentId;
    private String paymentType;
    private String paymentStatus;
    private Map<String, String> paymentDetails;

    public void setPaymentStatus(String status) {
        if (PaymentStatus.contains(status)) {
            this.paymentStatus = status;
        }
        else {
            throw new IllegalArgumentException();
        }
    }
}

