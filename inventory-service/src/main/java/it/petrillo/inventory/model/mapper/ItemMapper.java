package it.petrillo.inventory.model.mapper;

import it.petrillo.dto.ItemDto;
import it.petrillo.dto.ItemRentedDto;
import it.petrillo.inventory.model.Item;
import it.petrillo.inventory.model.ItemRented;

public class ItemMapper {

    public static ItemRentedDto toItemRentedDto (ItemRented item) {
        return new ItemRentedDto(
                item.getId(),
                item.getProduction_id(),
                item.getItem().getName(),
                item.getQuantity(),
                item.getStartingDate(),
                item.getEndingDate()
        );
    }

    public static ItemDto toItemDto (Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                String.valueOf(item.getItemCategory()),
                item.getItemInventory().getTotalQuantity()
        );
    }

    public static ItemRented toItemRented(ItemRentedDto dto, Item item) {
        return new ItemRented(
                item,
                dto.getStartingDate(),
                dto.getEndingDate(),
                dto.getProductionId(),
                dto.getQuantity()
        );
    }

}
