package ru.avokzal63.roadsale.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.Route;
import ru.avokzal63.roadsale.domain.Trip;

import java.util.Date;

public interface TripRepo extends JpaRepository<Trip, Integer> {
    Trip getTopByRouteAndStartDate(Route route, Date startDate);

    Trip findOneById(int trip);
}
