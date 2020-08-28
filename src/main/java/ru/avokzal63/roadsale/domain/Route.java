package ru.avokzal63.roadsale.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String number;
    @OneToOne(cascade = CascadeType.ALL)
    private BusStop startPoint;
    @OneToOne(cascade = CascadeType.ALL)
    private BusStop endPoint;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<BusStop> busStops;

    public void setStartPoint(BusStop startPoint) {
        if (this.busStops == null) this.busStops = new ArrayList<BusStop>();
        if (this.busStops.get(0) != null
                && this.busStops.size() >= 2) {
            this.busStops.set(0, startPoint);
        } else {
            this.busStops.add(0, startPoint);
        }
        this.startPoint = startPoint;
    }

    public void setEndPoint(BusStop endPoint) {
        if (this.busStops == null) this.busStops = new ArrayList<BusStop>();
        if (this.busStops.size() == 0) {
            this.busStops.add(endPoint);
        } else {
            this.busStops.set(busStops.size() - 1, endPoint);

        }
        this.endPoint = endPoint;
    }

    public String toString() {
        return number +
                " " +
                startPoint.getName() +
                " - " +
                endPoint.getName();
    }
}

