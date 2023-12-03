package co.com.parking.repository;

import co.com.parking.models.Card;

public interface CardRepository extends  GeneralRepository<Card>{

    public Card findByIdCard(String idCard);

}
