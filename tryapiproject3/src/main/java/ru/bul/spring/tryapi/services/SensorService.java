package ru.bul.spring.tryapi.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bul.spring.tryapi.models.Sensor;
import ru.bul.spring.tryapi.repositpries.SensorRepository;

import java.util.Optional;

@Service
public class SensorService {
   private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public void save(Sensor sensor){
        sensorRepository.save(sensor);
    }

    public Optional<Sensor> getFIndByName(String name){
        return sensorRepository.findByName(name);
    }

    public Optional<Sensor> giveLastSensor(){
        int s=sensorRepository.findAll().size();
        return Optional.ofNullable(sensorRepository.findAll().get(s - 1));
    }


}
