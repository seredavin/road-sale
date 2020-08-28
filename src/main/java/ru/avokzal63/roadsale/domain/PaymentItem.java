package ru.avokzal63.roadsale.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "PaymentType",
        "Sum"
})
@Data
public class PaymentItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("PaymentType")
    private Integer paymentType;
    @JsonProperty("Sum")
    private Double sum;

    public PaymentItem(Item item) {
        this.paymentType = item.getPaymentType();
        this.sum = item.getPrice();
    }
}