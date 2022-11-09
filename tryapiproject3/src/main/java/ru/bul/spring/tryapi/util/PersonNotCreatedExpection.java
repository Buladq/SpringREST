package ru.bul.spring.tryapi.util;

public class PersonNotCreatedExpection extends RuntimeException{
    public PersonNotCreatedExpection(String msg){
        super(msg);
    }
}
