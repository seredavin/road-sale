package ru.avokzal63.roadsale.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.BusStop;
import ru.avokzal63.roadsale.domain.Route;

import java.util.List;

public interface RouteRepo extends JpaRepository<Route, Integer> {
    List<Route> findByNumberAndStartPointAndEndPoint(String number, BusStop startPoint, BusStop endPoint);
}
