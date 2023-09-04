package it.petrillo.inventory.model.mapper;

import it.petrillo.dto.ItemDto;
import it.petrillo.dto.ItemFilteredDto;
import it.petrillo.dto.ItemRentedDto;
import it.petrillo.inventory.model.Item;
import it.petrillo.inventory.model.ItemRented;

public class ItemMapper {

    private static ItemMapper instance;

    public ItemFilteredDto toItemFilteredDto (Item item, int availableQuantities) {
        ItemFilteredDto dto = new ItemFilteredDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setCategory(String.valueOf(item.getItemCategory()));
        dto.setAvailableQuantity(availableQuantities);
        return dto;
    }

    public ItemRentedDto toItemRentedDto (ItemRented item) {
        ItemRentedDto dto = new ItemRentedDto();
        dto.setItemId(item.getItem().getId());
        dto.setItemName(item.getItem().getName());
        dto.setStartingDate(item.getStartingDate());
        dto.setEndingDate(item.getStartingDate());
        dto.setQuantity(item.getQuantity());
        return dto;
    }

    public ItemDto toItemDto (Item item) {
        ItemDto dto = new ItemDto();
        dto.setId(item.getId());
        dto.setName(item.getName());
        dto.setCategory(String.valueOf(item.getItemCategory()));
        dto.setTotalQuantity(item.getItemInventory().getTotalQuantity());
        return dto;
    }


    public static ItemMapper getInstance() {
        if (instance == null)
            instance = new ItemMapper();
        return instance;
    }
}
