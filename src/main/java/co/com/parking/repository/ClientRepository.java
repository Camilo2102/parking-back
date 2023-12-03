package co.com.parking.repository;

import co.com.parking.models.Client;

public interface ClientRepository extends GeneralRepository<Client>{

    public Client findByIdClient(String idClient);
}
