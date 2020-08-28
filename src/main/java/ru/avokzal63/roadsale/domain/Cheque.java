package ru.avokzal63.roadsale.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avokzal63.roadsale.domain.dto.ferma.StatusDataDto;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonRootName(value = "Request")
@JsonPropertyOrder({
        "Inn",
        "Type",
        "InvoiceId",
        "LocalDate",
        "CustomerReceipt",
        "Cashier"
})
@Table(name = "сheque")
@NoArgsConstructor
@Data
public class Cheque {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("Inn")
    private String inn;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("InvoiceId")
    private String invoiceId;
    @JsonProperty("LocalDate")
    private String localDate;
    @ManyToOne (optional=false, cascade=CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JsonProperty("CustomerReceipt")
    private CustomerReceipt customerReceipt;
    @ManyToOne (optional=false)
    @JsonProperty("Cashier")
    private Cashier cashier;
    @JsonIgnore
    private String receiptId;
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    private StatusDataDto statusDataDto;

    public Cheque(Ticket ticket, Item item, PaymentItem paymentItem) {
        String datePattern = "yyyy-MM-dd'T'HH:mm:ss";
        DateFormat df = new SimpleDateFormat(datePattern);
        this.inn = ticket.getOfdConfigure().getCompanyInn();
        this.type = "Income";
        this.invoiceId = ticket.getId();
        this.localDate = df.format(
                        ticket.getDate()
                );
        this.customerReceipt = new CustomerReceipt(ticket, item, paymentItem);
    }
}