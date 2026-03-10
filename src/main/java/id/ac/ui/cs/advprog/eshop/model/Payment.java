package id.ac.ui.cs.advprog.eshop.model;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Payment {
    private String paymentId;
    private String paymentType;
    private String paymentStatus;
    private Map<String, String> paymentDetails;
}
