package co.com.parking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Car extends GeneralModel{

    @Column(nullable = false, length =64)
    private String plate;

    @Column(nullable = false, length =64)
    private String model;

    @Column(nullable = false, length =64)
    private String brand;

    @Column(nullable = false, length =64)
    private String color;


    @ManyToOne
    @JoinColumn(name="client_id")
    private Client client;

    public Car(String plate, String model, String brand, String color) {
        this.plate = plate;
        this.model = model;
        this.brand = brand;
        this.color = color;
    }
}
