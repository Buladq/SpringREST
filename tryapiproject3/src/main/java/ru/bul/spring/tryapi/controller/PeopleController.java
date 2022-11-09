package ru.bul.spring.tryapi.controller;


import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.bul.spring.tryapi.dto.PersonDTO;
import ru.bul.spring.tryapi.models.Person;
import ru.bul.spring.tryapi.services.PeopleService;
import ru.bul.spring.tryapi.util.PersonErrorResponse;
import ru.bul.spring.tryapi.util.PersonNotCreatedExpection;
import ru.bul.spring.tryapi.util.PersonNotFoundException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/people")
public class PeopleController {

    private final PeopleService peopleService;

    private final ModelMapper modelMapper;



    public PeopleController(PeopleService peopleService,
                             ModelMapper modelMapper) {
        this.peopleService = peopleService;
        this.modelMapper = modelMapper;
    }


    @GetMapping
    public List<PersonDTO> getPeople(){
        List<PersonDTO> personsDTO=new ArrayList<>();
        List<Person> personList=peopleService.findAll();

        for (var x:
             personList) {
            System.out.println(x.getName());
            personsDTO.add (convertToPersonDTO(x));

        }
       return personsDTO; //Jaskson сам переводит в json
    }

    @GetMapping("/{id}")
    public PersonDTO getPerson(@PathVariable("id") int id){
        return convertToPersonDTO(peopleService.findOne(id));
    }

    @PostMapping()
    public ResponseEntity<HttpStatus> create(@RequestBody @Valid PersonDTO personDTO,
                                             BindingResult bindingResult){
        if(bindingResult.hasErrors()){
          StringBuilder errosMsg=new StringBuilder();

          List<FieldError> erros=bindingResult.getFieldErrors();
            for (FieldError error: erros
                 ) {
                errosMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new PersonNotCreatedExpection(errosMsg.toString());

        }

        peopleService.save(convertToPerson(personDTO));



        return ResponseEntity.ok(HttpStatus.OK);


    }




    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handeExc(PersonNotFoundException e){
        PersonErrorResponse response=new PersonErrorResponse(
                "person not found",
                System.currentTimeMillis()
        );


        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND); //404-status
    }




    @ExceptionHandler
    private ResponseEntity<PersonErrorResponse> handeExc(PersonNotCreatedExpection e){
        PersonErrorResponse response=new PersonErrorResponse(
                e.getMessage(),
                System.currentTimeMillis()
        );


        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST); //404-status
    }




    private Person convertToPerson(PersonDTO personDTO) {
        return modelMapper.map(personDTO, Person.class);
    }

    private PersonDTO convertToPersonDTO(Person person){
        return modelMapper.map(person,PersonDTO.class);
    }




}
