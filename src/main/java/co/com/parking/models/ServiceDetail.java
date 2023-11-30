package co.com.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ServiceDetail extends GeneralModel{
    @Column(nullable = false, length =64)
    private LocalDateTime entry_hour;

    @Column(nullable = false, length =64)
    private LocalDateTime exit_hour;

    @Column(nullable = false, length =64)
    private String description;

    @ManyToOne
    @JoinColumn(name="car_id")
    private Car car;

    @ManyToOne
    @JoinColumn(name="card_id")
    private Card card;

    @ManyToOne
    @JoinColumn(name="service_id")
    private Service service;



    public ServiceDetail(LocalDateTime entry_hour, LocalDateTime exit_hour) {
        this.entry_hour = entry_hour;
        this.exit_hour = exit_hour;
    }
}
