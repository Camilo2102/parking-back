package co.com.parking.repository;

import co.com.parking.models.Card;
import co.com.parking.models.ServiceDetail;

public interface ServiceDetailRepository extends GeneralRepository<ServiceDetail>{

    public ServiceDetail findByIdServiceDetail(String idServiceDetail);
}
