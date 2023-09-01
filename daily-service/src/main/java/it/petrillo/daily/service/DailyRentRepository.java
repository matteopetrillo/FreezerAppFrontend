package it.petrillo.daily.service;

import it.petrillo.daily.model.DailyRent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Map;
import java.util.Optional;

public interface DailyRentRepository extends MongoRepository<DailyRent,String> {
    @Query("{ 'year' : ?0, 'month' : ?1, 'day' : ?2 }")
    Optional<DailyRent> getRentsByDate(int fieldValue1, int fieldValue2, int fieldValue3);

}
