package ru.bul.spring.tryapi.controller;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bul.spring.tryapi.dto.MeasumentDTO;
import ru.bul.spring.tryapi.dto.SensorDTO;
import ru.bul.spring.tryapi.models.Measument;
import ru.bul.spring.tryapi.models.Sensor;
import ru.bul.spring.tryapi.services.MeasumentService;
import ru.bul.spring.tryapi.services.SensorService;
import ru.bul.spring.tryapi.util.PersonErrorResponse;
import ru.bul.spring.tryapi.util.PersonNotCreatedExpection;
import ru.bul.spring.tryapi.util.SensorValidator;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/wea")
public class Wethaer {

    private final SensorService sensorService;

    private final ModelMapper modelMapper;

    private final MeasumentService measumentService;

    private final SensorValidator sensorValidator;

    public Wethaer(SensorService sensorService, ModelMapper modelMapper, MeasumentService measumentService, SensorValidator sensorValidator) {
        this.sensorService = sensorService;
        this.modelMapper = modelMapper;
        this.measumentService = measumentService;
        this.sensorValidator = sensorValidator;
    }


    @GetMapping("/measurements")
    public List<MeasumentDTO> getMeasuments() {
        List<MeasumentDTO> measumentDTO = new ArrayList<>();
        List<Measument> measumentList = measumentService.findAll();
        for (var x :
                measumentList) {
           measumentDTO.add(convertToMeasumentDTO(x));
        }

        return measumentDTO; //Jaskson сам переводит в json

    }

    @GetMapping("/measurementsrain")
    public List<MeasumentDTO> getMeasumentsWithRain() {
        create100izmerenii();
        List<MeasumentDTO> measumentDTO1 = new ArrayList<>();
        List<Measument> measumentList1 = measumentService.findAllRain();
        for (var x :
                measumentList1) {
            measumentDTO1.add(convertToMeasumentDTO(x));
        }

        return measumentDTO1; //Jaskson сам переводит в json

    }
    @GetMapping("/measurements/rainyDaysCount")
    public int getMeasumentsWithRainCount() {

        List<MeasumentDTO> measumentDTO1 = new ArrayList<>();
        List<Measument> measumentList1 = measumentService.findAllRain();
        for (var x :
                measumentList1) {
            measumentDTO1.add(convertToMeasumentDTO(x));
        }

        return measumentDTO1.size(); //Jaskson сам переводит в json

    }





    @PostMapping("sensors/registration")
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid SensorDTO sensorDTO,
                                             BindingResult bindingResult) {
        sensorValidator.validate(sensorDTO, bindingResult);
        if (bindingResult.hasErrors()) {
            StringBuilder errosMsg = new StringBuilder();

            List<FieldError> erros = bindingResult.getFieldErrors();
            for (FieldError error : erros
            ) {
                errosMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedExpection(errosMsg.toString()); //обычная заглушка для ошибки

        }

        sensorService.save(convertToSensor(sensorDTO));


        return ResponseEntity.ok(HttpStatus.OK);


    }

    @PostMapping("/measurements/add")
    public ResponseEntity<HttpStatus> createMeas(@RequestBody @Valid MeasumentDTO measumentDTO,
                                                 BindingResult bindingResult) {

        Measument measument=convertToMeasument(measumentDTO);
        String naem=measumentDTO.getSensors().getName();
        Sensor sensor= measumentService.findTryName(naem);

        measument.setSensors(sensor);
        if (bindingResult.hasErrors() || sensor==null) {
            StringBuilder errosMsg = new StringBuilder();

            List<FieldError> erros = bindingResult.getFieldErrors();
            for (FieldError error : erros
            ) {
                errosMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";"); //обычная заглушка для ошибки
            }
            throw new PersonNotCreatedExpection(errosMsg.toString());

        }

        measumentService.save(measument);


        return ResponseEntity.ok(HttpStatus.OK);


    }


    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handeExc(PersonNotCreatedExpection e) {
        PersonErrorResponse response = new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //404-status
    }


    private Sensor convertToSensor(SensorDTO sensorDTO) {
        return modelMapper.map(sensorDTO, Sensor.class);
    }

    private Measument convertToMeasument(MeasumentDTO measumentDTO) {
        return modelMapper.map(measumentDTO, Measument.class);
    }

    private MeasumentDTO convertToMeasumentDTO(Measument measument) {
        return modelMapper.map(measument,MeasumentDTO.class);
    }

    public void create100izmerenii(){
        Optional<Sensor> sensor=sensorService.giveLastSensor();

        boolean[] ft={false,true};
        List<Integer> randomvalue=new ArrayList<>();
        int min = -99;
        int max = 99;
        int diff = max - min;
        Random random = new Random();
        for (int j = 0; j < 1000; j++) {
            double w = random.nextDouble(diff + 1);
            w += min;
            int n = (int)Math.floor(Math.random() * ft.length);
           Measument measument=new Measument();
           measument.setSensors(sensor.get());
           measument.setValue(w);
           measument.setRaining(ft[n]);
           measumentService.save(measument);
        }







    }
}
