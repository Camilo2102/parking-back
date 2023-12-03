package co.com.parking.service;

import co.com.parking.models.Card;
import co.com.parking.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService extends GeneralService<Card> {

    public final CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository){
       super(cardRepository);
       this.cardRepository = cardRepository;
    }


}
