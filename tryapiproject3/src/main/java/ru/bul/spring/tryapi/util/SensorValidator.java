package ru.bul.spring.tryapi.util;

import org.springframework.stereotype.Component;
import ru.bul.spring.tryapi.dto.SensorDTO;
import ru.bul.spring.tryapi.models.Sensor;
import ru.bul.spring.tryapi.services.SensorService;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {

    private final SensorService sensorService;

    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> aclazz) {
        return Sensor.class.equals(aclazz);
    }

    @Override
    public void validate(Object o, Errors errors) {
        SensorDTO sensor=(SensorDTO) o;
        if(sensorService.getFIndByName(sensor.getName()).isPresent())
            errors.rejectValue("name","","Sensor с таким именем уже есть");
    }
}












