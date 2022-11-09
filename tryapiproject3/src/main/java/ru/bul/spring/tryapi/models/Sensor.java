package ru.bul.spring.tryapi.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "can`t be empty")
    @Size(min = 3,max = 30,message = "from 2 to 30")
    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "sensors")
    private List<Measument> measuments;


    public Sensor(String name) {
        this.name = name;
    }

    public Sensor() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Measument> getMeasuments() {
        return measuments;
    }

    public void setMeasuments(List<Measument> measuments) {
        this.measuments = measuments;
    }
}
