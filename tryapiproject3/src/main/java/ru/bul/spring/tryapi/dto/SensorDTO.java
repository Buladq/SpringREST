package ru.bul.spring.tryapi.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class SensorDTO {
    @NotEmpty(message = "can`t be empty")
    @Size(min = 3,max = 30,message = "from 2 to 30")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SensorDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}
