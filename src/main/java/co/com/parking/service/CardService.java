package co.com.parking.service;

import co.com.parking.models.Card;
import co.com.parking.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService extends GeneralService<Card> {

    public final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository){
       super(cardRepository);
       this.cardRepository = cardRepository;
    }

    public Card addCard(Card card){
        cardRepository.save(card);
        return card;
    }

    public  Card searchCard(String id){
        return cardRepository.findByIdCard(id);
    }
    public List<Card> getAll(){
        return (List<Card>) cardRepository.findAll();
    }


}
