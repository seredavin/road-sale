package ru.avokzal63.roadsale.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.BusStop;

import java.util.List;

public interface BusStopRepo extends JpaRepository<BusStop, Integer> {
    List<BusStop> findByName(String name);
    BusStop findById(int id);

    BusStop getOneById(Integer id);
}
