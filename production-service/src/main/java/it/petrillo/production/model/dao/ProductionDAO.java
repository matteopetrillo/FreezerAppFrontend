package it.petrillo.production.model.dao;

import it.petrillo.production.model.Production;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductionDAO extends MongoRepository<Production,String> {
}
