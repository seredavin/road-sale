package ru.avokzal63.roadsale.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Data
public class Trip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NotNull(message = "Дата отправления должна быть заполнена")
    private Date startDate;
    @NotNull(message = "Маршрут должен быть обязательно заполнен")
    @OneToOne(cascade = CascadeType.ALL)
    private Route route;

    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm");
        return simpleDateFormat.format(startDate) + " " + route;
    }
}
