package ru.avokzal63.roadsale.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@NoArgsConstructor
public class Ticket {
    @Id
    private String id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Trip trip;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BusStop startPoint;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private BusStop endPoint;
    private double price;
    private Date date;
    @Enumerated(EnumType.STRING)
    private TickerType type;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Cheque cheque;
    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private OfdConfigure ofdConfigure;

    public String toString() {
        return "Билет " + type
                + " №" + id;
    }
}
