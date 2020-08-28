package ru.avokzal63.roadsale.domain.dto.ferma;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "DeviceId",
        "RNM",
        "ZN",
        "FN",
        "FDN",
        "FPD"
})
@NoArgsConstructor
@Data
public class StatusDataDeviceDto {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("DeviceId")
    private String deviceId;
    @JsonProperty("RNM")
    private String rnm;
    @JsonProperty("ZN")
    private String zn;
    @JsonProperty("FN")
    private String fn;
    @JsonProperty("FDN")
    private String fdn;
    @JsonProperty("FPD")
    private String fpd;
}
