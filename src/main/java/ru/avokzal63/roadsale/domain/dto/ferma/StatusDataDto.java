package ru.avokzal63.roadsale.domain.dto.ferma;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.avokzal63.roadsale.domain.Cheque;

import javax.persistence.*;
import java.util.Date;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "StatusCode",
        "StatusName",
        "StatusMessage",
        "ModifiedDateUtc",
        "ReceiptDateUtc",
        "Device"
})
@NoArgsConstructor
@Data
public class StatusDataDto {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("StatusCode")
    private int statusCode;
    @JsonProperty("StatusName")
    private String statusName;
    @JsonProperty("StatusMessage")
    private String statusMessage;
    @JsonProperty("ModifiedDateUtc")
    private Date modifiedDateUtc;
    @JsonProperty("ReceiptDateUtc")
    private Date receiptDateUtc;
    @JsonProperty("Device")
    @OneToOne(cascade = CascadeType.PERSIST)
    private StatusDataDeviceDto statusDataDeviceDto;
}
