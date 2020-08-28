package ru.avokzal63.roadsale.domain;

import java.util.Collections;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "TaxationSystem",
        "Email",
        "Phone",
        "PaymentType",
        "KktFA",
        "BillAddress",
        "Items",
        "PaymentItems"
})
@Entity
@Data
@NoArgsConstructor
public class CustomerReceipt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("TaxationSystem")
    private String taxationSystem;
    @JsonProperty("Email")
    private String email;
    @JsonProperty("Phone")
    private String phone;
    @JsonProperty("PaymentType")
    private Integer paymentType;
    @JsonProperty("KktFA")
    private Boolean kktFA;
    @JsonProperty("BillAddress")
    private String billAddress;
    @JsonProperty("Items")
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Item> items = null;
    @JsonProperty("PaymentItems")
    @Transient
    private List<PaymentItem> paymentItems = null;

    public CustomerReceipt(Ticket ticket,
                           Item item,
                           PaymentItem paymentItem) {
        this.taxationSystem = ticket.getOfdConfigure().getTaxationSystem();
        this.email = "avias@avokzal63.ru";
        this.phone = "";
        this.paymentType = ticket.getOfdConfigure().getDefaultPaymentType();
        this.kktFA = ticket.getOfdConfigure().isKktFA();
        this.billAddress = ticket.getOfdConfigure().getBillAddress();
        this.items = Collections.singletonList(item);
        this.paymentItems = Collections.singletonList(paymentItem);
    }
}