package co.com.parking.repository;

import co.com.parking.models.Client;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends GeneralRepository<Client>{
    @Override
    @Query("SELECT cl FROM Client cl")
    List<Client> findByFilter(Client client, Pageable page);

    @Override
    long countByFilter(Client client);

    @Override
    void deleteAllById(String id);

    @Override
    List<Client> findAll(Pageable page);
}
