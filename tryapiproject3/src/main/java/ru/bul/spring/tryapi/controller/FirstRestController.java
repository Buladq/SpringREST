package ru.bul.spring.tryapi.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController //
@RequestMapping("/api")
public class FirstRestController {



    @GetMapping("/sayhello")
    public String sayHello(){
        return "Hello world";
    }
}
