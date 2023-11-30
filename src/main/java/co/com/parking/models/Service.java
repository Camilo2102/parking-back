package co.com.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Service extends GeneralModel {

    @Column(nullable = false, length = 64)
    private String serviceType;

    @Column(nullable = false)
    private double value;

    public Service(String serviceType, double value) {
        this.serviceType = serviceType;
        this.value = value;
    }
}

