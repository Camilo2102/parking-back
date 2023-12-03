package co.com.parking.repository;

import co.com.parking.models.AdquiredService;
import co.com.parking.models.Card;

public interface AdquiredServiceRepository extends GeneralRepository<AdquiredService>{
    public AdquiredService findByIdAdquiredService(String idAdquiredService);
}
