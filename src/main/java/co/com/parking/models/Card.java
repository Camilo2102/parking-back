package co.com.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Card extends GeneralModel {

    @Column(nullable = false)
    private double totalCost;

    @Column(nullable = false)
    private LocalDateTime payDate;
}
