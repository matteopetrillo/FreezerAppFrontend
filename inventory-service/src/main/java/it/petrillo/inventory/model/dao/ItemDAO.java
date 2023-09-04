package it.petrillo.inventory.model.dao;

import it.petrillo.inventory.model.Item;
import it.petrillo.inventory.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Optional;

public interface ItemDAO extends ListCrudRepository<Item, Long> {
    Optional<Item> findByNameLike(String name);
    Optional<Item> findByItemCategory(ItemCategory itemCategory);


}
