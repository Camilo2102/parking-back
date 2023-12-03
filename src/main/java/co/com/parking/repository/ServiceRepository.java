package co.com.parking.repository;

import co.com.parking.models.Card;
import co.com.parking.models.Service;

public interface ServiceRepository extends GeneralRepository<Service>{
    public Service findByIdService(String idService);
}
