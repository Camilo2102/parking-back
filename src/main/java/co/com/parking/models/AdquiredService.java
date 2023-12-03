package co.com.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class AdquiredService extends GeneralModel{
    @Column(nullable = false,length = 64)
    private String description;

    @ManyToOne
    @JoinColumn(name="serviceDetail_id")
    private ServiceDetail serviceDetail;

    @ManyToOne
    @JoinColumn(name="service_id")
    private Service service;

    public AdquiredService(String description) {
        this.description = description;
    }
}
