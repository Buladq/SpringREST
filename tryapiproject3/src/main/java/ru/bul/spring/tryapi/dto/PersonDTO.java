package ru.bul.spring.tryapi.dto;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class PersonDTO {
    @NotEmpty(message = "can`t be empty")
    @Size(min = 2,max = 30,message = "from 2 to 30")
    private String name;

    @Min( value = 0,  message = "more 0")
    private int age;

    @NotEmpty(message = "can`t be empty")
    @Email
    private String email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
