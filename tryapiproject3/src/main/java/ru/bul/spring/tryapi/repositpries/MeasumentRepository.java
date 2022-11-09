package ru.bul.spring.tryapi.repositpries;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.bul.spring.tryapi.models.Measument;


@Repository
public interface MeasumentRepository extends JpaRepository<Measument,Integer> {
}
