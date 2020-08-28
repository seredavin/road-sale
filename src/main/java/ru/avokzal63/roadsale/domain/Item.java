package ru.avokzal63.roadsale.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Label",
        "Price",
        "Quantity",
        "Amount",
        "Vat",
        "MarkingCode",
        "MarkingCodeStructured",
        "PaymentMethod",
        "OriginCountryCode",
        "CustomsDeclarationNumber",
        "PaymentType"
})
@Entity
@Data
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("Label")
    private String label;
    @JsonProperty("Price")
    private Double price;
    @JsonProperty("Quantity")
    private Integer quantity;
    @JsonProperty("Amount")
    private Double amount;
    @JsonProperty("Vat")
    private String vat;
    @JsonProperty("MarkingCode")
    private String markingCode;
//    @JsonProperty("MarkingCodeStructured")
//    public MarkingCodeStructured markingCodeStructured;
    @JsonProperty("PaymentMethod")
    private Integer paymentMethod;
    @JsonProperty("OriginCountryCode")
    private String originCountryCode;
    @JsonProperty("CustomsDeclarationNumber")
    private String customsDeclarationNumber;
    @JsonProperty("PaymentType")
    private Integer paymentType;

    public Item(Ticket ticket) {
        this.label = ticket.toString();
        this.price = ticket.getPrice();
        this.quantity = 1;
        this.amount = ticket.getPrice();
        this.vat = ticket.getOfdConfigure().getDefaultVat();
        this.markingCode = "";
        this.paymentMethod = ticket.getOfdConfigure().getDefaultPaymentMethod();
        this.originCountryCode = "643";
        this.customsDeclarationNumber = "";
        this.paymentType = ticket.getOfdConfigure().getDefaultPaymentType();
    }
}