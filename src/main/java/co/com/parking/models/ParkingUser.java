package co.com.parking.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ParkingUser extends GeneralModel  {

    @Column(nullable = false, length = 60)
    private String name;

    @Column(nullable = false, length = 1)
    private String role;

    @Column(nullable = false, length = 1)
    private String active;

    @OneToOne
    @JoinColumn(name = "credential_id")
    private Credential credential;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", active='" + active + '\'' +
                ", credential=" + credential +
                '}';
    }
}
