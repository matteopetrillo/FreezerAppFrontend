package it.petrillo.inventory.services;

import it.petrillo.dto.ItemDto;
import it.petrillo.dto.ItemFilteredDto;
import it.petrillo.dto.ItemRentedDto;
import it.petrillo.inventory.model.Item;
import it.petrillo.inventory.model.ItemRented;
import it.petrillo.inventory.model.dao.ItemDAO;
import it.petrillo.inventory.model.dao.ItemRentedDAO;
import it.petrillo.inventory.model.mapper.ItemMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class ItemService {

    private ItemDAO itemDAO;
    private ItemRentedDAO itemRentedDAO;

    /**
     * Get all items.
     *
     * @return A list of `ItemDto` objects.
     */
    public List<ItemDto> getItems() {
        return itemDAO.findAll().stream()
                .map(ItemMapper::toItemDto)
                .toList();
    }

    /**
     * Get items filtered by date range.
     *
     * @param start The start date.
     * @param end The end date.
     * @return A list of `ItemFilteredDto` objects.
     */
    public List<ItemFilteredDto> getDateFilteredItems(LocalDate start, LocalDate end) {
        List<ItemRented> itemRenteds = itemRentedDAO.findByDateRange(start,end);
        List<Item> items = itemDAO.findAll();

        Map<Long, Integer> availableQuantities = new HashMap<>();

        for (Item item : items) {
            availableQuantities.put(item.getId(), item.getItemInventory().getTotalQuantity());
        }

        for (ItemRented rentedItem : itemRenteds) {
            availableQuantities.put(rentedItem.getItem().getId(),
                    availableQuantities.get(rentedItem.getItem().getId()) - rentedItem.getQuantity());
        }

        List<ItemFilteredDto> itemFilteredDtos = new ArrayList<>();

        for (Item item : items) {
            int quantities = availableQuantities.get(item.getId());
            if (quantities > 0)
                itemFilteredDtos.add(ItemMapper.toItemFilteredDto(item,quantities));
        }

        return itemFilteredDtos;

    }

    /**
     * Get all rented items.
     *
     * @return A list of `ItemRentedDto` objects.
     */
    public List<ItemRentedDto> getAllRentedItems() {
        return itemRentedDAO.findAll().stream()
                .map(ItemMapper::toItemRentedDto)
                .toList();
    }

    public ResponseEntity<Long> rentItem (ItemRentedDto dto) throws IllegalArgumentException {
        Optional<Item> itemOptional = itemDAO.findById(dto.getItemId());
        if (itemOptional.isEmpty())
            throw new IllegalArgumentException("L'item selezionato non è corretto");
        dto.setItemName(itemOptional.get().getName());
        ItemRented itemRented = itemRentedDAO.save(ItemMapper.toItemRented(dto,itemOptional.get()));
        return ResponseEntity.ok(itemRented.getId());
    }


}
