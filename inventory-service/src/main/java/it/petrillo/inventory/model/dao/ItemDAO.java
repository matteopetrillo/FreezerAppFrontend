package it.petrillo.inventory.model.dao;

import it.petrillo.inventory.model.Item;
import it.petrillo.inventory.model.ItemAvailableDto;
import it.petrillo.inventory.model.ItemCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface ItemDAO extends JpaRepository<Item, Long> {
    Optional<Item> findByNameLike(String name);
    Optional<Item> findByItemCategory(ItemCategory itemCategory);
    @Query(value =
            "SELECT " +
                    "item.id AS itemId, " +
                    "item.name AS itemName, " +
                    "item.item_category AS category, " +
                    "(item_inventory.total_quantity-COALESCE(i.rentedqnt,0)) AS availableQuantity " +
                    "FROM " +
                    "item " +
                    "LEFT JOIN " +
                    "(SELECT " +
                    "item_rented.item_id, " +
                    "item.name, " +
                    "SUM(item_rented.quantity) AS rentedqnt " +
                    "FROM " +
                    "item_rented " +
                    "JOIN " +
                    "item ON item_rented.item_id = item.id " +
                    "WHERE " +
                    "(item_rented.starting_date BETWEEN :start AND :end " +
                    "OR item_rented.ending_date BETWEEN :start AND :end) " +
                    "GROUP BY item_rented.item_id, item.name) AS i " +
                    "ON item.id = i.item_id " +
                    "JOIN " +
                    "item_inventory ON item.id = item_inventory.item_id " +
                    "WHERE (item_inventory.total_quantity-COALESCE(i.rentedqnt,0)) > 0 " +
                    "GROUP BY itemId, itemName, category, availableQuantity;",
            nativeQuery = true)
    List<Object[]> findItemsAvailableByDateRange(@Param("start") LocalDate startingDate,
                                                         @Param("end") LocalDate endingDate);

    default List<ItemAvailableDto> findItemAvailableDtosByDateRange(LocalDate startingDate, LocalDate endingDate) {
        List<Object[]> results = findItemsAvailableByDateRange(startingDate, endingDate);
        List<ItemAvailableDto> dtos = new ArrayList<>();

        for (Object[] result : results) {
            ItemAvailableDto dto = new ItemAvailableDto();
            dto.setItemId((Long) result[0]);
            dto.setItemName((String) result[1]);
            dto.setCategory((String) result[2]);
            dto.setAvailableQuantity((Long) result[3]);
            dtos.add(dto);
        }

        return dtos;
    }
}
