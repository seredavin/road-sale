package ru.avokzal63.roadsale.domain.dto.ferma;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "AuthToken",
        "ExpirationDateUtc"
})
@Data
@Entity
public class AuthTokenDataDto {
    @JsonProperty("AuthToken")
    public String authToken;
    @Id
    @JsonProperty("ExpirationDateUtc")
    public Date expirationDateUtc;
}
