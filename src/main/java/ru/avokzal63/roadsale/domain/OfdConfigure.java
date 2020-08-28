package ru.avokzal63.roadsale.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

//@IdClass(OfdConfigureKey.class)
@Entity
@Data
public class OfdConfigure implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotBlank(message = "Наименование не может быть пустым")
    private String name;
    @NotBlank(message = "Наименование организации не может быть пустым")
    private String companyName;
    @NotBlank(message = "ИНН не может быть пустым")
    private String companyInn;
    @NotBlank(message = "Поле не может быть пустым")
    private String taxationSystem;
    @NotNull(message = "Поле не может быть пустым")
    private int defaultPaymentType;
    @NotNull(message = "Поле не может быть пустым")
    private int defaultPaymentMethod;
    private boolean kktFA;
    @NotBlank(message = "Адрес не может быть пустым")
    private String billAddress;
    @NotBlank(message = "Поле не может быть пустым")
    private String defaultVat;
    @NotBlank(message = "URL не может быть пустым")
    private String serviceUrl;
    @NotBlank(message = "Пользователь не может быть пустым")
    private String serviceUser;
    @NotBlank(message = "Пароль не может быть пустым")
    private String servicePassword;
}
