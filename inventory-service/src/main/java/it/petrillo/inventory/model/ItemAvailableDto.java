package it.petrillo.inventory.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemAvailableDto {
    private Long itemId;
    private String itemName;
    private String category;
    private Long availableQuantity;
}
