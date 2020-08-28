package ru.avokzal63.roadsale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.avokzal63.roadsale.domain.BusStop;
import ru.avokzal63.roadsale.repos.BusStopRepo;

import java.util.List;

@Service
public class BusStopService {
    @Autowired
    private BusStopRepo busStopRepo;

    public List<BusStop> findAll() {
        return busStopRepo.findAll();
    }

    public BusStop findBuId(Integer id) {
        return busStopRepo.getOneById(id);
    }

    public void addBusStop(BusStop busStop) {
        if (busStopRepo.findByName(busStop.getName()).size() == 0) {
            busStopRepo.save(busStop);
        }
    }

}
