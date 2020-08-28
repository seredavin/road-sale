package ru.avokzal63.roadsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avokzal63.roadsale.domain.BusStop;
import ru.avokzal63.roadsale.domain.Route;
import ru.avokzal63.roadsale.repos.BusStopRepo;
import ru.avokzal63.roadsale.repos.RouteRepo;

import java.util.List;
import java.util.Map;

@Service
public class RouteService {
    @Autowired
    private RouteRepo routeRepo;
    @Autowired
    private BusStopRepo busStopRepo;

    public List<Route> findAll() {
        return routeRepo.findAll();
    }

    public void addRoute(Route route) {
        if (routeRepo.findByNumberAndStartPointAndEndPoint(route.getNumber(),
                route.getStartPoint(),
                route.getEndPoint()).size() == 0
                && !route.getStartPoint().equals(route.getEndPoint())) {
            routeRepo.save(route);
        }
    }

    public void updateRoute(Route route, Map<String, String> form) {
        if (route.getId() == Integer.parseInt(form.get("route_id"))
                && routeRepo.findById(route.getId()).isPresent()
                && !form.get("startPoint").equals(form.get("endPoint"))) {
            route.setNumber(form.get("number"));
            route.setStartPoint(busStopRepo.findById(Integer.parseInt(form.get("startPoint"))));
            route.setEndPoint(busStopRepo.findById(Integer.parseInt(form.get("endPoint"))));
            List<BusStop> busStops = route.getBusStops();
            for (Map.Entry<String, String> entry : form.entrySet()) {
                if (entry.getKey().contains("point_")) {
                    Integer index = Integer.parseInt(entry.getKey().split("_")[1]);
                    BusStop point = busStopRepo.findById(Integer.parseInt(entry.getValue()));
                    busStops.set(index, point);
                }
            }
            route.setBusStops(busStops);
            routeRepo.save(route);
        }
    }

    public void addPoint(String addPoint, String postPoint) {

    }

    public void updateRoute(Route route, int postPoint, BusStop addPoint) {
        if (!route.getBusStops().contains(addPoint)) {
            List<BusStop> busStops = route.getBusStops();
            busStops.add(postPoint + 1, addPoint);
            route.setBusStops(busStops);
            routeRepo.save(route);
        }
    }

    public Route getById(int route) {
        return routeRepo.getOne(route);
    }
}
