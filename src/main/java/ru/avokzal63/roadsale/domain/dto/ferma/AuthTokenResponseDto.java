package ru.avokzal63.roadsale.domain.dto.ferma;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Status",
        "Data"
})
@Data
public class AuthTokenResponseDto {
    @JsonProperty("Status")
    public String status;
    @JsonProperty("Data")
    public AuthTokenDataDto data;
}
