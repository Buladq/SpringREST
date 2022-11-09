package ru.bul.spring.tryapi.models;


import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "Measument")
public class Measument {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @NotNull
    @Min(value = -100,message ="больше -100 и меньше 100")
    @Max(value = 100,message ="больше -100 и меньше 100")
    @Column(name = "value")
    private double value;


    @Column(name = "raining")
    private boolean raining;


    @ManyToOne
    @JoinColumn(name = "sensor_id",referencedColumnName = "id")
    private Sensor sensors;

    public Measument(double value, boolean raining, Sensor sensors) {
        this.value = value;
        this.raining = raining;
        this.sensors = sensors;
    }

    public Measument() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Sensor getSensors() {
        return sensors;
    }

    public void setSensors(Sensor sensors) {
        this.sensors = sensors;
    }
}
