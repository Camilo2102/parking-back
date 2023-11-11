package com.example.parking;

import com.example.parking.utils.EmailUtil;
import com.example.parking.utils.TokenUtil;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@OpenAPIDefinition(
        info = @Info(
                title = "Parking api",
                version = "0.1",
                description = "Listado de endpoints de parking",
                license = @License(name = "Apache 2.0", url = "http://nose:&"),
                contact = @Contact(url = "http://parking.com", name = "Parking", email = "parking@mail.com")
        )
)
@ComponentScan(basePackages = "com.example.parking.repository")
@ComponentScan(basePackages = "com.example.parking.utils")
@EnableCaching
public class ParkingApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ParkingApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        initializeValues();
    }

    private void initializeValues() {
        TokenUtil.initializeTokenUtil();
        EmailUtil.initializeEmailUtil();
    }
}