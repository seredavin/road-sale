package ru.avokzal63.roadsale.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "Name",
        "Inn"
})
@Entity
@Data
public class Cashier {
    @JsonIgnore
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("Name")
    @NotBlank(message = "Имя не может быть пустым")
    private String name;
    @JsonProperty("Inn")
    @NotBlank(message = "ИНН не может быть пустым")
    private String inn;

    public String toString() {
        return name;
    }
}