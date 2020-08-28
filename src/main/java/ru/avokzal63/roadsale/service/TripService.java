package ru.avokzal63.roadsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avokzal63.roadsale.domain.Route;
import ru.avokzal63.roadsale.domain.Trip;
import ru.avokzal63.roadsale.repos.TripRepo;

import java.util.Date;
import java.util.List;

@Service
public class TripService {
    @Autowired
    private TripRepo tripRepo;

    public void addTrip(Route route, Date date) {
        if (tripRepo.getTopByRouteAndStartDate(route, date) == null) {
            Trip trip = new Trip();
            trip.setRoute(route);
            trip.setStartDate(date);
            tripRepo.save(trip);
        }

    }

    public List<Trip> findAll() {
        return tripRepo.findAll();
    }

    public Trip findById(int trip) {
        return tripRepo.findOneById(trip);
    }
}
