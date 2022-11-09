package ru.bul.spring.tryapi.repositpries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bul.spring.tryapi.models.Sensor;

import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor,Integer> {
    Optional<Sensor> findByName(String name);

}
