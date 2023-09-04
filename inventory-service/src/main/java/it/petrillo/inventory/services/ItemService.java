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
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public List<ItemDto> getItems() {
        return itemDAO.findAll().stream()
                .map(item -> ItemMapper.getInstance().toItemDto(item))
                .toList();
    }

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
                itemFilteredDtos.add(ItemMapper.getInstance().toItemFilteredDto(item,quantities));
        }

        return itemFilteredDtos;

    }

    public List<ItemRentedDto> getAllRentedItems() {
        return itemRentedDAO.findAll().stream()
                .map(item -> ItemMapper.getInstance().toItemRentedDto(item))
                .toList();
    }
}
