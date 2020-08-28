package ru.avokzal63.roadsale.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Type",
        "Gtin",
        "Serial"
})
public class MarkingCodeStructured {

    @JsonProperty("Type")
    private String type;
    @JsonProperty("Gtin")
    private String gtin;
    @JsonProperty("Serial")
    private String serial;

    public MarkingCodeStructured() {
        this.type = "";
        this.gtin = "";
        this.serial = "";
    }
}