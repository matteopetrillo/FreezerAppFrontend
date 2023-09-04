package it.petrillo.inventory.model.dao;

import it.petrillo.inventory.model.ItemRented;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ItemRentedDAO extends ListCrudRepository<ItemRented, Long> {

    @Query("SELECT r FROM ItemRented r WHERE r.startingDate BETWEEN :start AND :end")
    List<ItemRented> findByDateRange(@Param("start") LocalDate startingDate,
                                     @Param("end") LocalDate endingDate);
}
