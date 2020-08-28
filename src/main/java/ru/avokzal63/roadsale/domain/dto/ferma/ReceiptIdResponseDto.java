package ru.avokzal63.roadsale.domain.dto.ferma;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Status",
        "Data"
})
@Data
@NoArgsConstructor
public class ReceiptIdResponseDto {
    @JsonProperty("Status")
    private String status;
    @JsonProperty("Data")
    private ReceiptIdDataDto data;
}

