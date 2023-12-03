package co.com.parking.service;

import co.com.parking.models.Client;
import co.com.parking.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService extends GeneralService<Client> {
    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        super(clientRepository);
        this.clientRepository = clientRepository;
    }

    public Client addClient(Client client){
        clientRepository.save(client);
        return client;
    }

    public Client searchClient(String id){
        return clientRepository.findByIdClient(id);
    }

    public List<Client> getAll(){
        return (List<Client>) clientRepository.findAll();
    }
}
