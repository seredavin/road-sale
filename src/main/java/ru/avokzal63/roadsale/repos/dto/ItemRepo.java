package ru.avokzal63.roadsale.repos.dto;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.avokzal63.roadsale.domain.Item;

public interface ItemRepo extends JpaRepository<Item, Integer> {

}
