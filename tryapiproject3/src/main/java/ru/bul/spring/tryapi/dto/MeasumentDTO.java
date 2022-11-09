package ru.bul.spring.tryapi.dto;

import ru.bul.spring.tryapi.models.Sensor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class MeasumentDTO {

    @NotNull
    @Min(value = -100,message ="больше -100 и меньше 100")
    @Max(value = 100,message ="больше -100 и меньше 100")
    private double value;
    private boolean raining;

    private SensorDTO sensors;





    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public boolean isRaining() {
        return raining;
    }

    public void setRaining(boolean raining) {
        this.raining = raining;
    }

    public SensorDTO getSensors() {
        return sensors;
    }

    public void setSensors(SensorDTO sensors) {
        this.sensors = sensors;
    }

    @Override
    public String toString() {
        return "MeasumentDTO{" +
                "value=" + value +
                ", raining=" + raining +
                ", sensors=" + sensors +
                '}';
    }
}
