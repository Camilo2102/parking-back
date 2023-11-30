package co.com.parking.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Client extends GeneralModel {

    @Column(nullable = false,length = 64)
    private String name;

    @Column(nullable = false,length = 64)
    private String lastName;

    @Column(nullable = false,length = 64)
    private String address;

    @Column(nullable = false, length =64)
    private String phone;



    public Client(String name, String lastName, String address, String phone) {
        this.name = name;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
    }


}
