package ru.bul.spring.tryapi.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.bul.spring.tryapi.dto.MeasumentDTO;
import ru.bul.spring.tryapi.dto.SensorDTO;
import ru.bul.spring.tryapi.models.Measument;
import ru.bul.spring.tryapi.models.Sensor;
import ru.bul.spring.tryapi.repositpries.MeasumentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class MeasumentService {
    private final MeasumentRepository measumentRepository;

    private final ModelMapper modelMapper;

    public MeasumentService(MeasumentRepository measumentRepository, ModelMapper modelMapper) {
        this.measumentRepository = measumentRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional
    public void save(Measument measument){
       measumentRepository.save(measument);
    }

    public List<Measument> findAll(){
      return   measumentRepository.findAll();
    }


    public List<Measument> findAllRain(){
        List<Measument> mm=measumentRepository.findAll();
        List<Measument> mmwith=new ArrayList<>();

        for (var ss:
            mm ) {
            if(ss.isRaining()){
                mmwith.add(ss);
            }
        }

        return mmwith;
    }

    public Sensor findTryName(String name){
        List<Measument> m=measumentRepository.findAll();
        for (var xz:
             m) {
            if(xz.getSensors().getName().equals(name)){
                return xz.getSensors();
            }
        }
        return null;
    }


    private SensorDTO convertToSensorDTO(Sensor sensor) {
        return modelMapper.map(sensor, SensorDTO.class);
    }


}
